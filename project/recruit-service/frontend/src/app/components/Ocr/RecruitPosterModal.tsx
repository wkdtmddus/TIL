import React from "react";
import styles from "./RecruitPosterModal.module.css"; // Your existing styles

// Modal component to show during upload
const RecruitPosterModal: React.FC<{ isOpen: boolean; onClose: () => void }> = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className={styles.modalOverlay}>
      <div className={styles.modalContent}>
        <div className={styles.spinner}></div> {/* Loading spinner */}
        <p>AI가 분석 중...</p>
        <button onClick={onClose} className={styles.closeButton}>✕</button>
      </div>
    </div>
  );
};

export default RecruitPosterModal;
