import apiImg from '../api/apiImg'; // apiImg를 import하여 사용

export const saveSignature = async (recruitId: number, contractRequestId: number, signatureImg: Blob) => {
  try {
    const formData = new FormData();
    // formData.append('recruitId', String(recruitId)); // recruitId를 문자열로 변환하여 전송
    // formData.append('contractRequestId', String(contractRequestId)); // contractRequestId도 추가
    formData.append('signature', signatureImg, 'signature.png'); // Blob 데이터를 전송

    const response = await apiImg.post(`/contracts?contractRequestId=${contractRequestId}`, formData, {
      // headers: {
      //   'Content-Type': 'multipart/form-data',
      // },
    });

    if (response.status === 200) {
      console.log('Signature saved successfully');
      return response.data;
    } else {
      console.error('Failed to save signature');
      throw new Error('Failed to save signature');
    }
  } catch (error) {
    console.error('Error:', error);
    throw error;
  }
};






