"use client";

import React, { useState, useEffect } from 'react';
import styles from './InputAreaField.module.css';
import '../../typography.css';

interface InputAreaFieldProps {
  label: string;
  placeholder: string;
  required?: boolean;
  errorMessage?: string;
  value?: string;
  onChange?: (value: string) => void;
}

const InputAreaField: React.FC<InputAreaFieldProps> = ({
  label,
  placeholder,
  required = false,
  errorMessage,
  value,
  onChange,
}) => {
  const [inputValue, setInputValue] = useState(value || '');
  const [showError, setShowError] = useState(false);

  // 부모 컴포넌트로부터 받은 value가 변경될 때 inputValue도 업데이트
  useEffect(() => {
    setInputValue(value || '');
  }, [value]);

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const newValue = event.target.value;
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
      <textarea
        className={`${styles.input} ${showError ? styles.error : ''}`}
        placeholder={placeholder}
        value={inputValue}
        onChange={handleChange}
        onBlur={handleBlur}
        onFocus={handleFocus}
        required={required}
        rows={5} // 기본 줄 수 조정 (필요에 따라 수정)
      />
      <div className={styles.errorMessageContainer}>
        {showError && <img src="/image/errorbeacon.png" alt='에러 아이콘' className={styles.erroricon} />}
        {showError && <span className={styles.errorMessage}>{errorMessage}</span>}
      </div>
    </div>
  );
};

export default InputAreaField;
