// components/Button.tsx
import React from 'react';
import styles from './ButtonGrey.module.css';

interface ButtonProps {
  label: string;
  onClick?: () => void;
  type?: 'button' | 'submit' | 'reset';
  style?: React.CSSProperties;
  disabled?: boolean;
}

const Button: React.FC<ButtonProps> = ({
  label,
  onClick,
  type = 'button',
  style,
  disabled = false,
}) => {
  return (
    <button
      className={styles.button}
      onClick={onClick}
      type={type}
      style={style}
      disabled={disabled}
    >
      {label}
    </button>
  );
};

export default Button;
