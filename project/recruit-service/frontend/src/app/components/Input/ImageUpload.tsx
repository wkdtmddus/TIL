"use client";

import { useState, useRef } from 'react';
import styles from './ImageUpload.module.css';

interface ImageUploadProps {
  setImage: (image: File) => void;
}

export default function ImageUpload({ setImage }: ImageUploadProps) {
  const [previewImage, setPreviewImage] = useState<string>('/image/add-img.png');
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setPreviewImage(reader.result as string);
        setImage(file);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleImageClick = () => {
    fileInputRef.current?.click();
  };

  return (
    <div className={styles.imageUpload}>
      <input
        type="file"
        accept="image/*"
        onChange={handleImageChange}
        ref={fileInputRef}
        style={{ display: 'none' }}
      />
      <img
        src={previewImage}
        alt="Preview"
        className={styles.preview}
        onClick={handleImageClick}
      />
      <img
        src="/image/camera-icon.png"
        alt="Camera"
        className={styles.cameraIcon}
        onClick={handleImageClick}
      />
    </div>
  );
}
