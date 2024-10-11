"use client";

import React, { useState } from 'react';
import styles from './styles.module.css';
import { useAnnouncementStore } from '@/app/store/useAnnouncementStore';
import '@/app/typography.css';
import { useRouter } from 'next/navigation';
import TopLeftButton from '@/app/components/Button/TopLeftButton';
import Header from '@/app/components/Header/Header';

interface AddressSearchResponse {
  roadAddr: string;
  jibunAddr: string;
  zipNo: string;
}

const AddressSearch: React.FC = () => {
  const [keyword, setKeyword] = useState('');
  const [results, setResults] = useState<AddressSearchResponse[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const setAddress = useAnnouncementStore((state) => state.setAddress);
  const router = useRouter();

  const handleSearch = async () => {
    if (!keyword.trim()) {
      setError('검색어를 입력해주세요');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const response = await fetch(`/api/address-search?keyword=${encodeURIComponent(keyword)}`);
      const data: AddressSearchResponse[] = await response.json();
      console.log(data)
      setResults(data);
    } catch (err) {
      setError('주소 데이터를 불러오는데 실패했습니다');
      console.error('주소 데이터를 불러오는데 실패했습니다:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleAddressSelect = (selectedAddress: string) => {
    setAddress(selectedAddress);
    router.push('/announcement'); // Navigate back to the announcement page
  };

  return (
    <div>
      <Header
        imagesSrc="/image/back2-icon.png"
        altText="홈으로 가기"
        href="/announcement"
        navigateType='replace'
        title='위치 검색'
      >
      </Header>
      <hr className={styles.divideline}></hr>
      <div className={styles.container}>
        <div className={styles.inputContainer}>
          <input
            type="text"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            placeholder="도로명, 건물명, 번지 검색"
            className={styles.searchInput}
          />
          <button onClick={handleSearch} className={styles.button} disabled={loading}>
            {loading ? '검색 중...' : '검색'}
          </button>
        </div>

        {error && <p className={styles.error}>{error}</p>}

        {/* 결과가 없을 때만 예시를 보여줌 */}
        {results.length === 0 && (
          <div className={styles.examples}>
            <p className={styles.textMarginBottom}>이렇게 검색해보세요</p>
            <br />
            <p className='text-large'>도로명 + 건물번호 </p>
            <p className={styles.exampleItem}>예) 숲속마을 1로 85</p>
            <br />
            <p className='text-large'>동/읍/면/리 + 번지 </p>
            <p className={styles.exampleItem}>예) 마북동 630</p>
            <br />
            <p className='text-large'>건물명, 아파트명 </p>
            <p className={styles.exampleItem}>예) 삼성 래미안</p>
          </div>
        )}

        <div className={styles.results}>
          {results.map((item, index) => (
            <div key={index} className={styles.resultItem} onClick={() => handleAddressSelect(item.roadAddr)}>

              <strong>{item.zipNo}</strong>
              <br />
              <div className={styles.roadAddr}>
                <div className={styles.roadAddrBox}>도로명</div>
                <div className={styles.roadAddrInfo}>{item.roadAddr}</div>
              </div>
              <br />
              <div className={styles.jibunAddr}>
                <div className={styles.jibunAddrBox}>지번</div>
                <div className={styles.jibunAddrInfo}>{item.jibunAddr}</div>
              </div>

            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default AddressSearch;
