'use client';

import { useEffect, useState } from 'react';
import TopNav from '@/app/components/TopNav';
import styles from './ChatDetail.module.css';
import ChatMessages from '@/app/components/ChatMessages';
import RecruitInfo from '@/app/components/RecruitInfo';

export default function ChatDetailPage({ params }: { params: { id: number } }) {
  const { id: chatRoomId } = params;
  const [userId, setUserId] = useState<number | null>(null);
  const [recruitId, setRecruitId] = useState<number | null>(null);

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const userIdString = localStorage.getItem('userId');
      const recruitIdString = localStorage.getItem('recruitId');
      setUserId(userIdString ? Number(userIdString) : null);
      setRecruitId(recruitIdString ? Number(recruitIdString) : null);
    }
  }, []);

  return (
    <div className={styles.chatContainer}>
      <div className={styles.topnav}>
        <TopNav title="채팅" />
        <RecruitInfo recruitId={recruitId} userId={userId} />
      </div>
      <ChatMessages chatRoomId={chatRoomId} userId={userId} />
    </div>
  );
}
