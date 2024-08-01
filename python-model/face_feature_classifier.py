import cv2
import mediapipe as mp
import numpy as np
from matplotlib import pyplot as plt
from io import BytesIO
import sys
import requests

def classify_features():
    if len(sys.argv) > 1:
        roomId = sys.argv[1]
    else :
        return "이미지를 읽을 수 없습니다"

    # MediaPipe FaceMesh 초기화
    mp_face_mesh = mp.solutions.face_mesh
    face_mesh = mp_face_mesh.FaceMesh()

    # 바이트 데이터를 OpenCV 이미지로 변환
    # image_stream = BytesIO(image_bytes)
    response = requests.get(f'http://localhost:8080/missing-persons/get-img?roomId={roomId}')

    # 얼굴 탐지기 로드 (Haar Cascades)
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

    # 이미지 읽기
    # 바이트 데이터를 OpenCV 이미지로 변환
    image_stream = BytesIO(response.content)
    image = np.asarray(bytearray(image_stream.read()), dtype=np.uint8)
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)
    image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

    # # OpenCV 이미지 불러오기
    # image = cv2.imread(image_path)
    # image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

    # 얼굴 특성 추출
    results = face_mesh.process(image_rgb)

    if not results.multi_face_landmarks:
        print("No face detected.")
        return

    landmarks = results.multi_face_landmarks[0].landmark

    # 랜드마크를 리스트로 변환
    landmarks_list = [(lm.x, lm.y, lm.z) for lm in landmarks]
    landmarks_array = np.array(landmarks_list, dtype=np.float32)

    def get_coordinates(indices):
        return landmarks_array[indices, :2]

    def get_face_shape():
        # 얼굴 형상 추출 로직 예시
        forehead_landmarks = [10, 109, 67]
        jawline_landmarks = [152, 234, 454, 466]
        
        forehead_points = np.array([landmarks_array[i] for i in forehead_landmarks])
        jawline_points = np.array([landmarks_array[i] for i in jawline_landmarks])
        
        forehead_width = np.linalg.norm(forehead_points[0] - forehead_points[2])
        jawline_width = np.linalg.norm(jawline_points[0] - jawline_points[2])
        
        ratio = forehead_width / jawline_width
        
        if ratio < 0.8:
            return "계란형"
        elif ratio < 1.2:
            return "둥근형"
        else:
            return "각진형"

    def get_forehead_features():
        # 이마의 특성을 추출하는 로직 예시
        forehead_landmarks = [10, 109, 67]
        
        forehead_points = np.array([landmarks_array[i] for i in forehead_landmarks])
        
        forehead_width = np.linalg.norm(forehead_points[0] - forehead_points[2])
        
        if forehead_width < 0.05:
            return "좁은 이마"
        elif forehead_width < 0.1:
            return "중간 이마"
        else:
            return "넓은 이마"

    def get_eye_features():
        # 눈 특성을 추출하는 로직 예시
        left_eye_landmarks = [33, 160, 158, 133, 153, 144]
        right_eye_landmarks = [362, 385, 387, 263, 373, 380]
        
        def calculate_eye_size(landmarks):
            eye_points = np.array([landmarks_array[i] for i in landmarks])
            distances = [
                np.linalg.norm(eye_points[i] - eye_points[j])
                for i in range(len(eye_points))
                for j in range(i + 1, len(eye_points))
            ]
            return np.mean(distances)
        
        left_eye_size = calculate_eye_size(left_eye_landmarks)
        right_eye_size = calculate_eye_size(right_eye_landmarks)
        
        avg_eye_size = (left_eye_size + right_eye_size) / 2
        
        if avg_eye_size < 0.05:
            size = "작은 눈"
        elif avg_eye_size < 0.1:
            size = "중간 눈"
        else:
            size = "큰 눈"
        
        return {
            "size": size,
            "shape": "둥근 눈",  # Placeholder
            "double_eyelid": "있음",  # Placeholder
            "eyebrow_shape": "직선",  # Placeholder
            "eyebrow_position": "중간",  # Placeholder
            "distance_between_eyes": "중간"  # Placeholder
        }

    def get_nose_features():
        # 코 특성을 추출하는 로직 예시
        nose_landmarks = [1, 6, 49, 121, 128]
        
        nose_points = np.array([landmarks_array[i] for i in nose_landmarks])
        
        nose_width = np.linalg.norm(nose_points[1] - nose_points[3])
        nose_height = np.linalg.norm(nose_points[2] - nose_points[4])
        
        if nose_width < 0.05:
            size = "작은 코"
        elif nose_width < 0.1:
            size = "중간 코"
        else:
            size = "큰 코"
        
        return {
            "size": size,
            "shape": "뾰족한 코끝",  # Placeholder
            "bridge_height": "중간"  # Placeholder
        }

    def get_lip_features():
        # 입술 특성을 추출하는 로직 예시
        upper_lip_landmarks = [13, 62, 66, 71, 14]
        lower_lip_landmarks = [291, 294, 305, 306, 308]
        
        def calculate_lip_thickness(landmarks):
            lip_points = np.array([landmarks_array[i] for i in landmarks])
            distances = [
                np.linalg.norm(lip_points[i] - lip_points[j])
                for i in range(len(lip_points))
                for j in range(i + 1, len(lip_points))
            ]
            return np.mean(distances)
        
        upper_lip_thickness = calculate_lip_thickness(upper_lip_landmarks)
        lower_lip_thickness = calculate_lip_thickness(lower_lip_landmarks)
        
        avg_thickness = (upper_lip_thickness + lower_lip_thickness) / 2
        
        if avg_thickness < 0.02:
            thickness = "얇은 입술"
        elif avg_thickness < 0.05:
            thickness = "중간 입술"
        else:
            thickness = "두꺼운 입술"
        
        return {
            "size": "중간 입술",  # Placeholder
            "thickness": thickness,
            "shape": "입꼬리 올라간 형태"  # Placeholder
        }

    def get_cheekbone_features():
        # 광대뼈 특성을 추출하는 로직 예시
        cheekbone_landmarks = [234, 454]
        
        cheekbone_points = np.array([landmarks_array[i] for i in cheekbone_landmarks])
        
        cheekbone_height = np.linalg.norm(cheekbone_points[0] - cheekbone_points[1])
        
        if cheekbone_height < 0.05:
            return "낮은 광대뼈"
        elif cheekbone_height < 0.1:
            return "중간 광대뼈"
        else:
            return "높은 광대뼈"

    def get_jawline_features():
        # 턱 특성을 추출하는 로직 예시
        jawline_landmarks = [152, 234, 454, 466]
        
        jawline_points = np.array([landmarks_array[i] for i in jawline_landmarks])
        
        jawline_width = np.linalg.norm(jawline_points[0] - jawline_points[2])
        
        if jawline_width < 0.1:
            return "얇은 턱"
        elif jawline_width < 0.2:
            return "중간 턱"
        else:
            return "굵은 턱"

    def get_ear_features():
        # 귀 특성을 추출하는 로직 예시 (대략적인 접근)
        ear_landmarks = [293, 316]  # Placeholder indices
        
        ear_points = np.array([landmarks_array[i] for i in ear_landmarks])
        
        ear_width = np.linalg.norm(ear_points[0] - ear_points[1])
        
        if ear_width < 0.03:
            size = "작은 귀"
        elif ear_width < 0.06:
            size = "중간 귀"
        else:
            size = "큰 귀"
        
        return {
            "size": size,
            "shape": "중간",  # Placeholder
            "position": "중간"  # Placeholder
        }

    def get_skin_features(image):
        # Convert image to HSV color space
        image_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
        
        # Define HSV range for skin color detection (example range, may need tuning)
        lower_skin = np.array([0, 20, 70], dtype=np.uint8)
        upper_skin = np.array([20, 255, 255], dtype=np.uint8)
        
        # Create a mask for skin color
        skin_mask = cv2.inRange(image_hsv, lower_skin, upper_skin)
        
        # Calculate skin area percentage
        skin_area = np.sum(skin_mask > 0)
        total_area = image.shape[0] * image.shape[1]
        skin_percentage = (skin_area / total_area) * 100
        
        # Analyze blemishes and wrinkles (simplified)
        blemishes = "없음" if skin_percentage > 10 else "있음"  # Example logic
        wrinkles = "없음"  # Placeholder
        
        return {
            "tone": "중간 톤",  # Placeholder
            "blemishes": blemishes,
            "wrinkles": wrinkles
        }

    def get_hairstyle_features(image):
        # Define a placeholder for hairstyle features
        # Actual analysis would require advanced methods
        return {
            "length": "중간 길이",  # Placeholder: this could be estimated based on hairline landmarks if available
            "style": "웨이브",     # Placeholder: this would be determined by a more sophisticated analysis or model
            "hairline": "중간"     # Placeholder: Hairline position could be inferred from landmarks if available
        }

    # Extract features
    face_shape = get_face_shape()
    forehead_features = get_forehead_features()
    eye_features = get_eye_features()
    nose_features = get_nose_features()
    lip_features = get_lip_features()
    cheekbone_features = get_cheekbone_features()
    jawline_features = get_jawline_features()
    ear_features = get_ear_features()
    skin_features = get_skin_features(image)
    hairstyle_features = get_hairstyle_features(image)
    # wrinkle_features = get_wrinkle_features()
    # cheek_features = get_cheek_features()
    # additional_features = get_additional_features()

    # 결과 출력
    print(f"얼굴 형상: {face_shape}")
    print(f"이마: {forehead_features}")
    print(f"눈: {eye_features}")
    print(f"코: {nose_features}")
    print(f"입: {lip_features}")
    print(f"광대뼈: {cheekbone_features}")
    print(f"턱: {jawline_features}")
    print(f"귀: {ear_features}")
    print(f"피부 상태: {skin_features}")
    print(f"헤어스타일: {hairstyle_features}")
    # print(f"주름: {wrinkle_features}")
    # print(f"볼: {cheek_features}")
    # print(f"기타 특징: {additional_features}")

    # # 랜드마크 표시된 이미지 저장 및 출력
    # for idx, landmark in enumerate(landmarks):
    #     x = int(landmark.x * image.shape[1])
    #     y = int(landmark.y * image.shape[0])
    #     cv2.circle(image, (x, y), 2, (0, 255, 0), -1)
    #     cv2.putText(image, str(idx), (x, y), cv2.FONT_HERSHEY_SIMPLEX, 0.4, (0, 0, 0), 1)

    # output_image_path = 'output_face.png'
    # cv2.imwrite(output_image_path, image)
    # plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
    # plt.show()

# # 예제 바이트 데이터 사용
# with open('python-model/sample/flower.webp', 'rb') as f:
#     image_bytes = f.read()

if __name__ == "__main__":
    classify_features()

