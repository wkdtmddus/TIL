'use client'; // 클라이언트 컴포넌트로 설정

import { useSearchParams } from 'next/navigation'; // useSearchParams 사용
import { useEffect, useState, Suspense } from 'react';

// 결제 승인 API 호출 함수
const approvePayment = async (paymentKey: string, orderId: string, amount: number) => {
  try {
    const BASE_URL = `${process.env.PROTOCOL}://${process.env.HOST}:${process.env.BACK_PORT}`; // Use full URL
    const response = await fetch(`${BASE_URL}/approve`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${process.env.NEXT_PUBLIC_API_KEY}`, // Make sure this is safe to expose
      },
      body: JSON.stringify({
        paymentKey,
        orderId,
        amount,
      }),
    });

    if (!response.ok) {
      throw new Error('Payment approval failed');
    }

    const result = await response.json();
    return result;
  } catch (error) {
    console.error('Error in payment approval:', error);
    throw error;
  }
};

function SuccessPageContent() {
  const searchParams = useSearchParams(); // 쿼리 파라미터 접근
  const paymentKey = searchParams?.get('paymentKey') || ''; // null 체크 및 기본값 설정
  const orderId = searchParams?.get('orderId') || '';
  const amount = searchParams?.get('amount') || '';
  const [isProcessing, setIsProcessing] = useState(true); // Default to true to show loader initially
  const [isError, setIsError] = useState(false); // Track error state

  useEffect(() => {
    // 파라미터가 없을 경우 로직을 중단
    if (!paymentKey || !orderId || !amount) {
      console.error("Missing query parameters");
      setIsError(true); // Set error if parameters are missing
      setIsProcessing(false);
      return;
    }

    const parsedAmount = parseFloat(amount);

    // 페이지 로드 시 결제 승인 요청
    const approvePaymentRequest = async () => {
      try {
        setIsProcessing(true);
        const result = await approvePayment(paymentKey as string, orderId as string, parsedAmount);
        console.log('Payment approved successfully:', result);
        setIsProcessing(false); // Stop the loader once successful
      } catch (error) {
        console.error('Error approving payment:', error);
        setIsError(true); // Set error if payment fails
        setIsProcessing(false);
      }
    };

    approvePaymentRequest();
  }, [paymentKey, orderId, amount]);

  return (
    <div>
      {isProcessing ? (
        <p>Processing payment approval...</p>
      ) : isError ? (
        <p>Payment approval failed. Please try again or contact support.</p>
      ) : (
        <p>Payment approval completed successfully!</p>
      )}
    </div>
  );
}

// Suspense로 감싸서 데이터 로딩이 끝날 때까지 기다리도록 설정
export default function SuccessPage() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <SuccessPageContent />
    </Suspense>
  );
}
