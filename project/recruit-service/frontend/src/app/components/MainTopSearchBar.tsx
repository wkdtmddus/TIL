// MainTopSearchBar.tsx

'use client';

import { useRouter } from 'next/navigation'; // Corrected import
import styles from "./MainTopBar.module.css";

export default function TopSearchBar() {
  const router = useRouter();

  const handleSearchClick = () => {
    router.push('/recruit/search');
  };

  return (
    <div className="">
      <div>
        <div className={styles.TopBar}>
          <div className={styles.logoimage}>
            <img src="/image/logo.png" alt="Logo" />
          </div>
          <div className={styles.searchContainer} onClick={handleSearchClick}>
            <input
              className={styles.TopBarInput}
              type="text"
              placeholder="어떤 줄서기를 하고 싶은가요?"
            />
            <img src="/image/search-icon.png" alt="Search Icon" className={styles.searchIcon} />
          </div>
          <div className={styles.bellimage}>
            <img src="/image/bell.png" alt="Notifications" />
          </div>
        </div>
      </div>
    </div>
  );
}
