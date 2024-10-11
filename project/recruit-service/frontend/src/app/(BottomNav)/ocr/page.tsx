// components/ImageUpload.tsx
"use client";

import React, { useState } from 'react';
import './ocr.css'

const ImageUpload: React.FC = () => {
  const [image, setImage] = useState<File | null>(null);
  const [ocrResult, setOcrResult] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      setImage(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!image) {
      setError('Please upload an image');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const reader = new FileReader();
      reader.readAsDataURL(image);
      reader.onloadend = async () => {
        const response = await fetch('/api/ocr', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ image: reader.result }),
        });

        if (!response.ok) {
          throw new Error('Failed to fetch OCR data');
        }

        const data = await response.json();
        setOcrResult(JSON.stringify(data, null, 2)); // Display OCR results
      };
    } catch (err: any) {
      setError(err.message || 'An error occurred');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto' }}>
      <h2 className='text'>Upload an Image for OCR</h2>
      <form onSubmit={handleSubmit}>
        <input type="file" accept="image/*" onChange={handleImageChange} />
        <button type="submit" disabled={loading || !image}>
          {loading ? 'Processing...' : 'Submit'}
        </button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {ocrResult && (
        <div className='text'>
          <h3 className='text'>OCR Result:</h3>
          <pre className='text'>{ocrResult}</pre>
        </div>
      )}
    </div>
  );
};

export default ImageUpload;
