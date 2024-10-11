import React, { useState } from 'react';
import styles from './TimePickerDrawer.module.css';

interface TimePickerDrawerProps {
  onClose: () => void;
  onSelect: (time: string) => void;
}

const times = [
  "09:00", "09:10", "09:20", "09:30", "09:40", "09:50",
  "10:00", "10:10", "10:20", "10:30", "10:40", "10:50",
  "11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
  // 추가 시간대...
];

const TimePickerDrawer: React.FC<TimePickerDrawerProps> = ({ onClose, onSelect }) => {
  return (
    <div className={styles.drawerBackground}>
      <div className={styles.drawerContainer}>
        <div className={styles.header}>
          <button onClick={onClose}>닫기</button>
          <h3>시간 선택</h3>
        </div>
        <div className={styles.timeList}>
          {times.map((time, index) => (
            <div
              key={index}
              className={styles.timeItem}
              onClick={() => onSelect(time)}
            >
              {time}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default TimePickerDrawer;
