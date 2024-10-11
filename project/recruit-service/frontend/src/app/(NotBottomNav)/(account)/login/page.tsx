import SocialLoginButtons from '@/app/components/SocialLoginButtons';
import styles from './login.module.css';

export default function Login() {
  return (
    <div className={styles.background}>
      <div className={styles['text-box']}>
        <h1>오픈런 알바,</h1>
        <h1>줄서기 대행은 지금</h1>
        <img src='/image/logo-login.png' alt='Logo' />
      </div>
      <div className={styles.logo}></div>
      <p className={styles['text-middle']}>계속하려면 로그인 하세요.</p>
      
      <SocialLoginButtons />

      <div className={styles['text-bottom']}>
        로그인 시 <a href="/terms">이용 약관</a> 및 <a href="/privacy">개인정보 보호정책</a>에 동의합니다.
      </div>
    </div>
  );
}
