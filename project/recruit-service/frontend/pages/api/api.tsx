import axios from 'axios';
import { headers } from 'next/headers';

const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BACK_PORT,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use((config) => {
  if (typeof window !== 'undefined') {
    const accessToken = localStorage.getItem('Authorization');
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
  }
  return config;
});

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem('refreshtoken');
        const response = await axios.get(
          `${process.env.NEXT_PUBLIC_BACK_PORT}/auth/refresh`,
          {
            headers: {
              refreshToken: `Bearer ${refreshToken}`,
              'Content-Type': 'application/json',
            },
          }
        );
        
        const newAccessToken = response.data.Authorization;
        const newRefreshToken = response.data.refreshToken;
        localStorage.setItem('Authorization', newAccessToken);
        localStorage.setItem('refreshToken', newRefreshToken);

      } catch (refreshError) {
        console.error('토큰 갱신 실패:', refreshError);
        alert('로그인을 다시 해주세요.');
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default api;
