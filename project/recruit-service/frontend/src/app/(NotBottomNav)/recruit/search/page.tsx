// recruit/search.tsx
'use client'

import React from "react";
import MainTopSearchBar from '@/app/components/MainTopSearchBar'
import { useRouter } from 'next/navigation'; // Corrected import



const SearchPage = () => {
  const router = useRouter();

  return (
    <>
    <MainTopSearchBar></MainTopSearchBar>
    <div style={{ padding: '20px', background: '#1E1E1E', height: '100vh' }}>
        
      <button onClick={() => router.back()} style={{ color: '#fff', marginBottom: '10px' }}>뒤로 가기</button>
      <input type="text" placeholder="검색어를 입력하세요" style={{ width: '100%', padding: '10px', fontSize: '16px' }} />
      <p style={{ color: '#fff', marginTop: '10px' }}>최근 검색어</p>
      {/* Add more search UI here */}
    </div>
    </>
  );
}

export default SearchPage;
