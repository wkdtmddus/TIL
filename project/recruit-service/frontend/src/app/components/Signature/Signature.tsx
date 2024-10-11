import React, { useRef } from 'react';
import SignatureCanvas from 'react-signature-canvas';
import styles from './Signature.module.css'; // CSS 모듈을 임포트
import { saveSignature } from '../../../../pages/api/signaturewriterapi';

type SignatureCanvasType = {
  clear: () => void;
  getTrimmedCanvas: () => HTMLCanvasElement;
};

interface SignatureComponentProps {
  recruitId: number; // recruitId도 받음
  contractRequestId: number; // 이 부분을 추가
  onClose: () => void; // 모달 닫기 함수
}

const SignatureComponent: React.FC<SignatureComponentProps> = ({ recruitId, contractRequestId, onClose }) => {
  const sigCanvas = useRef<SignatureCanvasType | null>(null);

  const clear = () => sigCanvas.current?.clear();

  const save = async () => {
    const canvas = sigCanvas.current?.getTrimmedCanvas();
    if (canvas) {
      // Canvas 데이터를 Blob으로 변환한 후 전송
      canvas.toBlob(async (blob: Blob | null) => {
        if (blob) {
          try {
            // recruitId와 contractRequestId를 함께 전송
            await saveSignature(recruitId, contractRequestId, blob); 
            onClose(); // 서명 완료 후 모달 닫기
          } catch (error) {
            console.error('Error while saving signature:', error);
          }
        }
      }, 'image/png'); // Blob 타입 지정
    }
  };

  return (
    <div>
      <div className={styles.signatureContainer}>
        <span className={`${styles.signatureLabel} ${styles.realName}`}>공고자 실명</span>
        <SignatureCanvas 
          ref={sigCanvas} 
          penColor="#2CE3A7" 
          canvasProps={{ width: 500, height: 200, className: 'sigCanvas' }} 
        />
        <span className={`${styles.signatureLabel} ${styles.signature}`}>서명</span>
      </div>
      <div className={styles.buttonContainer}>
        <button onClick={clear}>Clear</button>
        <button onClick={save}>Save</button>
      </div>
    </div>
  );
};


export default SignatureComponent;

