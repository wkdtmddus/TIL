import React, { useState } from "react";
import { styled } from "styled-components";
import categorymarker from "../../../img/categorymarker.jpg";

const SelectCountry = ({
  id,
  onClick,
}: {
  id: number;
  onClick: (selectedValue: string) => void;
}) => {
  const countrySelect = (category: number) => {
    switch (category) {
      case 1:
        return [
          "한국",
          "일본",
          "홍콩",
          "대만",
          "중국",
          "몽골",
          "싱가포르",
          "베트남",
          "태국",
          "인도네시아",
          "말레이시아",
          "필리핀",
          "라오스",
          "캄보디아",
          "미얀마",
          "아랍에미리트",
          "인도",
          "네팔",
          "이스라엘",
          "카타르",
        ];
      case 2:
        return [
          "이집트",
          "남아프리카공화국",
          "탄자니아",
          "에티오피아",
          "케냐",
          "나미비아",
          "모로코",
        ];
      case 3:
        return [
          "프랑스",
          "이탈리아",
          "터키",
          "스페인",
          "영국",
          "오스트리아",
          "네덜란드",
          "독일",
          "스위스",
          "포르투갈",
          "폴란드",
          "아이슬란드",
          "핀란드",
          "스웨덴",
          "노르웨이",
          "덴마크",
          "그리스",
          "러시아",
          "아일랜드",
          "헝가리",
          "벨기에",
          "체코",
          "슬로베니아",
        ];
      case 4:
        return ["호주", "뉴질랜드", "괌", "하와이"];
      case 5:
        return [
          "미국",
          "캐나다",
          "멕시코",
          "페루",
          "볼리비아",
          "칠레",
          "아르헨티나",
          "쿠바",
          "브라질",
        ];
      default:
        return [];
    }
  };

  const handleClick = (selectedValue: string) => {
    onClick(selectedValue);
  };

  return (
    <div>
      <SelectContainer>
        {countrySelect(id).map((item, index) => (
          <StSelects key={index} onClick={() => handleClick(item)}>
            <img src={categorymarker} />
            <StCountry>{item}</StCountry>
          </StSelects>
        ))}
      </SelectContainer>
    </div>
  );
};

export default SelectCountry;

const SelectContainer = styled.div`
  width: 100%;
  border: 1px solid #cfced7;
  border-radius: 8.53px;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  font-size: 20px;
  box-sizing: border-box;
  padding: 32px;
`;

const StSelects = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  width: 260px;
  margin: 8px 0;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;

  &:hover {
    background-color: #f0f0f0;
  }
`;
const StCountry = styled.div``;
