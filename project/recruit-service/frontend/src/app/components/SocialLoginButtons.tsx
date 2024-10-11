"use client";

import React, { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import styles from './SocialLoginButtons.module.css';

const SocialLoginButtons = () => {
  const router = useRouter();

  const handleLogin = (provider: string) => {
    const BASE_URL = process.env.NEXT_PUBLIC_BACK_PORT;
    window.location.href = `${BASE_URL}/oauth2/authorization/${provider}`;
  };

  useEffect(() => {
    const handleOAuthResponse = async () => {
      const currentUrl = window.location.href;

      if (currentUrl.includes('/auth/register')) {
        const url = new URL(currentUrl);
        const email = url.searchParams.get('email');

        if (email) {
          localStorage.setItem('email', email);
          router.push('/signup');
        } else {
          console.error('No email');
          router.push('/login');
        }
      }
    };

    handleOAuthResponse();
  }, [router]);

  return (
    <div className={styles['icon-box']}>
      <img
        src="/image/kakao-icon.png"
        alt="Kakao"
        onClick={() => handleLogin('kakao')}
      />
      <img
        src="/image/naver-icon.png"
        alt="Naver"
        onClick={() => handleLogin('naver')}
      />
      <img
        src="/image/google-icon.png"
        alt="Google"
        onClick={() => handleLogin('google')}
      />
    </div>
  );
};

export default SocialLoginButtons;
