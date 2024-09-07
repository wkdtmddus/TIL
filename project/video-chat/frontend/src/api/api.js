import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const api = axios.create({
    // 로컬
    // baseURL: 'http://localhost:5000',

    // 서버
    // baseURL: 'http://3.36.120.21:4040/api',
    baseURL: 'https://i11a207.p.ssafy.io/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

api.interceptors.request.use((config) => {
    const accessToken = localStorage.getItem('accessToken');
    const accessTokenexpirationTime = localStorage.getItem('accessTokenexpirationTime');
    config.headers.Authorization = `Bearer ${accessToken}`;
    return config;
});

export default api;
