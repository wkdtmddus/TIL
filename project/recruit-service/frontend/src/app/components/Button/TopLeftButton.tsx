// components/TopLeftButton.tsx
"use client";

import React from 'react';
import styles from './TopLeftButton.module.css';
import { useRouter } from 'next/navigation';

interface TopLeftButtonProps {
  imageSrc: string; // 이미지 경로를 받아오는 Prop
  altText: string; // 이미지의 대체 텍스트
  href: string; // 이동할 경로
  navigateType: 'replace' | 'push' | 'not'; // 이동 방식 선택
  onClick?: () => void; // 추가적인 onClick 이벤트 핸들러
}

const TopLeftButton: React.FC<TopLeftButtonProps> = ({ 
  imageSrc, 
  altText = "Back", 
  href, 
  navigateType = 'push', // 기본값으로 'push' 설정
  onClick 
}) => {
  
  const router = useRouter();

  const handleClick = () => {
    if (onClick) {
      onClick(); // 추가적인 onClick 함수 호출
    }
    
    // navigateType에 따라 라우터 이동 방식 결정
    switch (navigateType) {
      case 'not':
        break;
      case 'replace':
        router.replace(href); // 히스토리 대체
        break;
      case 'push':
      default:
        router.push(href); // 일반적인 이동
        break;
    }
  };

  return (
    <button className={styles.closeButton} onClick={handleClick}>
      {imageSrc && <img src={imageSrc} alt={altText} className={styles.icon} />}
    </button>
  );
};

export default TopLeftButton;
