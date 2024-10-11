import { useEffect, useState } from "react";
import { useRouter } from "next/navigation"; 
import api from '../../../../pages/api/api'; // Axios instance for API calls

interface BrandpayCheckoutPageProps {
  contractRequestId: number; // Expect the contractRequestId prop
}

export default function BrandpayCheckoutPage({ contractRequestId }: BrandpayCheckoutPageProps) {
  const router = useRouter();
  const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;
  const [pgToken, setPgToken] = useState<string | null>(null);

  // Function to initiate the payment request
  const requestPayment = async () => {
    try {
      const response = await api.post(`${baseURL}/payments`, {
        contractRequestId, // Send contractRequestId to the server
      });

      // Redirect to payment page (KakaoPay or other PG provider)
      const paymentUrl = response.data.redirectUrl;
      window.location.href = paymentUrl; // Redirect to the payment gateway
    } catch (error) {
      console.error('결제 실패:', error);
      alert('결제 요청에 실패했습니다.');
    }
  };

  // Listen for the pgToken after the user returns from the payment gateway
  useEffect(() => {
    const searchParams = new URLSearchParams(window.location.search);
    const token = searchParams.get('pgToken');
    if (token) {
      setPgToken(token);
      completePayment(token);
    }
  }, []);

  // Function to complete the payment after receiving the pgToken
  const completePayment = async (pgToken: string) => {
    try {
      const response = await api.get(`${baseURL}/payments/completed`, {
        params: {
          pgToken, // Pass pgToken back to the server
        },
      });
      alert('결제가 성공적으로 완료되었습니다.');
      router.push('/payment-success'); // Redirect to success page
    } catch (error) {
      console.error('결제 완료 처리 실패:', error);
      alert('결제 완료 처리 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="wrapper">
      <button
        className="button"
        onClick={requestPayment}
        style={{
          backgroundColor: 'rgba(44, 227, 167, 0.2)',
          color: '#fff',
          padding: '10px',
          width: '80%',
          margin: '20px auto',
          textAlign: 'center',
          border: '1px solid rgba(44, 227, 167, 0.2)',
          borderRadius: '5px',
        }}
      >
        결제하기
      </button>
    </div>
  );
}
