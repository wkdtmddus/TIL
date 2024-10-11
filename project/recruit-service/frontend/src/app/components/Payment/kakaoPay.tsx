import { useEffect, useState } from "react";
import api from '../../../../pages/api/api'; // Axios instance for API calls


export default function KakaoPay() {
    
  const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;

  const requestPayment = async () => {
    try {
        // const response = await api.post(`${baseURL}/contract-requests/${contract-request-id}/pay`, {

        // });
    
    } catch (error) {
        console.error('결제 실패:', error);
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
