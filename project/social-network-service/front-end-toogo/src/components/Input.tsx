import styled from "styled-components";
import { LuEyeOff, LuEye } from "react-icons/lu";

import React from "react";
import Button from "./Button";

type InputProps = {
  placeholder: string;
  type: string;
  value: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  size: string;
  color?: string;
  variant?: "default" | "eyeIcon" | "button";
  name?: string;
  required?: boolean;
  maxLength?: number;
  onButtonClick?: (event: React.FormEvent<Element>) => void;
};

const sizeHandler = (size: InputProps["size"]) => {
  switch (size) {
    case "signup":
      return `width: 384px; height: 46px; margin-bottom: 16px;`;
    case "nicknameChange":
      return `width: 384px; height: 46px; margin-bottom: 8px;`;
    case "postTitle":
      return `width: 100%; height: 70px; margin: 40px 0`;
    case "comment":
      return `width:100%; height: 80px; margin: 0 24px; font-size: 20px;`;
    case "editComment":
      return `width:100%; height: 50px; font-size: 20px; margin-bottom : 20px `;
    case "postContents":
      return `width: 100%; height: 190px; margin: 40px 0`;
    default:
      return `width: 384px; height: 46px;`;
  }
};

const Input: React.FC<InputProps> = ({
  placeholder,
  type,
  value,
  onChange,
  size,
  variant = "default",
  name,
  color,
  required,
  maxLength,
  onButtonClick,
}) => {
  const [showPassword, setShowPassword] = React.useState(false);
  // 일반 인풋
  const renderInput = () => (
    <InputContainer size={size} color={color}>
      <InputField
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
    </InputContainer>
  );

  // 눈 버튼이 들어간 인풋
  const renderEyeIcon = () => (
    <InputContainer size={size} color={color}>
      <InputField
        type={showPassword ? "text" : type} // Use "text" when showPassword is true
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
      {/* Toggle showPassword state on icon click */}
      {showPassword ? (
        <CustomEye onClick={() => setShowPassword(false)} />
      ) : (
        <CustomEyeOffIcon onClick={() => setShowPassword(true)} />
      )}
    </InputContainer>
  );

  // 인풋안에 버튼
  const renderButton = () => (
    <InputContainer size={size} color={color}>
      <InputField
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
      {onButtonClick && (
        <CustomButton
          color={value ? "on" : "negative"}
          margin="0 6px 0 0"
          onClick={onButtonClick}
          size={"small"}
          name={name}
        />
      )}
    </InputContainer>
  );

  const renderVariant = () => {
    switch (variant) {
      case "eyeIcon":
        return renderEyeIcon();
      case "button":
        return renderButton();
      default:
        return renderInput();
    }
  };

  return renderVariant();
};

export default Input;

const InputContainer = styled.div<{ size: string; color?: string }>`
  ${({ size }) => sizeHandler(size)};
  border: 1px solid ${({ color }) => color};
  border-radius: 8.53px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative;
  /* box-shadow: 3px 0px 15px #c1c1c1; */
  &:focus-within {
    border-color: #2bde97; /* 포커스 시 보더 컬러 변경 */
  }
  
`;

const InputField = styled.input`
  margin-left: 15px;
  font-size: 16px;
  color: #403f4e;
  border: none;
  outline: none;
  flex: 1;
  &::placeholder {
    color: #dddce3;
  }
  &:focus::placeholder {
    opacity: 0;
  }
`;

const CustomEyeOffIcon = styled(LuEyeOff)`
  cursor: pointer;
  color: #cfced7;
  margin-right: 16px;
  width: 19.8px;
  height: 17.6px;
`;

const CustomEye = styled(LuEye)`
  cursor: pointer;
  color: #5e5e5e;
  margin-right: 16px;
  width: 19.8px;
  height: 17.6px;
`;

const CustomButton = styled(Button)<{ color?: string }>`
  &:active {
    background-color: ${({ theme, color }) =>
      color === "#ffffff" ? theme.color.grey : "#ffffff"};
    color: ${({ theme, color }) =>
      color === "#ffffff" ? "black" : theme.color.grey};
  }
`;
