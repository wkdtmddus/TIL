'use client';
import React, { useState, useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation'; // useRouter와 useSearchParams 둘 다 사용
import Header from "@/app/components/Header/Header";
import announcementStyles from '@/app/(NotBottomNav)/recruit/announcement.module.css';
import '@/app/typography.css';
import styles from '@/app/(NotBottomNav)/contract/contract.module.css';
import SignatureComponent from '@/app/components/Signature/Signature';
import SignatureComponentEmpolyee from "@/app/components/Signature/Signature-empolyee";
import Modal from "@/app/components/Modal/Modal";
import BrandpayCheckoutPage from '@/app/components/Payment/BrandpayCheckout';
import api from '../../../../pages/api/api'; // API import 추가
import { Suspense } from 'react';


export default function ContractWrapper() {
  return (
    <Suspense fallback={<p>Loading contract data...</p>}>
      <Contract />
    </Suspense>
  );
}

interface RecruitData {
  writerId: number;
  title: string;
  content: string;
  successSalary: number;
  failSalary: number;
  startDate: string;
  endDate: string;
  startAt: string;
  endAt: string;
  location: {
      streetAddress: string;
      district: string;
      latitude: number;
      longitude: number;
  };
  recruitImgUrl: string;
  recruitStatus: string;
};

function Contract() {
  const [isLoading, setIsLoading] = useState(true); // 로딩 상태를 관리
  const searchParams = useSearchParams(); // useSearchParams 사용
  const router = useRouter(); // useRouter 사용
  const [applicantId, setApplicantId] = useState<string | null>(null); // applicantId 상태 관리
  const [writerId, setWriterId] = useState<string | null>(null); // writerId 상태 관리
  const [recruitId, setRecruitId] = useState<string | null>(null); // recruitId 상태 관리
  const [contractRequestId, setContractRequestId] = useState<string | null>(null); // contractRequestId 상태 관리
  const [recruitData, setRecruitData] = useState<RecruitData | null>(null);

  const [isModalOpen, setModalOpen] = useState(false); // 모달 상태 관리
  
  // 데이터 로드
  useEffect(() => {
    if (searchParams) {
      const queryRecruitId = searchParams.get('recruitId');
      const queryContractRequestId = searchParams.get('contractRequestId');
      
      setRecruitId(queryRecruitId); // 쿼리 파라미터에서 recruitId 설정
      setContractRequestId(queryContractRequestId); // 쿼리 파라미터에서 contractRequestId 설정
  
      if (queryRecruitId) {
        // recruitId가 존재할 때 API 요청
        api.get(`/recruits/${queryRecruitId}`)
          .then(response => {
            const data = response.data;
            
            // writerId와 applicantId를 포함한 데이터를 상태에 저장
            setWriterId(data.writerId);
            setApplicantId(data.applicantId);
  
            setRecruitData(response.data);
          })
          .catch(error => {
            console.error('Error fetching recruit data:', error);
          })
          .finally(() => {
            setIsLoading(false);
          });
      } else {
        setIsLoading(false);
      }
    }
  }, [searchParams]);

  // 모달 열기/닫기 함수
  const handleOpenModal = () => setModalOpen(true);
  const handleCloseModal = () => setModalOpen(false);

  if (isLoading) {
    return <p>Loading...</p>;
  }

  return (
    <div className={announcementStyles.announcement}>
      <Header
        imagesSrc="/image/back-icon.png"
        altText="채팅방으로 가기"
        href='#'
        onClick={() => router.back()} // router.back()으로 돌아가기 기능 처리
        navigateType="replace"
        title="근무약속(계약서)"
      />
      <hr className={announcementStyles.divideline}></hr>

      <div className={styles.chatInfo}>
        <img src='/image/add-img.png' alt="프로필" className={styles.profileImage} />
        <div className={styles.chatDetails}>
          <h2>{recruitData?.title}</h2>
          <p>
            {recruitData?.successSalary}
            <span style={{ fontSize: '0.6rem', marginLeft: '8px' }}>{recruitData?.startDate} {recruitData?.startAt}~{recruitData?.endAt}</span>
          </p>
        </div>
      </div>

      <div className={styles.infoBox}>
        <h3>라인업 날짜</h3>
        <div className={styles.timeBoxContainer}>
          <div className={styles.timeBox}>
            <span>시작 {recruitData?.startDate}</span>
          </div>
          <div className={styles.timeBox}>
            <span>종료 {recruitData?.endDate}</span>
          </div>
        </div>
      </div>

      <div className={styles.infoBox}>
        <h3>라인업 시간</h3>
        <div className={styles.timeBoxContainer}>
          <div className={styles.timeBox}>
            <span>시작 {recruitData?.startAt}</span>
          </div>
          <div className={styles.timeBox}>
            <span>종료 {recruitData?.endAt}</span>
          </div>
        </div>
      </div>

      <div className={styles.infoBox}>
        <h3>라인업 장소</h3>
        <div className={styles.dateBox}>
          <p className={styles.infoBox}>{recruitData?.location.streetAddress}</p>
          <p className={styles.adressBox}>{recruitData?.location.district}</p>
        </div>
      </div>

      {writerId && 
        <div className={styles.infoBox}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <h3 style={{ margin: '0 20px 0 0' }}>공고자 결제란</h3>
            <BrandpayCheckoutPage contractRequestId={Number(contractRequestId)} />
          </div>
          <h3>공고자 계약 확인란</h3>
          <div className={styles.signatureBox} onClick={handleOpenModal}>
            <p>서명하기</p>
            <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
              {writerId && recruitId && contractRequestId && (
                <SignatureComponent
                  recruitId={Number(recruitId)}
                  contractRequestId={Number(contractRequestId)}
                  onClose={handleCloseModal}
                />
              )}
            </Modal>
          </div>
        </div>
      }

      {applicantId &&
        <div className={styles.infoBox}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <h3 style={{ margin: '0 20px 0 0' }}>대행자 결제란</h3>
            <BrandpayCheckoutPage contractRequestId={Number(contractRequestId)} />
          </div>
          <h3>대행자 계약 확인란</h3>
          <div className={styles.signatureBox} onClick={handleOpenModal}>
            <p>서명하기</p>
            <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
              {applicantId && contractRequestId && (
                <SignatureComponentEmpolyee
                  recruitId={Number(recruitId)}
                  contractRequestId={Number(contractRequestId)}
                  onClose={handleCloseModal}
                />
              )}
            </Modal>
          </div>
        </div>
      }
    </div>
  );
}
