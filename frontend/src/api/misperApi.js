import apiController from "./apiController";

// misper: 실종자
const misperApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    /**
     * @param {object} request - uid: String (from URL)
     * @returns {object} name: String, place: String, age: Number, clothes: String, face: String, sex: Number,  image_urls: Array[String]
     */
    getMisperInfo: async param => {
        return {
            name: '홍길동',
            place: '서울시 강남구 삼성동',
            age: 72,
            clothes: '빨간 상의, 검정 긴바지',
            face: '무쌍, 사각턱, 매부리코',
            sex: 1, // 1: 남자, 2: 여자
            image_urls: [
                'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Fphotos%2Fbeautiful-cat&psig=AOvVaw3dmsL9GqCY1iuzWLUac47G&ust=1721658498828000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOi1tOuruIcDFQAAAAAdAAAAABAJ',
                'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2Fcute-and-playful-3d-cat-wallpapers--207869339043705172%2F&psig=AOvVaw3dmsL9GqCY1iuzWLUac47G&ust=1721658498828000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOi1tOuruIcDFQAAAAAdAAAAABAZ',
                'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.wfla.com%2Fbloom-tampa-bay%2F10-surprising-benefits-of-having-a-cat-in-your-life%2F&psig=AOvVaw3dmsL9GqCY1iuzWLUac47G&ust=1721658498828000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOi1tOuruIcDFQAAAAAdAAAAABAh'
            ],
        };
    },
}

export default misperApi;