// app/(with-nav)/layout.tsx
import React from 'react';
import BottomNav from '../components/BottomNav';
import { Inter } from 'next/font/google';
import '../globals.css';

const inter = Inter({ subsets: ['latin'] });

export default function LayoutWithNav({ children }: { children: React.ReactNode }) {
  return (
        <div className="container">
          {children}
          <BottomNav />
        </div>
  );
}
