import axios from 'axios';

const apiImg = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BACK_PORT,
  headers: {
   'Content-Type': 'multipart/form-data',
  },
});


apiImg.interceptors.request.use((config) => {
  if (typeof window !== 'undefined') {
    const accessToken = localStorage.getItem('Authorization');
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
  }
  return config;
});

apiImg.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem('refreshtoken');
        const response = await axios.post(
          `${process.env.NEXT_PUBLIC_BACK_PORT}/auth/refresh`,
          { refreshToken }
        );

        const newAccessToken = response.data.accessToken;
        localStorage.setItem('Authorization', newAccessToken);

        originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
        return apiImg(originalRequest);
      } catch (refreshError) {
        console.error('토큰 갱신 실패:', refreshError);
        alert('로그인을 다시 해주세요.');
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default apiImg;
