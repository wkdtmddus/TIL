import React, { useRef } from 'react';
import SignatureCanvas from 'react-signature-canvas';
import styles from './Signature.module.css'; // CSS 모듈을 임포트
import { saveSignature } from '../../../../pages/api/signatureapi';

type SignatureCanvasType = {
  clear: () => void;
  getTrimmedCanvas: () => HTMLCanvasElement;
};

interface SignatureComponentEmpolyeeProps {
  recruitId: number; // 부모 컴포넌트에서 받을 contractRequestId
  contractRequestId: number; // 이 부분을 추가
  onClose: () => void; // 모달 닫기 함수
}

const SignatureComponentEmpolyee: React.FC<SignatureComponentEmpolyeeProps> = ({ recruitId, contractRequestId, onClose }) => {
  const sigCanvas = useRef<SignatureCanvasType | null>(null);

  const clear = () => sigCanvas.current?.clear();

  const save = async () => {
    const canvas = sigCanvas.current?.getTrimmedCanvas();
    if (canvas) {
      // Convert canvas to Blob and then upload
      canvas.toBlob(async (blob: Blob | null) => {
        if (blob) {
          try {
            await saveSignature(recruitId, contractRequestId, blob); // Pass Blob to saveSignature
            onClose();
          } catch (error) {
            console.error('Error while saving signature:', error);
          }
        }
      }, 'image/png'); // Blob type
    }
  };

  return (
    <div>
      <div className={styles.signatureContainer}>
        <span className={`${styles.signatureLabel} ${styles.realName}`}>대행자 실명</span>
        <SignatureCanvas 
          ref={sigCanvas} 
          penColor="#2CE3A7" // 원하는 색상으로 변경
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

export default SignatureComponentEmpolyee;
