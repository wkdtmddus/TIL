import React from 'react';
import styles from './LoadingSpinner.module.css';

const LoadingSpinner: React.FC = () => {
  return (
    <div className={styles.spinnerContainer}>
      <div className={styles.spinner}></div>
      <p className={styles.loadingMessage}>Loading...</p>
    </div>
  );
};

export default LoadingSpinner;
