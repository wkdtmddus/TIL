"use client";

import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import api from "../../../pages/api/api";
import styles from "./RecruitInfo.module.css";
import LoadingSpinner from "@/app/components/LoadingSpinner";

interface RecruitData {
  writerId: number;
  title: string;
  successSalary: number;
  startDate: string;
  endDate: string;
  recruitImgUrl: string;
  recruitStatus: string;
}

interface RecruitInfoProps {
  userId: number | null;
  recruitId: number | null;
}

const RecruitInfo: React.FC<RecruitInfoProps> = ({ recruitId, userId }) => {
  const router = useRouter();
  const [recruitData, setRecruitData] = useState<RecruitData | null>(null);

  useEffect(() => {
    if (!recruitId) {
      return console.log("recruitId 불러오는 중");
    }
    const fetchRecruitData = async () => {
      try {
        const response = await api.get(`/recruits/${recruitId}`);
        setRecruitData(response.data);
      } catch (error) {
        console.error("Error fetching recruit data:", error);
      }
    };

    fetchRecruitData();
  }, [recruitId]);

  if (!recruitData) {
    return <LoadingSpinner />;
  }

  const handleContract = async () => {
    try {
      console.log(recruitId);
      console.log(userId);
      // 서버로 POST 요청을 보내서 contractRequestId를 받아옴
      const response = await api.post("/contract-requests/send", {
        'recruitId': recruitId,
        'applicantId': userId,
      });

      const contractRequestId = response.data; // 서버에서 반환된 contractRequestId
      console.log("Contract request successful with ID:", contractRequestId);

      // contract 페이지로 이동하면서 contractRequestId와 recruitId를 쿼리 파라미터로 전달
      router.push(
        `/contract?contractRequestId=${contractRequestId}&recruitId=${recruitId}`
      );
    } catch (error) {
      console.error("계약 요청 실패:", error);
      alert("계약자가 아닙니다.");
    }
  };

  return (
    <div className={styles.chatInfo}>
      <div className={styles.info}>
        <img
          // src={recruitData.recruitImgUrl}
          src='/image/MainCardList.png'
          alt="공고 이미지"
          className={styles.recruitImage}
        />
        <div className={styles.chatDetails}>
          <h2>{recruitData.title}</h2>
          <div className={styles.pricedate}>
            <p>{recruitData.successSalary} 원</p>
            <p style={{ fontSize: "0.6rem" }}>
              {recruitData.startDate} ~ {recruitData.endDate}
            </p>
          </div>
        </div>
      </div>
      <div className={styles.buttons}>
        {userId == recruitData.writerId ? null : (
          <button
            className={styles.button}
            onClick={() => handleContract()}
          >
            계약하기
          </button>
        )}
        {recruitData.recruitStatus == "구인 완료" ? (
          <button
            className={styles.button}
            onClick={() => router.push("/verification")}
          >
            진행상황
          </button>
        ) : null}
      </div>
    </div>
  );
};

export default RecruitInfo;
