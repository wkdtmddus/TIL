import React, { useState } from "react";
import styles from "./TopNav.module.css";
import { useRouter } from 'next/navigation';

interface TopNavProps {
  title: string;
}

const TopNav: React.FC<TopNavProps> = ({ title }) => {
  const router = useRouter();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => {
    setIsMenuOpen((prev) => !prev);
  };

  return (
    <div className={styles.topContainer}>
      <div className={styles.TopNav}>
        <div className={styles.start} onClick={() => router.back()}>
          <img src="/image/back2-icon.png" alt="back" />
        </div>
        <div className={styles.mid}>
          <div className={styles.midText}>
            <p>{title}</p>
          </div>
        </div>
        <div className={styles.end} onClick={toggleMenu}>
          <img src="/image/ellipsis.png" alt="menu" />
        </div>
      </div>

      {isMenuOpen && (
        <div className={styles.menu}>
          <div className={styles.menuItem}>알림끄기</div>
          <div className={styles.menuItem}>신고하기</div>
          <div className={styles.menuItem}>차단하기</div>
          <div className={styles.menuItemExit}>대화방 나가기</div>
        </div>
      )}
    </div>
  );
};

export default TopNav;
