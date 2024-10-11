'use client'

import { useParams } from 'next/navigation';
import { useEffect, useRef, useState } from 'react';
import { useRouter } from 'next/navigation';
import styles from './detail.module.css';
import Header from '@/app/components/Header/Header';
import KakaoMapButton from '@/app/components/Map/KakaoMapButton';
import KakaoMapModal from '@/app/components/Map/KaKaoMapModal';
import '@/app/typography.css';
import Button from '@/app/components/Button/Button';
import api from '../../../../../pages/api/api';
import ApplicantsModal from '@/app/components/ApplicantsModal';

const RecruitDetail = () => {
    type RecruitData = {
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

    const recruitDataRef = useRef<RecruitData | null>(null);
    const [isLoading, setIsLoading] = useState(true); // 로딩 상태 관리
    const [isMapOpen, setIsMapOpen] = useState(false); // 지도 모달 상태 관리
    const [userId, setUserId] = useState<number | null>(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [applicantsList, setApplicantsList] = useState([]);
    const params = useParams(); // 클라이언트 사이드에서만 사용
    const router = useRouter();
    const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;

    // 로컬스토리지에서 userId 가져오기 (클라이언트에서만 실행)
    useEffect(() => {
        const storedUserId = typeof window !== 'undefined' ? localStorage.getItem('userId') : null;
        if (storedUserId) {
            setUserId(parseInt(storedUserId, 10));
        }
    }, []);

    // 모집 데이터 로드
    useEffect(() => {
        if (params && params.id) {
            const id = params.id;
            localStorage.setItem('recruitId', id.toString());
            setIsLoading(true); // 데이터 로드 시작 시 로딩 상태로 변경
            api.get(`${baseURL}/recruits/${id}`)
                .then(response => {
                    recruitDataRef.current = response.data; // 데이터 저장
                    setIsLoading(false); // 데이터 로드 완료
                })
                .catch(error => {
                    console.error('모집 데이터 로드 실패:', error);
                    setIsLoading(false); // 에러 시 로딩 종료
                });
        }
    }, [params]);

    const handleMapButtonClick = () => {
        setIsMapOpen(true);
    };

    const handleCloseModal = () => {
        setIsMapOpen(false);
    };

    const applications = async () => {
        if (!params || !params.id) return;

        try {
            const response = await api.post(`${baseURL}/recruits/${params.id}/applications`, {});
            if (response.data) {
                const { chatRoomId, recruitId } = response.data;
                localStorage.setItem('recruitId', recruitId);
                router.push(`/chat/${chatRoomId}`);
            }
        } catch (error) {
            console.error('제출 실패:', error);
        }
    };

    const handleOpenApplicantsModal = () => {
        if (!params || !params.id) return;

        api.get(`${baseURL}/recruits/${params.id}/applicants`)
            .then(response => {
                console.log('지원자 목록:', response.data);
                setApplicantsList(response.data);
                setIsModalOpen(true);
            })
            .catch(error => {
                console.error('지원자 목록 조회 실패:', error);
                alert('지원자 목록을 불러오는 중 오류가 발생했습니다.');
            });
    };

    const handleCloseApplicantsModal = () => {
        setIsModalOpen(false);
    };

    // 로딩 상태일 때 또는 데이터가 없을 때는 로딩 화면 표시
    if (isLoading) {
        return <p>Loading...</p>;
    }

    if (!recruitDataRef.current) {
        return <p>Error: Recruit data not found</p>;
    }

    const recruitData = recruitDataRef.current;
    const address = recruitData.location.streetAddress || '';

    return (
        <div className={styles.detailPage}>
            <Header
                imagesSrc="/image/back2-icon.png"
                altText="홈으로 가기"
                href="/home"
                navigateType="push"
                title="라인업 채용정보"
            />

            <div className={styles.banner}>
                <img src="/image/mainbanner.png" alt="캐릭터" className={styles.character} />
            </div>

            <div className={styles.mainDetails}>
                <h1 className="text-xlarge">{recruitData.title}</h1>
                <hr className={styles.divideline}></hr>

                <div className={styles.detailsContainer}>
                    <div className={styles.detailItem}>
                        <div>
                            <img src="/image/mainbanner.png" alt="" className={styles.detailItemIcon} />
                        </div>
                        <div className={styles.detailItemText}>
                            <span className="text-small">일급</span>
                            <span className="text-medium" style={{ fontWeight: 'bold', color: '#2CE3A7' }}>
                                {recruitData.successSalary}
                            </span>
                        </div>
                    </div>
                    <div className={styles.detailItem}>
                        <div>
                            <img src="/image/mainbanner.png" alt="" className={styles.detailItemIcon} />
                        </div>
                        <div className={styles.detailItemText}>
                            <span className="text-small">모집 인원</span>
                            <span className="text-medium">1명</span>
                        </div>
                    </div>
                </div>

                <div className={styles.detailItem}>
                    <div>
                        <img src="/image/mainbanner.png" alt="" className={styles.detailItemIcon} />
                    </div>
                    <div className={styles.detailItemText}>
                        <span className="text-small">날짜</span>
                        <span className="text-medium">
                            {recruitData.startDate} ~ {recruitData.endDate}
                        </span>
                    </div>
                </div>
                <hr className={styles.divideline}></hr>

                <div className={styles.requirements}>
                    <h3>상세 정보</h3>
                    <div className={styles.detailItem}>
                        <div className={styles.detailItemText}>
                            <span className="text-small">{recruitData.content}</span>
                        </div>
                    </div>
                </div>

                <div className={styles.lineupArea}>
                    <h3>라인업 지역</h3>
                    <div className={styles.mapBox}>
                        <span>{address}</span>
                        <KakaoMapButton onClick={handleMapButtonClick} />
                        {isMapOpen && <KakaoMapModal keyword={address} onClose={handleCloseModal} />}
                    </div>
                </div>

                <div className={styles.actions}>
                    <button className={styles.likeButton}>
                        <img src="/image/heart.png" alt="" className={styles.heartIcon} />
                    </button>

                    {recruitData.writerId === userId ? (
                        <Button label="지원자 목록" type="submit" onClick={handleOpenApplicantsModal} />
                    ) : (
                        <Button label="지원하기" type="submit" onClick={applications} />
                    )}
                </div>

                {isModalOpen && params && params.id && (
                    <ApplicantsModal
                    applicants={applicantsList}
                    onClose={handleCloseApplicantsModal}
                    recruitId={Number(params.id)} // 여기서 recruitId를 ApplicantsModal로 전달
                    recruitStatus={recruitData.recruitStatus}
                    />
                )}
            </div>
        </div>
    );
};

export default RecruitDetail;
