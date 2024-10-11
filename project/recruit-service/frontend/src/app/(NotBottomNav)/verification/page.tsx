'use client'
import React, { useState, useEffect } from "react";
import { useAnnouncementStore } from '@/app/store/useAnnouncementStore';
import { useRouter } from 'next/navigation';
import Header from "@/app/components/Header/Header";
import '@/app/typography.css';
import styles from '@/app/(NotBottomNav)/verification/verification.module.css';
import '@/app/typography.css';
import line from '../../../../public/image/line.png';

const categories = [
  { name: '백화점', icon: '/image/drop-icon.png', activeIcon: '/image/drop-active-icon.png' },
  { name: '음식점', icon: '/image/drop2-icon.png', activeIcon: '/image/drop2-active-icon.png' },
  { name: '팝업스토어', icon: '/image/drop3-icon.png', activeIcon: '/image/drop3-active-icon.png' },
  { name: '카페', icon: '/image/drop4-icon.png', activeIcon: '/image/drop4-active-icon.png' },
];

export default function Verification() {
  const user = {
    nickname: '우디',
    title: '대장동 백화점 줄서기',
    price: '일급 200,000원',
    date: '2024.10.11(금)',
    startTime: '18:00',
    lastTime:'21:00',
    imageSrc: '/image/add-img.png',
    address: '맥도날드 서초점',
    addressDetail: '도로명 주소',
  };
  // const [user, setUser] = useState({
  //   nickname: '',
  //   title: '',
  //   price: '',
  //   date: '',
  //   startTime: '',
  //   lastTime: '',
  //   imageSrc: '',
  //   address: '',
  //   addressDetail: '',
  // });

  // useEffect(() => {
  //   const fetchUserData = async () => {
  //     try {
  //       const response = await fetch('/contract-request/applications/{application-id}'); // 데이터를 가져올 API 엔드포인트
  //       if (!response.ok) {
  //         throw new Error('Failed to fetch user data');
  //       }
  //       const data = await response.json();
        
  //       // 서버에서 받은 데이터로 상태 업데이트
  //       setUser({
  //         nickname: data.nickname,
  //         title: data.title,
  //         price: data.price,
  //         date: data.date,
  //         startTime: data.startTime,
  //         lastTime: data.lastTime,
  //         imageSrc: data.imageSrc,
  //         address: data.address,
  //         addressDetail: data.addressDetail,

  //       });
  //     } catch (error) {
  //       console.error('Error fetching user data:', error);
  //     }
  //   };
  
  //   fetchUserData();
  // }, []); 
  // 빈 배열: 컴포넌트가 처음 마운트될 때만 실행
  



  const handleModify = async () => {
    // Assuming contractRequestId is the ID of the contract you want to modify
    const contractRequestId = 'some-id'; // Replace 'some-id' with actual logic to get the ID
    const url = `/contract-requests/${contractRequestId}`;
    const {reset,} = useAnnouncementStore();
    const updatedUserData = {
      nickname: '우디',
      title: '대장동 백화점 줄서기',
      price: '일급 200,000원',
      date: '2024.10.11(금)',
      startTime: '18:00',
      lastTime: '21:00',
      imageSrc: '/image/add-img.png',
    };
  
    try {
      const response = await fetch(url, {
        method: 'PUT', // or 'PATCH' if your API supports it
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedUserData),
      });
  
      if (!response.ok) {
        throw new Error('Failed to modify the contract');
      }
  
      console.log('Modify action successfully sent');
      // Optional: Handle the response data if necessary
      const responseData = await response.json();
      console.log(responseData);
    } catch (error) {
      console.error('Error modifying the contract:', error);
    }
  };
  


  const handleRequestCancel = async () => {
    // Assuming contractRequestId is the ID of the contract you want to cancel
    const contractRequestId = 'some-id'; // Replace 'some-id' with actual logic to get the ID
    const url = `/contract-requests/${contractRequestId}`;
  
    try {
      const response = await fetch(url, {
        method: 'DELETE',
      });
  
      if (!response.ok) {
        throw new Error('Failed to cancel the contract');
      }
  
      console.log('Request cancel action successfully sent');
      // Optional: Redirect or update state after successful cancellation
    } catch (error) {
      console.error('Error cancelling the contract:', error);
    }
  };
  
  const {
    title,
    successReward,
    failureReward,
    address,
    category,
    detailContent,
    latitude,
    longitude,
    serviceType,
    contracteeDeposit,
    announceImg,
   
    setTitle,
    setSuccessReward,
    setFailureReward,
    setCategory,
    setDetailContent,
    setLatitude,
    setLongitude,
    setServiceType,
    setContracteeDeposit,
    setAnnounceImg,

    reset,
  } = useAnnouncementStore();

  const router = useRouter(); // Initialize the router
  const handleArrowButtonClick = () => {
    const id = '1'; // Example hardcoded ID; replace with actual logic to get the correct ID
    router.push(`/recruit/${id}`); // Navigates to the specified route
  };

  const handleNavigate = () => {
    router.push('/contract');  // '/contract' 페이지로 이동
  };
  

  return (
    <div>
      <Header
        imagesSrc="/image/back-icon.png"
        altText="채팅방으로 가기"
        href="/chat/[id]"
        navigateType="replace"
        title=""
        onClick={handleArrowButtonClick}
        showTopRightButton={false}
        onModify={handleModify}
        onRequestCancel={handleRequestCancel}
      />
  
        <div className={styles.chatInfo}>
        <div className={styles.horizontalAlign}>
          <div className={styles.textGroup}>
            <div className={styles.titleWithButton}>
              <h2 className={styles.chatDetailsTitle}>{user.title}</h2>
              <button
                onClick={handleArrowButtonClick}
                className={styles.arrowButton}
              >
                <img src="/image/arrow.png" alt="Navigate" style={{ width: '10px', height: 'auto' }} />
              </button>
            </div>
            <p className={styles.priceInfo}>
              {user.price}
              <span className={styles.priceDetailSpan}>
                {user.date} {user.startTime}~{user.lastTime}
              </span>
            </p>
          </div>
          <img src="/image/MainCardList.png" alt="Main Card List" className={styles.mainCardImage} />
        </div>
      </div>
      {/* 상단 진행 상태 바 */}
      <div className={styles.progressContainer}>
        
        <div className={styles.progressBarContainer}>
          <div className={styles.progressBar}>
            <div className={styles.progressFill}></div>
          </div>
          <div className={styles.progressLabels}>
            <span className={styles.progressLabel}>근무약속</span>
            <span className={styles.progressLabel}>시작</span>
            <span className={styles.progressLabel}>종료</span>
          </div>
        </div>
      </div>

      <div className={styles.infoBox}>
        <h3>라인업 진행상황</h3>
        <div className={styles.timeBoxContainer}>
         
            <span>라인업 관련 근무 미션을 완료해주세요.</span>
       
       
        </div>
      </div>

      <div className={styles.container}>
      {/* 타임라인 영역 */}
      <div className={styles.timeline}>
        <div className={styles.horizontalAlign}>
          <div className={styles.timelineHeadingContainer}>
            <div className={styles.leftContainer}>
              <div className={styles.timelineIcon}>1</div>
              <h2 className={styles.timelineHeading}>근무약속 확정</h2>
            </div>
            <span className={styles.status}>종료</span>
          </div>
        </div>
        
        <div className={styles.horizontalAlign}> 
          <img src={line.src} alt="Vertical line" className={styles.verticalLine} />
          <div className={styles.smallContainer}>
              {/* 근무 약속 확정 */}
              <div className={styles.section}>
              <p>라인업 근로 약속을 서로 확정했어요!</p>
              <button onClick={handleNavigate} className={styles.buttonContract}>
              보러가기
            </button>
              </div>
          </div>
        </div>

        <div className={styles.horizontalAlign}>
          <div className={styles.timelineHeadingContainer}>
            <div className={styles.leftContainer}>
              <div className={styles.timelineIcon}>2</div>
              <h2 className={styles.timelineHeading}>출근인증</h2>
            </div>
            <span className={styles.status}>종료</span>
          </div>
        </div>

        <div className={styles.horizontalAlign}> 
          <img src={line.src} alt="Vertical line" className={styles.verticalLine} />
          
          <div className={styles.smallContainer}>
            
            {/* 새로운 배경색이 적용된 섹션 */}
            <div className={styles.authSection}>
              <div className={styles.warningDescription}>
                아래에서 출근 인증을 선택해주세요.
              </div>
              
              <div className={styles.buttonContainer}>
                <button className={styles.button}>GPS 인증</button>
                <button className={styles.button}>사진 인증</button>
              </div>
            </div>

            {/* 미확인 페널티 안내 섹션 */}
            <div className={styles.noticeBox}>
              <div className={styles.warningTitle}>⚠️ 미확인 페널티 안내</div>
              <div className={styles.warningDetail}>
                출근 및 퇴근 미확인 시 서비스 이용에 제한이 있을 수 있습니다. (3회 이상 대행 일시정지)
              </div>
            </div>

          </div>
        </div>


        <div className={styles.horizontalAlign}>
          <div className={styles.timelineHeadingContainer}>
            <div className={styles.leftContainer}>
              <div className={styles.timelineIcon}>3</div>
              <h2 className={styles.timelineHeading}>퇴근확인</h2>
            </div>
            <span className={styles.status}>진행중</span>
          </div>
        </div>
        <div className={styles.horizontalAlign}> 
          <img src={line.src} alt="Vertical line" className={styles.verticalLine} />              
          <div className={styles.smallContainer}>
            <div className={styles.authSection}>
                <div className={styles.warningDescription}>
                  근무를 종료했다면 확인을 눌러주세요.
                </div>
                
                <div className={styles.buttonContainer}>
                  <button className={styles.button}>확인하기</button>
                </div>
              </div>

          </div>
          </div>

          <div className={styles.horizontalAlign}>
          <div className={styles.timelineHeadingContainer}>
            <div className={styles.leftContainer}>
              <div className={styles.timelineIcon}>4</div>
              <h2 className={styles.timelineHeading}>급여 정산</h2>
            </div>
            <span className={styles.status}>시작전</span>
          </div>
        </div>

                     
        <div className={styles.horizontalAlign}> 
          <img src={line.src} alt="Vertical line" className={styles.verticalLine} />            
          <div className={styles.smallContainer}>

          <div className={styles.authSection}>
              <div className={styles.warningDescription}>
                급여는 총 일급 200,000원 이에요.
              </div>
              
              <div className={styles.buttonContainer}>
                <button className={styles.button}>받았어요!</button>
                <button className={styles.button}>못 받았어요.</button>
              </div>
            </div>
      
          </div>
         </div>           
      </div>



    </div>



    </div>
  );
}  