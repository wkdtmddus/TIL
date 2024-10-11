import React, { useState, useEffect } from 'react';
import styles from './AddressSearchModal.module.css';
import { useAnnouncementStore } from '@/app/store/useAnnouncementStore';
import Header from '@/app/components/Header/Header';
import '@/app/typography.css';

interface AddressSearchResponse {
  roadAddr: string;
  jibunAddr: string;
  zipNo: string;
}

interface AddressSearchModalProps {
  onClose: () => void;
}

declare global {
  interface Window {
    kakao: any; // Kakao 지도 객체를 any 타입으로 선언
  }
}

const AddressSearchModal: React.FC<AddressSearchModalProps> = ({ onClose }) => {
  const [keyword, setKeyword] = useState('');
  const [results, setResults] = useState<AddressSearchResponse[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [kakaoLoaded, setKakaoLoaded] = useState(false); // Kakao SDK 로드 확인

  // Zustand actions to update the address, latitude, and longitude
  const { setAddress, setLatitude, setLongitude, setDistrict } = useAnnouncementStore();
  const kakaokey = process.env.NEXT_PUBLIC_KAKAO_API_KEY
  useEffect(() => {
    // Kakao 지도 SDK 로드
    const loadKakaoSDK = () => {
      if (document.querySelector('script[src="//dapi.kakao.com/v2/maps/sdk.js"]')) {
        // 이미 스크립트가 로드되어 있다면 바로 설정
        setKakaoLoaded(true);
        return;
      }

      console.log('map');
      const script = document.createElement('script');
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${kakaokey}&libraries=services`;
      script.async = true;

      script.onload = () => {
        console.log('Kakao SDK Loaded');
        window.kakao.maps.load(() => {
          setKakaoLoaded(true); // Kakao SDK가 로드되었음을 표시
        });
      };

      // 스크립트 로드 실패 확인
      script.onerror = () => {
        console.error('Kakao SDK 로드에 실패했습니다.');
        setError('Kakao SDK 로드에 실패했습니다.');
      };

      document.body.appendChild(script);
    };

    loadKakaoSDK();
  }, []); // 한 번만 실행되도록 빈 배열로 설정

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
      console.log(data);
      setResults(data);
    } catch (err) {
      setError('주소 데이터를 불러오는데 실패했습니다');
      console.error('주소 데이터를 불러오는데 실패했습니다:', err);
    } finally {
      setLoading(false);
    }
  };

  // 엔터 키 입력 시 검색 실행
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      event.preventDefault(); // 폼 제출 등 기본 동작을 막음
      handleSearch(); // 검색 실행
    }
  };

  const handleAddressSelect = (selectedAddress: string) => {
    if (!kakaoLoaded) {
      console.error('Kakao SDK가 아직 로드되지 않았습니다.');
      return;
    }

    // Kakao Geocoder로 위도와 경도를 변환
    const geocoder = new window.kakao.maps.services.Geocoder();

    geocoder.addressSearch(selectedAddress, (result: any, status: any) => {
      if (status === window.kakao.maps.services.Status.OK) {
        const latitude = result[0].y;
        const longitude = result[0].x;
        const district = result[0].address.region_2depth_name
        
        console.log(selectedAddress);
        console.log(latitude);
        console.log(longitude);
        console.log(district);

        // Update Zustand state with the selected address, coordinates, and district
        setAddress(selectedAddress);
        setLatitude(latitude);
        setLongitude(longitude);
        setDistrict(district); // 구 정보 업데이트

        // 모달 닫기
        onClose();
      } else {
        console.error('좌표 변환 실패:', status);
      }
    });
  };

  return (
    <div className={styles.modalBackground}>
      <div className={styles.modalContainer}>
        <Header
          imagesSrc="/image/back-icon.png"
          altText="뒤로 가기"
          href="/announcement"
          navigateType="not"
          title="위치 검색"
          onClick={onClose}
        />
        <hr className={styles.divideline}></hr>
        <div className={styles.searchContainer}>
          <div className={styles.inputContainer}>
            <input
              type="text"
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
              onKeyDown={handleKeyDown} // 엔터 키 감지
              placeholder="도로명, 건물명, 번지 검색"
              className={styles.searchInput}
            />
            <button onClick={handleSearch} className={styles.button} disabled={loading}>
              {loading ? '검색 중...' : '검색'}
            </button>
          </div>
        </div>

        {error && <p className={styles.error}>{error}</p>}

        <div className={styles.results}>
          {results.length > 0 ? (
            results.map((item, index) => (
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
            ))
          ) : (
            <div className={styles.examples}>
              <p className={styles.textMarginBottom}>이렇게 검색해보세요</p>
              <br />
              <p className="text-large">도로명 + 건물번호</p>
              <p className={styles.exampleItem}>예) 숲속마을 1로 85</p>
              <br />
              <p className="text-large">동/읍/면/리 + 번지</p>
              <p className={styles.exampleItem}>예) 마북동 630</p>
              <br />
              <p className="text-large">건물명, 아파트명</p>
              <p className={styles.exampleItem}>예) 삼성 래미안</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AddressSearchModal;
