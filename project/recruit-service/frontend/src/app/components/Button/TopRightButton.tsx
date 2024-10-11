// components/TopLeftButton.tsx
"use client";

import React, { useState } from "react";
import styles from './TopRightButton.module.css';
import { useRouter } from 'next/navigation';

interface TopRightButtonProps {
    imageRightSrc: string; // Image source for the button
    altText: string; // Alternate text for the image
    onModify: () => void; // Function to call on modify
    onRequestCancel: () => void; // Function to call on request cancel
  }
  
  const TopRightButton: React.FC<TopRightButtonProps> = ({ 
    imageRightSrc, 
    altText = "Menu", 
    onModify,
    onRequestCancel 
  }) => {
    const [dropdownVisible, setDropdownVisible] = useState(false); // Manage dropdown visibility
    const router = useRouter();
  
    // Toggle the visibility of the dropdown menu
    const toggleDropdown = () => {
      setDropdownVisible(!dropdownVisible);
    };
  
    return (
      <div className={styles.topRightButtonWrapper}>
        <button className={styles.menuButton} onClick={toggleDropdown}>
          {imageRightSrc && <img src={imageRightSrc} alt={altText} className={styles.icon} />}
        </button>
        
        {dropdownVisible && (
          <div className={styles.dropdownMenu}>
            <button 
              className={styles.dropdownItem} 
              onClick={() => {
                setDropdownVisible(false);
                onModify();
              }}
            >
              수정하기
            </button>
            <button 
              className={styles.dropdownItem} 
              onClick={() => {
                setDropdownVisible(false);
                onRequestCancel();
              }}
            >
              취소 요청
            </button>
          </div>
        )}
      </div>
    );
  };
  
  export default TopRightButton;