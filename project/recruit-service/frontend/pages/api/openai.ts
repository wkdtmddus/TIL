import type { NextApiRequest, NextApiResponse } from 'next';
import { OpenAI } from 'openai';

// Your handler function
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    try {
        // Check for POST method
        if (req.method !== 'POST') {
            return res.status(405).json({ message: 'Method not allowed' });
        }

        const { ocrText } = req.body;

        // Check if ocrText is provided
        if (!ocrText) {
            return res.status(400).json({ message: 'No OCR text provided' });
        }

        // Debug environment variables
        console.log('OpenAI API Key:', process.env.NEXT_PUBLIC_OPENAI_API_KEY);

        // Initialize OpenAI
        const openai = new OpenAI({
            apiKey: process.env.NEXT_PUBLIC_OPENAI_API_KEY!,
        });

        console.log('Sending request to OpenAI with OCR text:', ocrText);

        // Make the request to OpenAI API
        const response = await openai.chat.completions.create({
            model: "gpt-3.5-turbo-0125",
            messages: [
                {
                    role: "user",
                    content: `
                    다음 공고 텍스트를 바탕으로 아래의 정보를 간결하고 명확하게 추출한 후 JSON 형식으로 반환하세요. 줄서기 대행자를 모집하는 것을 고려해 지원자가 쉽게 이해할 수 있는 형식으로 작성하십시오:
                    {
                      "title": "행사 이름과 줄서기 대행 모집의 목적을 명확히 표현한 제목 (15글자 이내)",
                      "detailContent": "줄서기 대행자에게 필요한 행사 설명을 간결하게 작성 (100자 이내, 지원자가 어떤 역할을 하게 되는지 명확하게 설명)"
                    }
                    텍스트: "${ocrText}"
                    `
                }
            ]
        });

        // Debug OpenAI response
        console.log('OpenAI response received:', response);

        // Return the response
        res.status(200).json({ data: response.choices[0].message.content });
    } catch (error: any) {
        // Handle and log the error
        console.error('Error processing OpenAI request:', error.message);
        console.error('Stack trace:', error.stack);

        // Respond with 500 status and error details
        res.status(500).json({ message: 'Error processing OpenAI request', error: error.message });
    }
}
