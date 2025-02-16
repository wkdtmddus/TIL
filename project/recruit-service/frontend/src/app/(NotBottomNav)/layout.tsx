// app/(without-nav)/layout.tsx
import React from 'react';
import { Inter } from 'next/font/google';
import '../globals.css';

const inter = Inter({ subsets: ['latin'] });

export default function LayoutWithoutNav({ children }: { children: React.ReactNode }) {
  return (
    <div className="container">
      {children}
    </div>

  );
}
