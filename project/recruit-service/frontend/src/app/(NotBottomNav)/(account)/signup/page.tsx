'use client';

import styles from './signup.module.css';
import ImageUpload from '@/app/components/Input/ImageUpload';
import InputField from '@/app/components/Input/InputField';
import Button from '@/app/components/Button/Button';
import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';

export default function Signup() {
  const [nickname, setNickname] = useState('');
  const [nicknamed, setNicknamed] = useState('');
  const [isNicknameValid, setIsNicknameValid] = useState(false);
  const [email, setEmail] = useState('');
  const [image, setImage] = useState<File | null>(null);
  const defaultImage = '/image/add-img.png';
  const router = useRouter();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const queryEmail = params.get('email');

    if (queryEmail) setEmail(queryEmail);
  }, []);

  useEffect(() => {
    if (localStorage.getItem('nickname')) {
      router.push(`/home`);
    }
  }, []);

  const handleNicknameCheck = async () => {
    try {
      const response = await axios.get(`${process.env.NEXT_PUBLIC_BACK_PORT}/auth/check-nickname`, {
        params: { nickname },
      });

      if (!response.data) {
        setIsNicknameValid(true);
        setNicknamed(nickname);
        alert('사용 가능한 닉네임입니다.');
      } else {
        setIsNicknameValid(false);
        alert('이미 사용 중인 닉네임입니다.');
      }
    } catch (error) {
      console.error('Error checking nickname:', error);
    }
  };

  const handleSignup = async () => {
    if (!isNicknameValid) {
      alert('닉네임 중복확인을 해주세요.');
      return;
    }

    if (isNicknameValid) {
      if (nickname !== nicknamed) {
        alert('닉네임을 확인하세요.');
        return;
      }

      try {
        const formData = new FormData();

        const signupRequest = JSON.stringify({
          nickname: nickname,
          email: email,
        });
        formData.append('oauth2SignupRequest', new Blob([signupRequest], { type: 'application/json' }));

        if (image) {
          formData.append('userImg', image);
        } else {
          const defaultImageBlob = await fetch(defaultImage).then(res => res.blob());
        formData.append('userImg', defaultImageBlob, 'default-image.png');
        }

        const response = await axios.post(`${process.env.NEXT_PUBLIC_BACK_PORT}/auth/users`, formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        });

        const accessToken = response.headers['authorization'];
        const refreshToken = response.headers['refreshtoken'];
        localStorage.setItem("Authorization", accessToken);
        localStorage.setItem("refreshtoken", refreshToken);
        localStorage.setItem("nickname", nickname);
        localStorage.setItem("email", email);

        router.push(`/home`);
      } catch (error) {
        console.error('Error during signup:', error);
        alert('회원가입 중 문제가 발생했습니다.');
      }
    } else {
      alert('닉네임을 입력하세요.');
    }
  };

  return (
    <div className={styles.signup}>
      <div className={styles.title}>
        <img src='/image/logo-profile.png' alt='logo' />
        <h1>프로필</h1>
      </div>
      <h3 className={styles['text-middle']}>LineUp!에서 활동할 프로필을 등록해 보세요.</h3>

      {/* Image Upload */}
      <ImageUpload setImage={setImage} />

      {/* Nickname Input */}
      <div className={styles.inputField}>
        <InputField
          label='닉네임 설정'
          placeholder='특수문자를 제외한 10자 이내'
          required
          errorMessage='닉네임을 입력해 주세요.'
          value={nickname}
          onChange={(value => setNickname(value))}
        />
        <div className={styles.button}>
          <Button
            label='중복확인'
            type='button'
            onClick={handleNicknameCheck}
            disabled={!nickname}
            style={{
              backgroundColor: '#212732',
              color: 'white',
              fontSize: '14px',
              padding: 0,
              fontWeight: 400,
              height: '40px',
            }}
          />
        </div>
      </div>

      {/* Signup Button */}
      <div className={styles.submitButton}>
        <Button
          label='등록하기'
          type='button'
          onClick={handleSignup}
          disabled={!isNicknameValid}
          style={{
            fontSize: '18px',
            borderRadius: '8px',
          }}
        />
      </div>
    </div>
  );
}
