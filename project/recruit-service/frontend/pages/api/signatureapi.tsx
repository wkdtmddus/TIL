import apiImg from '../api/apiImg'; // apiImg를 import하여 사용

export const saveSignature = async (recruitId: number, contractRequestId: number, signatureImg: Blob) => {
  try {
    const formData = new FormData();
    // formData.append('recruitId', String(recruitId)); // recruitId as a string
    formData.append('signatureImg', signatureImg, 'signature.png'); // Blob data as a file

    const response = await apiImg.post(`/contract-requests/accept`, formData, {
      // headers: {
      //   'Content-Type': 'multipart/form-data',
      // },
      params: {
        'recruitId': recruitId,
      }
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

