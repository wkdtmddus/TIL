'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import api from '../../../pages/api/api';
import styles from './ChatList.module.css';

interface ChatListProps { }

interface Room {
  chatRoomId: number;
  nickname: string;
  profileImgUrl: string;
  recentMessage: string;
  createdAt: string;
  isRead: boolean;
  recruitId: number;
}

export default function ChatList() {
  const [rooms, setRooms] = useState<Room[]>([]);
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get('/chat-rooms');
        setRooms(response.data);
        console.log(response.data);
        console.log('profileImgUrl: ', response.data[0].profileImgUrl);
      } catch (error) {
        console.error('채팅 목록을 가져오는 중 오류 발생:', error);
      }
    };

    fetchData();
  }, []);

  const handleClick = (id: number, recruitId: number) => {
    if (typeof window !== 'undefined') {
      localStorage.setItem('recruitId', recruitId.toString());
    }
    router.push(`/chat/${id}`);
  };

  return (
    <div className={styles.chatContainer}>
      {rooms.map((room) => (
        <div key={room.chatRoomId} className={styles.chatMessage} onClick={() => handleClick(room.chatRoomId, room.recruitId)}>
          {/* <img src={room.profileImgUrl} alt="profile" className={styles.profileImage} /> */}
          <img src='/image/add-img.png' alt="profile" className={styles.profileImage} />
          <div className={styles.messageContent}>
            <div className={styles.messageHeader}>
              <span className={styles.name}>{room.nickname}</span>
              <span className={styles.time}>{room.createdAt}</span>
            </div>
            <p className={styles.message}>{room.recentMessage}</p>
          </div>
        </div>
      ))}
    </div>
  );
}
