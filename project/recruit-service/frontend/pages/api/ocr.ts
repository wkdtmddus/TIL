// pages/api/ocr.ts
import type { NextApiRequest, NextApiResponse } from 'next';
import axios from 'axios';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  const { image } = req.body;

  if (!image) {
    return res.status(400).json({ error: 'No image provided' });
  }

  try {
    // Prepare request payload for the OCR API
    const payload = {
      version: "V2",
      requestId: `ocr-request-${Date.now()}`, // Generate a unique request ID
      timestamp: Date.now(),
      images: [
        {
          format: "jpg", // Adjust the format if needed
          name: "uploaded_image",
          data: image.split(',')[1] // Extract the base64 data
        }
      ]
    };

    // Send the request to Naver Clova OCR API
    const response = await axios.post(process.env.NEXT_PUBLIC_NAVER_OCR_URL!, payload, {
      headers: {
        'X-OCR-SECRET': process.env.NEXT_PUBLIC_NAVER_OCR_SECRET!,
        'Content-Type': 'application/json'
      }
    });

    // Extract 'inferText' values from the OCR response
    const inferTexts = response.data.images[0].fields.map((field: any) => field.inferText);

    // Send back the extracted inferText values
    res.status(200).json({ inferTexts });
  } catch (error) {
    console.error('Error calling Naver Clova OCR:', error);
    res.status(500).json({ error: 'Failed to process OCR' });
  }
}
