'use client';

import { useState, useRef, useEffect } from 'react';
import styles from './home.module.css';
import "../../typography.css";
import MainTopBar from '../../components/MainTopBar';
import Card from '../../components/MainCardList';
import FabButton from '../../components/Button/FabButton';
import Link from 'next/link';
import axios from "axios";
import api from '../../../../pages/api/api';

type RecruitData = {
  id: number;
  title: string;
  successSalary: number;
  startDate: string;
  endDate: string;
  startAt: string;
  endAt: string;
  likeCount: number;
  viewCount: number;
  streetAddress: string;
  recruitImgUrl: string;
  // 필요한 모든 필드 추가
};

export default function Home() {
  const [isSearchActive, setIsSearchActive] = useState(false);
  const [recentSearches, setRecentSearches] = useState<string[]>(['검색어 1', '검색어 2']);
  const searchInputRef = useRef<HTMLInputElement>(null);
  const [recruitList, setRecruitList] = useState<RecruitData[]>([]);

  const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;

  useEffect(() => {
    // 임시 토큰을 쿠키에서 추출
    const getCookie = (name: string) => {
      const value = `; ${document.cookie}`;
      // console.log(value);
      const parts = value.split(`; ${name}=`);

      if (parts.length === 2) {
        const result = parts.pop();
        return result ? result.split(';').shift() : undefined;
      }
    };

    const tempToken = getCookie("Authorization");
    // console.log(tempToken);
    if (tempToken) {
      // /auth/token 엔드포인트로 임시 토큰을 사용하여 Access/Refresh Token 요청
      axios.get(`${baseURL}/auth/token`, {
        headers: {
          Authorization: `Bearer ${tempToken}`,
        },
      })
        .then(response => {
          // 서버로부터 새로운 Access Token과 Refresh Token을 수신
          const accessToken = response.headers['authorization'];
          const refreshToken = response.headers['refreshtoken'];

          // 세션 스토리지에 저장
          localStorage.setItem('Authorization', accessToken);
          localStorage.setItem('refreshToken', refreshToken);

          // console.log('Access Token:', accessToken);
          // console.log('Refresh Token:', refreshToken);
        })
        .catch(error => {
          console.error('토큰 재발급 중 에러 발생:', error);
        });
    }
    fetchUserData();
  }, []);

  const fetchUserData = async () => {
    try {
      const response = await api.get('/auth/userInfo');


      const userData = response.data;
      console.log(response.data);
      localStorage.setItem('userId', userData.userId);
      localStorage.setItem('email', userData.email);
      localStorage.setItem('profileImg', userData.profileImg || '/image/add-img.png');
      localStorage.setItem('nickname', userData.nickname);
    } catch (error) {
      console.log('failed userdata fetching', error);
    }
  };

  useEffect(() => {
    const fetchRecruits = async () => {
      try {
        const response = await api.get('/recruits/list');
        console.log(response.data);
        setRecruitList(response.data);
      } catch (error) {
        console.log('recruits fetching error', error);
      }
    };
    fetchRecruits();
  }, []);

  const handleInputFocus = () => {
    setIsSearchActive(true);
    searchInputRef.current?.focus(); // Focus the input to open the keyboard
  };

  const handleBackClick = () => setIsSearchActive(false);

  return (
    <div>
      <MainTopBar onFocus={handleInputFocus} onBack={handleBackClick} searchInputRef={searchInputRef} />

      {isSearchActive && (
        <>
          <hr className={styles.divideline}></hr>
          <div className={styles.searchContainer}>
            <div className={styles.searchButtons}>
              <p>최근 검색어</p>
              {recentSearches.length > 0 ? (
                recentSearches.map((search, index) => <p key={index}>{search}</p>)
              ) : (
                <p>최근 검색어가 없습니다.</p>
              )}
            </div>
          </div>
        </>
      )}

      {!isSearchActive && (
        <>
          <div className={styles.banner}>
            <div className={styles.textContainer}>
              <p className={`text-medium ${styles.textWithMargin}`}></p>
              <h1 className="text-xlarge">
                오픈런 알바는 지금,
                <br />
                <span className="text-xxlarge">라인업</span>
              </h1>
            </div>
            <img src="/image/mainbanner.png" alt="캐릭터" className={styles.character} />
          </div>
          <div className={styles.cardContainer}>
            {recruitList.map((recruit) => (
              <Link href={`/recruit/${recruit.id}`} key={recruit.id} style={{ textDecoration: "none" }}>
                <Card
                  title={recruit.title}
                  price={recruit.successSalary}
                  likes={recruit.likeCount}
                  views={recruit.viewCount}
                  date={recruit.startDate}
                  location={recruit.streetAddress}
                  recruitImgUrl={recruit.recruitImgUrl}
                />
              </Link>
            ))}
          </div>
          <FabButton href="/recruit" />
        </>
      )}
    </div>
  );
}
