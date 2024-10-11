import Link from 'next/link';
import './globals.css';
import styles from './main.module.css';
import NotificationComponent from './components/NotificationComponent ';

export default function Main() {
  return (
    <div className={styles.main}>
      <div className={styles.banner}>
        <div className={styles.textContainer}>
          <h1 className={styles.mainTitle}>오픈런 알바는 지금,</h1>
          <h1 className={styles.title}>라인업</h1>
        </div>
        <div className={styles.character}>
          <img src="/image/mainbanner.png" alt="캐릭터" />
        </div>
      </div>

      <Link href="/home">
        <div className={styles.button}>
          <span className={styles.buttonText}>바로가기 →</span>
        </div>
      </Link>
    </div>
  );
}
