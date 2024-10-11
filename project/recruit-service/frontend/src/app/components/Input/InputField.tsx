// components/InputField.tsx
"use client";

import React, { useState, useEffect } from 'react';
import styles from './InputField.module.css';
import '../../typography.css';

interface InputFieldProps {
  label?: string;
  placeholder: string;
  required?: boolean;
  errorMessage?: string;
  value?: string;
  onChange?: (value: string) => void;
  type?: 'text' | 'number'; // Input type 추가
}

const InputField: React.FC<InputFieldProps> = ({
  label,
  placeholder,
  required = false,
  errorMessage,
  value,
  onChange,
  type = 'text', // 기본값을 'text'로 설정
}) => {
  const [inputValue, setInputValue] = useState(value || '');
  const [showError, setShowError] = useState(false);

  // 부모 컴포넌트로부터 받은 value가 변경될 때 inputValue도 업데이트
  useEffect(() => {
    setInputValue(value || '');
  }, [value]);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = event.target.value;

    // 숫자 타입일 때는 숫자만 입력 가능
    if (type === 'number' && !/^\d*$/.test(newValue)) {
      return;
    }

    setInputValue(newValue);
    setShowError(required && newValue === ''); // 필수 입력 조건 체크
    onChange && onChange(newValue); // 부모 컴포넌트로 값 전달
  };

  const handleBlur = () => {
    // 필수 입력 조건 체크
    if (required && inputValue === '') {
      setShowError(true);
    }
  };

  const handleFocus = () => {
    setShowError(false); // 포커스 시 에러 메시지 숨김
  };

  return (
    <div className={styles.inputContainer}>
      <div className={styles.labelContainer}>
        <label className="text-large">{label}</label>
      </div>
      <input
        type={type} // 타입을 동적으로 설정
        className={`${styles.input} ${showError ? styles.error : ''}`}
        placeholder={placeholder}
        value={inputValue}
        onChange={handleChange}
        onBlur={handleBlur}
        onFocus={handleFocus}
        required={required}
      />
      <div className={styles.errorMessageContainer}>
        {showError && <img src="/image/errorbeacon.png" alt='에러 아이콘' className={styles.erroricon} />}
        {showError && <span className={styles.errorMessage}>{errorMessage}</span>}
      </div>
    </div>
  );
};

export default InputField;
