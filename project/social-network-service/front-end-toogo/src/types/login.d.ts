
// 회원가입
export type SignupFormValues = {
  email : string;
  nickname: string;
  password: string;
  code: string;
}

// 로그인
export type LoginFormValues = {
  email : string;
  password: string;
}

// 이메일 중복확인

// 닉네임 중복확인

export type RootState = {
  isLogin: {
    isLogin: boolean;
  }
}