"use client";

import React from 'react';
import Link from 'next/link';
import styles from './FabButton.module.css';

interface FabButtonProps {
  href: string; // 링크 정보를 받아올 수 있도록 설정
}

const FabButton: React.FC<FabButtonProps> = ({ href }) => {
  return (
    <Link href={href}>
      <button className={styles.fab}>+</button>
    </Link>
  );
};

export default FabButton;
