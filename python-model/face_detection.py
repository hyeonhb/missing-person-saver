import cv2
import sys
from io import BytesIO
import numpy as np
import requests
import base64

def main():
    try:
        if len(sys.argv) > 1:
            roomId = sys.argv[1]
        else :
            return "이미지를 읽을 수 없습니다"
        
        response = requests.get(f'http://localhost:8080/missing-persons/get-img?roomId={roomId}')

        # 얼굴 탐지기 로드 (Haar Cascades)
        face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

        # 이미지 읽기
        # 바이트 데이터를 OpenCV 이미지로 변환
        image_stream = BytesIO(response.content)
        image = np.asarray(bytearray(image_stream.read()), dtype=np.uint8)
        image = cv2.imdecode(image, cv2.IMREAD_COLOR)
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

        # 이미지가 정상적으로 로드되지 않았을 경우 예외 처리
        if image is None:
            print("이미지를 로드하지 못했습니다. 경로를 확인하세요.")
        else:
            # 그레이스케일로 변환 (Haar Cascades는 그레이스케일 이미지를 필요로 함)
            gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

            # 얼굴 탐지
            faces = face_cascade.detectMultiScale(gray_image, scaleFactor=1.1, minNeighbors=8, minSize=(50, 50))

            # 얼굴 탐지 결과 확인
            if len(faces) == 0:
                print("N")
            else:
                print("Y")
                

                # for (x, y, w, h) in faces:
                #     cv2.rectangle(image, (x, y), (x+w, y+h), (255, 0, 0), 2)

                # # 결과 이미지를 파일로 저장
                # result_image_path = 'sample/result_image.jpg'
                # cv2.imwrite(result_image_path, image)
                # print(f"탐지된 얼굴을 포함한 이미지를 {result_image_path} 파일로 저장했습니다.")
    except Exception as e:
        print(e)


if __name__ == "__main__":
    main()
