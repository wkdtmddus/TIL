import React, { useState } from "react";
import { css, styled } from "styled-components";
import { LoginFormValues } from "../../types/login";
import { useNavigate } from "react-router-dom";
import useInput from "../../hooks/useInput";
import { useMutation } from "react-query";
import { login } from "../../api/userApi";
import Button from "../../components/Button";
import Input from "../../components/Input";
import Header from "../../components/Header";
import { useDispatch } from "react-redux";
import { logIn } from "../../redux/modules/loginSlice";
import Footer from "../../components/Footer";
import { useRecoilState } from "recoil";
import { isLoggedInState } from "../../recoil/Auth";

type ButtonProps = {
  backgroundColor?: string;
  fontColor?: string;
  fontWeight?: string;
};

function Login() {
  const [email, handleEmailChange] = useInput();
  const [password, handlePasswordChange] = useInput();
  const [emailCheck, setEmailCheck] = useState<boolean | string>(false);
  const [passwordCheck, setPasswordCheck] = useState<boolean | string>(false);
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInState);

  // ----------------------------------------로그인 로직
  const loginMutation = useMutation(login, {
    onError: (error: any) => {
      if (error.response && error.response.status === 400) {
        const errorMsg = error.response.data.msg;
        alert(errorMsg);
      } else {
        console.error("An error occurred:", error);
      }
    },
    onSuccess: () => {
      dispatch(logIn());
      navigate("/");
      setIsLoggedIn(true);
    },
  });

  const loginHandler = (event: React.FormEvent) => {
    event.preventDefault();
    if (!emailRegex.test(email)) {
      setEmailCheck("유효한 이메일 주소를 입력해주세요.");
      return;
    }
    if (password.length == 0) {
      setPasswordCheck("비밀번호를 입력해주세요.");
      return;
    }
    const loginInformation: LoginFormValues = {
      email,
      password,
    };
    loginMutation.mutate(loginInformation);
  };

  const location = window.location.origin;

  // ----------------------------------------카카오 로그인
  const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
  const REDIRECT_URI = `${location}/api/auth/kakao`;
  const link = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  const kakaoLoginHandler = () => {
    window.location.href = link;
  };

  // 입력값에 따라 로그인 버튼의 색상을 업데이트하는 함수
  const updateLoginButtonColor = () => {
    if (email && password) {
      return "loginOn"; // 두 입력값이 모두 채워져 있을 때
    } else {
      return "negative"; // 두 입력값 중 하나라도
    }
  };

  return (
    <>
      <Header />
      <CenteredContainer>
        <LoginLayout>
          <LoginText>로그인</LoginText>
          <LoginForm onSubmit={loginHandler}>
            <Label>이메일</Label>
            <Input
              type="text"
              placeholder="이메일을 입력하세요"
              value={email}
              onChange={handleEmailChange}
              size={"signup"}
              color={emailCheck ? "#E32D2D" : "#cfced7"}
              variant={"default"}
            />
            {emailCheck && <StCheckMassage>{emailCheck}</StCheckMassage>}
          </LoginForm>
          <LoginForm onSubmit={loginHandler}>
            <Label>비밀번호</Label>
            <Input
              type="password"
              placeholder="비밀번호를 입력하세요"
              value={password}
              onChange={handlePasswordChange}
              size={"signup"}
              color={passwordCheck ? "#E32D2D" : "#cfced7"}
              variant={"eyeIcon"}
            />
            {passwordCheck && <StCheckMassage>{passwordCheck}</StCheckMassage>}
          </LoginForm>

          <LoginButton>
            <Button
              color={updateLoginButtonColor()}
              onClick={loginHandler}
              margin={"0 0 16px 0"}
              size={"large"}
              name={"로그인"}
            />

            <Button
              color={"kakaoLogin"}
              onClick={kakaoLoginHandler}
              size={"large"}
              name={"Kakao로 시작하기"}
              kakao={true}
            />
          </LoginButton>

          <LoginAccountText>
            <IdText onClick={() => navigate("/findPassword")}>
              비밀번호찾기
            </IdText>
            <AccountLine>|</AccountLine>
            <IdText onClick={() => navigate("/signup")}>회원가입</IdText>
          </LoginAccountText>
        </LoginLayout>
      </CenteredContainer>
      <Footer />
    </>
  );
}

export default Login;

const CenteredContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 200px auto 200px auto;
`;

const LoginLayout = styled.div`
  text-align: center;
`;

const LoginText = styled.div`
  font-family: Pretendard;
  font-size: 32px;
  font-weight: 900;
  line-height: 1;
  letter-spacing: 0.96px;
  margin-bottom: 40px;
  color: #403f4e;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  
`;

const Label = styled.label`
  align-self: flex-start;
  margin-bottom: 8px;
  font-size: 16px;
  font-family: Pretendard;
  color: #403f4e;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: 1;
  letter-spacing: normal;
`;

const StCheckMassage = styled.div`
  font-size: 14px;
  margin: 0 auto 16px 0;
  color: red;
`;

const LoginButton = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 40px;
`;

const LoginAccountText = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 24px;
`;

const IdText = styled.div`
  cursor: pointer;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: 1;
  letter-spacing: normal;
  text-align: right;
  color: #403f4e;
`;

const AccountLine = styled.span`
  width: 16px;
  height: 16px;
  margin-left: 8.5px;
  margin-right: 8.5px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: 1;
  letter-spacing: normal;
  color: #636363;
`;
