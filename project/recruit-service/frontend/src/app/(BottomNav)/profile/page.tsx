"use client";

import { useState, useEffect } from 'react';
import styles from '@/app/(BottomNav)/profile/profile.module.css';
import api from '../../../../pages/api/api';

interface CardData {
  id: number;
  title: string;
  description: string;
}

interface UserProfile {
  nickname: string;
  profileImg: string;
}

export default function Profile() {
  const [activeTab, setActiveTab] = useState('관심목록');
  const [interestList, setInterestList] = useState<CardData[]>([]);
  const [postList, setPostList] = useState<CardData[]>([]);
  const [applicationList, setApplicationList] = useState<CardData[]>([]);
  const [userProfile, setUserProfile] = useState<UserProfile | null>(null);
  const [imageError, setImageError] = useState(false); // Track if the profile image fails to load

  useEffect(() => {
    setUserProfile({
      nickname: localStorage.getItem('nickname') || '',
      profileImg: localStorage.getItem('profileImg') || '/image/add-img.png'
    });

    fetchData();

  }, []);

  // Fetch other data (interest, posts, applications) using the custom API instance
  const fetchData = async () => {
    try {
      const interestData = await api.get('/api/interest');
      const postData = await api.get('/api/posts');
      const applicationData = await api.get('/api/applications');

      setInterestList(interestData.data);
      setPostList(postData.data);
      setApplicationList(applicationData.data);
    } catch (error) {
      console.error('Failed to fetch data:', error);
    }
  };

  const renderCards = () => {
    let dataList: CardData[] = [];
    if (activeTab === '관심목록') dataList = interestList;
    if (activeTab === '공고내역') dataList = postList;
    if (activeTab === '대행내역') dataList = applicationList;

    return (
      <div className={styles.cardContainer}>
        {dataList.length > 0 ? (
          dataList.map((item) => (
            <div key={item.id} className={styles.card}>
              <h3>{item.title}</h3>
              <p>{item.description}</p>
            </div>
          ))
        ) : (
          <p className={styles.emptyMessage}>데이터가 없습니다.</p>
        )}
      </div>
    );
  };

  return (
    <div className={styles.mypageContainer}>
      <div className={styles.header}>
        <div className={styles.profileSection}>
          {/* Profile Image */}
          {userProfile ? (
            <img
              src={imageError ? '/image/add-img.png' : userProfile.profileImg} // Load the new fallback image if error
              alt="Profile"
              className={styles.profileImage}
              onError={() => setImageError(true)} // If image fails, show the new fallback
            />
          ) : (
            // Show grey circle if userProfile isn't loaded
            <div className={styles.defaultProfile}></div>
          )}

          <span className={styles.nickname}>
            {userProfile ? userProfile.nickname : 'Loading...'}
          </span>
        </div>
      </div>

      <div className={styles.historyCards}>
        <div className={styles.card}>
          <h4>공고이력</h4>
          <p>라인업 공고 완료 횟수 0</p>
          <p>라인업 공고 완료 금액 0원</p>
        </div>
        <div className={styles.card}>
          <h4>대행이력</h4>
          <p>라인업 대행 완료 횟수 0</p>
          <p>라인업 대행 완료 금액 0원</p>
        </div>
      </div>

      <div className={styles.tabNavigation}>
        <button
          className={`${styles.tabButton} ${activeTab === '관심목록' ? styles.activeTab : ''}`}
          onClick={() => setActiveTab('관심목록')}
        >
          관심목록
        </button>
        <button
          className={`${styles.tabButton} ${activeTab === '공고내역' ? styles.activeTab : ''}`}
          onClick={() => setActiveTab('공고내역')}
        >
          공고내역
        </button>
        <button
          className={`${styles.tabButton} ${activeTab === '대행내역' ? styles.activeTab : ''}`}
          onClick={() => setActiveTab('대행내역')}
        >
          대행내역
        </button>
      </div>

      <div className={styles.tabContent}>{renderCards()}</div>
    </div>
  );
}
