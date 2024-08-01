import axios from 'axios';

// 기본 URL 설정
const instance = axios.create({
    baseURL: '', // 백엔드 API 기본 URL
});

export default instance;