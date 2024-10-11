import React from 'react';
import styles from './KakaoMapButton.module.css';
import '@/app/typography.css'

interface KakaoMapButtonProps {
  onClick: () => void;
}

const KakaoMapButton: React.FC<KakaoMapButtonProps> = ({ onClick }) => {
  return (
    <button onClick={onClick} className={styles.mapButton}>
      <img src="/image/location-active-icon.png" alt="Map Icon" className={styles.icon} />
      <div className={styles.textContainer}>
        <span className='text-medium'>라인업 지도 보기</span>
        <img src="/image/chevron-icon.png" alt="Map Icon" className={styles.icon2} />
      </div>
    </button>
  );
};

export default KakaoMapButton;
