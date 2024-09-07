import React, { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { styled } from "styled-components";
import { RootState } from "../types/login";
import "../fonts/Font.css";
import { LuSearch } from "react-icons/lu";
import { HiOutlineBell } from "react-icons/hi";
import { RxAvatar } from "react-icons/rx";
import HeaderSelect from "./HeaderSelect";
import BudgetModal from "./BudgetModal";
import { Badge } from "@mui/material";
import { useRecoilState } from "recoil";
import { eventDataListState } from "../recoil/Alert";

function Header() {
  const navigate = useNavigate();
  const [eventDataList, setEventDataList] = useRecoilState(eventDataListState);
  const [isSelectOpen, setIsSelectOpen] = useState<boolean>(false);
  const [budgetOpen, setBudgetOpen] = useState<boolean>(false);
  const state = useSelector((state: RootState) => state.isLogin.isLogin);
  const [keyword, setKeyword] = useState<string>("");

  const node = useRef<HTMLDivElement | null>(null); // 창의 바깥부분을 클릭하였을때 창이 사라짐
  useEffect(() => {
    const clickOutside = (e: MouseEvent) => {
      if (
        isSelectOpen &&
        node.current &&
        !node.current.contains(e.target as Node)
      )
        setIsSelectOpen(false);
    };
    document.addEventListener("mousedown", clickOutside);
    return () => {
      document.removeEventListener("mousedown", clickOutside);
    };
  }, [isSelectOpen]);

  const handleBox2Click = () => {
    setIsSelectOpen(!isSelectOpen);
  };
  // --------------------------------------------------------------------------------

  const buge = useRef<HTMLDivElement | null>(null); // 창의 바깥부분을 클릭하였을때 창이 사라짐
  useEffect(() => {
    const clickOutside = (e: MouseEvent) => {
      if (
        budgetOpen &&
        buge.current &&
        !buge.current.contains(e.target as Node)
      )
        setBudgetOpen(false);
    };
    document.addEventListener("mousedown", clickOutside);
    return () => {
      document.removeEventListener("mousedown", clickOutside);
    };
  }, [budgetOpen]);

  const budgetBoxClick = () => {
    setBudgetOpen(!budgetOpen);
  };

  // --------------------------------------------------------------------------------
  const handleSearchButtonClick = () => {
    navigate(`/searchpage/?keyword=${keyword}`);
  };

  const handleEnterKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      handleSearchButtonClick();
    }
  };

  return (
    <HeaderContainer>
      <HeaderContainer2>
        <Logo onClick={() => navigate("/")}>오이여행</Logo>
        <SearchContainer>
          <SearchInput
            type="text"
            placeholder="검색어를 입력해주세요."
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            onKeyPress={handleEnterKeyPress}
          />
          <SearchButton onClick={handleSearchButtonClick}>
            <LuSearch color="white" size="17px" />
          </SearchButton>
        </SearchContainer>
        {state ? (
          <LoginConditionButtons>
            <div ref={buge}>
              <Bell onClick={budgetBoxClick}>
                <Badge color="error" badgeContent={eventDataList.length}>
                  <HiOutlineBell color="#403F4E" size="24px" />
                </Badge>
              </Bell>
              <BudgetModal position={"absolute"} budgetOpen={budgetOpen} />
            </div>
            <div ref={node}>
              <Profile onClick={handleBox2Click}>
                <RxAvatar color="#403F4E" size="26px" />
              </Profile>
              <HeaderSelect position={"absolute"} isSelectOpen={isSelectOpen} />
            </div>
          </LoginConditionButtons>
        ) : (
          <LogoutConditionButtons>
            <SignupButton onClick={() => navigate("/signup")}>
              회원가입
            </SignupButton>
            <Line>|</Line>
            <LoginButton onClick={() => navigate("/login")}>로그인</LoginButton>
          </LogoutConditionButtons>
        )}
      </HeaderContainer2>
    </HeaderContainer>
  );
}

export default Header;

const HeaderContainer = styled.header`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100px;
  background-color: white;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  border-color: #d1d5db;
`;

const HeaderContainer2 = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  max-width: 1200px;
`;

const Logo = styled.button`
  background-color: white;
  border: none;
  color: #2bde97;
  font-family: "Cafe24 Ssurround";
  font-size: 30px;
  font-weight: 700;
`;

const SearchContainer = styled.div`
  width: 334px;
  height: 48px;
  display: flex;
  align-items: center;
  position: relative;
  gap: 16px;
  border-radius: 1000px;
  border: 1px solid #cfced7;
  background: white;
  &:focus-within {
    border-color: #2bde97; /* 포커스 시 보더 컬러 변경 */
  }
`;

const SearchInput = styled.input`
  width: 254px;
  height: 20px;
  margin-left: 24px;
  border: none;
  font-family: "Pretendard";
  font-size: 14px;
  font-weight: 500;
  outline: none;
  &::placeholder {
    color: #dddce3;
  }
  &:focus::placeholder {
    opacity: 0;
  }
`;

const SearchButton = styled.button`
  position: absolute;
  right: 8px;
  width: 32px;
  height: 32px;
  background-color: #2bde97;
  border: none;
  border-radius: 1000px;
  padding-bottom: 4px;
`;

const LogoutConditionButtons = styled.div`
  display: flex;
  gap: 10px;
`;

const SignupButton = styled.button`
  background-color: white;
  border: none;
  font-family: "Pretendard";
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
`;

const LoginButton = styled.button`
  background-color: white;
  border: none;
  color: #9a9a9a;
  font-family: "Pretendard";
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
`;

const Line = styled.div`
  color: #9a9a9a;
  font-family: "Pretendard";
  font-size: 16px;
  font-weight: 400;
  cursor: default;
`;

const LoginConditionButtons = styled.div`
  width: 90px;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  gap: 10px;
  padding: 8px 8px 8px 8px;
  border-radius: 1000px;
  border: 1px solid #cfced7;
  background: white;
`;

const Bell = styled.button`
  border: none;
  padding: 0px;
  background-color: white;
  &:hover {
    transform: scale(1.05);
  }
`;

const DM = styled.button`
  border: none;
  padding: 0px;
  background-color: white;
  margin-top: -5px;
  &:hover {
    transform: scale(1.05);
  }
`;

const Profile = styled.button`
  border: none;
  padding: 0px;
  background-color: white;
  &:hover {
    transform: scale(1.05);
  }
`;
