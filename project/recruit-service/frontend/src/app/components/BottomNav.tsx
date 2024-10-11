// components/BottomNav.tsx
"use client";

import { usePathname } from 'next/navigation';
import styles from './BottomNav.module.css';
import Link from 'next/link';

export default function BottomNav() {
  const pathname = usePathname(); // 현재 경로를 가져옴

  // 경로와 활성/비활성 이미지 경로를 관리하는 객체
  const navItems = [
    {
      href: '/chat',
      label: '채팅',
      activeImg: '/image/menu_active_message.png',
      inactiveImg: '/image/menu_inactive_message.png',
    },
    {
      href: '/home',
      label: '홈',
      activeImg: '/image/menu_active_house.png',
      inactiveImg: '/image/menu_inactive_house.png',
    },
    {
      href: '/profile',
      label: '마이페이지',
      activeImg: '/image/menu_active_person.png',
      inactiveImg: '/image/menu_inactive_person.png',
    },
  ];

  return (
    <nav className={styles.navbar}>
      {/* map 함수로 navitems를 item으로 뺌 */}
      {navItems.map((item) => (
        // 스타일 navitem쓰고, pathname이랑 주소랑 비교해서 active css넣기
        <Link key={item.href} href={item.href} className={`${styles.navItem} ${pathname === item.href ? styles.active : ''}`}>
          <span>
            <img
              src={pathname === item.href ? item.activeImg : item.inactiveImg}
              alt={item.label}
              className={styles.icon}
            />
          </span>
          <span>{item.label}</span>
        </Link>
      ))}
    </nav>
  );
}
