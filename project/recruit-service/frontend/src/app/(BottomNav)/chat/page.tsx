'use client';

import TopNav from '@/app/components/TopNav';
import ChatList from '@/app/components/ChatList'; // ChatList 컴포넌트 불러오기

export default function ChatPage() {
  return (
    <div>
      <TopNav title="채팅" />
      <ChatList />
    </div>
  );
}
