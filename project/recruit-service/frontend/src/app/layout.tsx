// app/layout.tsx
import React from 'react';
import { Inter } from 'next/font/google';
import './globals.css';
import Script from "next/script";
import NotificationComponent from './components/NotificationComponent ';

const inter = Inter({ subsets: ['latin'] });

declare global {
  interface Window {
    kakao: any;
    daum: any;
  }
}

export const metadata = {
  title: 'LineUp!',
  img: "/image/logo.png"
};

export default function LayoutWithoutNav({ children }: { children: React.ReactNode }) {
  return (

    <html lang="en">
      {/* 해당 부분에 Script를 추가한다. */}
      <head>
        <Script src="https://developers.kakao.com/sdk/js/kakao.js" async />
        <Script
          type="text/javascript"
          src={`//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.NEXT_PUBLIC_KAKAO_API_KEY}&libraries=services&autoload=false`}
          // strategy="beforeInteractive"
          async
        />
        <Script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" async />

      </head>
      <body className={inter.className}>
        {/* <NotificationComponent></NotificationComponent> */}
        {children}
      </body>

    </html>
  );
}


