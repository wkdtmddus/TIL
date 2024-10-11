import React, { useEffect, useState } from "react";
import styles from "./ApplicantsModal.module.css";
import { useRouter } from "next/navigation";
import api from "../../../pages/api/api"; // API 호출을 위한 모듈 가져오기
// 지원자 모달 부르기

interface Applicant {
  applicantId: number;
  nickname: string;
  createdAt: string;
  chatRoomId: number;
  profileImgUrl: string;
}

interface ApplicantsModalProps {
  applicants: Applicant[];
  onClose: () => void;
  recruitId: number; // 부모 컴포넌트에서 전달받은 recruitId
  recruitStatus: string;
}

const ApplicantsModal: React.FC<ApplicantsModalProps> = ({
  applicants,
  onClose,
  recruitId,
  recruitStatus,
}) => {
  const [isClient, setIsClient] = useState(false);
  const router = useRouter();

  useEffect(() => {
    setIsClient(true);
  }, []);

  // 계약 요청 함수
  // 계약 요청 함수
  const handleContract = async (applicantId: number) => {
    if (!isClient) return;

    try {
      console.log(recruitId);
      console.log(applicantId);
      // 서버로 POST 요청을 보내서 contractRequestId를 받아옴
      const response = await api.post("/contract-requests/send", {
        recruitId: recruitId,
        applicantId: applicantId,
      });

      const contractRequestId = response.data; // 서버에서 반환된 contractRequestId
      console.log("Contract request successful with ID:", contractRequestId);

      // contract 페이지로 이동하면서 contractRequestId와 recruitId를 쿼리 파라미터로 전달
      router.push(
        `/contract?contractRequestId=${contractRequestId}&recruitId=${recruitId}`
      );
    } catch (error) {
      console.error("계약 요청 실패:", error);
      alert("계약 요청에 실패했습니다. 다시 시도해주세요.");
    }
  };

  const chatting = (chatRoomId: number) => {
    if (isClient) {
      router.push(`/chat/${chatRoomId}`);
    }
  };

  const formatDate = (createdAt: string): string => {
    const createdDate = new Date(createdAt);
    const today = new Date();

    const diffTime =
      today.setHours(0, 0, 0, 0) - createdDate.setHours(0, 0, 0, 0);
    const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24));

    if (diffDays === 0) {
      return "오늘";
    } else if (diffDays === 1) {
      return "어제";
    } else {
      return `${diffDays}일 전`;
    }
  };

  return (
    <div className={styles.modalOverlay}>
      <div className={styles.modalContent}>
        {applicants.length > 0 ? (
          applicants.map((applicant, index) => (
            <div className={styles.applicantItem} key={index}>
              <div className={styles.applicantInfo}>
                <img
                  // src={applicant.profileImgUrl}
                  src='/image/add-img.png'
                  alt="avatar"
                  className={styles.avatar}
                />
                <div className={styles.applicantDetails}>
                  <span className={styles.applicantName}>
                    {applicant.nickname}
                  </span>
                </div>
                <span className={styles.time}>
                  {formatDate(applicant.createdAt)}
                </span>
              </div>
              <div className={styles.actionButtons}>
                <button
                  className={styles.chatButton}
                  onClick={() => chatting(applicant.chatRoomId)}
                >
                  채팅
                </button>
                <button
                  className={styles.contractButton}
                  onClick={() => handleContract(applicant.applicantId)}
                >
                  계약하기
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>목록이 없습니다.</p>
        )}
        <button className={styles.closeButton} onClick={onClose}>
          닫기
        </button>
      </div>
    </div>
  );
};

export default ApplicantsModal;
