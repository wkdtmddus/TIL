// pages/api/address-search.ts
import type { NextApiRequest, NextApiResponse } from 'next';
import axios from 'axios';

interface AddressSearchResponse {
  roadAddr: string;
  jibunAddr: string;
  zipNo: string;
}

interface ApiResponse {
  results: {
    juso: AddressSearchResponse[];
  };
}

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const { keyword } = req.query;

  if (!keyword) {
    return res.status(400).json({ error: 'Keyword is required' });
  }

  try {
    const response = await axios.get<ApiResponse>('https://business.juso.go.kr/addrlink/addrLinkApi.do', {
      params: {
        currentPage: 1,
        countPerPage: 10,
        keyword,
        confmKey: 'devU01TX0FVVEgyMDI0MDkxMTEwMDY1NjExNTA3OTI=', // 발급받은 API Key 사용
        resultType: 'json',
      },
    });

    res.status(200).json(response.data.results.juso || []);
  } catch (error) {
    console.error('Error fetching address data:', error);
    res.status(500).json({ error: 'Failed to fetch address data' });
  }
}
