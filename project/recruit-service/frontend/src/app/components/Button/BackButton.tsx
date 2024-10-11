// components/BackButton.tsx
"use client";

import React from 'react';
import { useRouter } from 'next/navigation';
import styles from './BackButton.module.css'; // 스타일 파일 추가

interface BackButtonProps {
  imageSrc?: string; // 이미지 경로를 받아오는 Prop
  altText?: string; // 이미지의 대체 텍스트
}

const BackButton: React.FC<BackButtonProps> = ({imageSrc, altText = "Back" }) => {
  const router = useRouter();

  const handleBack = () => {
    router.back(); // 뒤로 가기 기능
  };

  return (
    <button className={styles.closeButton} onClick={handleBack}>
        <img src={imageSrc} alt={altText} className={styles.icon} />
    </button>
  );
};

export default BackButton;
