// MainTopBar.tsx
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import styles from "./MainTopBar.module.css";

interface TopBarProps {
    onFocus: () => void;
    onBack: () => void;
    searchInputRef: React.RefObject<HTMLInputElement>;
}

export default function TopBar({ onFocus, onBack, searchInputRef }: TopBarProps) {
    const router = useRouter();
    const [isSearchActive, setIsSearchActive] = useState(false);

    // Handle focus event for input
    const handleFocus = () => {
        setIsSearchActive(true);
        onFocus();
    };

    // Handle back button click to exit search
    const handleBackClick = () => {
        setIsSearchActive(false);
        onBack(); // Notify Home component to show announcements
    };

    return (
        <div className={styles.TopBar}>
            {!isSearchActive ? (
                // Default state with logo and bell icon
                <>
                    <div className={styles.logoimage}>
                        <img src="/image/logo.png" alt="Logo" />
                    </div>
                    <div className={styles.searchContainer}>
                        <input
                            className={styles.TopBarInput}
                            type="text"
                            placeholder="어떤 줄서기를 하고 싶은가요?"
                            onFocus={handleFocus}
                        />
                        <img src="/image/search-icon.png" alt="Search Icon" className={styles.searchIcon} />
                    </div>
                    <div className={styles.bellimage}>
                        <img src="/image/bell.png" alt="Notifications" />
                    </div>
                </>
            ) : (
                // Search active state with back button and full-width input
                <>
                    <button onClick={handleBackClick} className={styles.backButton}>
                        <img src="/image/back2-icon.png" alt="뒤로가기" />
                    </button>
                    <div className={styles.fullWidthSearchContainer}>
                        <input
                            className={styles.fullWidthInput}
                            type="text"
                            placeholder="어떤 줄서기를 하고 싶은가요?"
                            onFocus={onFocus}
                            ref={searchInputRef}
                        />
                    </div>
                </>
            )}
        </div>
    );
}
