// pages/api/toss/index.ts
import type { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const { customerKey, code } = req.query;

  const apiSecretKey = process.env.TOSS_SECRET_KEY;

  if (!apiSecretKey) {
    return res.status(500).json({ message: 'Toss Secret Key is missing' });
  }

  const encryptedApiSecretKey = "Basic " + Buffer.from(`${apiSecretKey}:`).toString("base64");

  try {
    const response = await fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token", {
      method: "POST",
      headers: {
        Authorization: encryptedApiSecretKey,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        grantType: "AuthorizationCode",
        customerKey,
        code,
      }),
    });

    const result = await response.json();

    if (!response.ok) {
      console.error("Error fetching access token:", result);
      return res.status(response.status).json({ message: 'Failed to fetch access token', error: result });
    }

    res.status(200).json(result);

  } catch (error) {
    console.error("Error during access token request:", error);
    res.status(500).json({ message: 'Internal Server Error', error });
  }
}
