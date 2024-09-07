import React from "react";
import { styled } from "styled-components";
import backgroundImage1 from "../../../img/태국.webp";
import backgroundImage2 from "../../../img/영국.webp";
import backgroundImage3 from "../../../img/모로코.webp";
import backgroundImage4 from "../../../img/미국.webp";
import backgroundImage5 from "../../../img/호주.webp";
import Button from "../../../components/Button";
import { useQuery } from "react-query";
import { getCountrySum } from "../../../api/postApi";

import Spinner from "../../../components/Spinner";

interface MainCardRayoutProps {
  backgroundImage: string;
  cardtype: string;
}

const TextHandler = (
  cardtype: MainCardRayoutProps["cardtype"],
  asiaPostCount: number,
  europePostCount: number,
  africaPostCount: number,
  americaPostCount: number,
  oceaniaPostCount: number
) => {
  let cardName = "";
  let cardDescription = "";

  switch (cardtype) {
    case "아시아":
      cardName = "아시아";
      cardDescription = `${asiaPostCount}여 개의 동행글`;
      break;
    case "유럽":
      cardName = "유럽";
      cardDescription = `${europePostCount}여 개의 동행글`;
      break;
    case "아프리카":
      cardName = "아프리카";
      cardDescription = `${africaPostCount}여 개의 동행글`;
      break;
    case "아메리카":
      cardName = "아메리카";
      cardDescription = `${americaPostCount}여 개의 동행글`;
      break;
    case "오세아니아":
      cardName = "오세아니아";
      cardDescription = `${oceaniaPostCount}여 개의 동행글`;
      break;
    default:
      cardName = "아시아";
      cardDescription = `${asiaPostCount}여 개의 동행글`;
  }

  return { cardName, cardDescription };
};

const BackgroundHandler = (cardtype: MainCardRayoutProps["cardtype"]) => {
  switch (cardtype) {
    case "아시아":
      return backgroundImage1;
    case "유럽":
      return backgroundImage2;
    case "아프리카":
      return backgroundImage3;
    case "아메리카":
      return backgroundImage4;
    case "오세아니아":
      return backgroundImage5;
    default:
      return "";
  }
};

function MainCard({ cardtype }: MainCardRayoutProps) {
  const { isLoading, isError, data } = useQuery("CountrySum", getCountrySum, {
    refetchOnWindowFocus: false,
  });
  const { cardName, cardDescription } = TextHandler(
    cardtype,
    data?.asiaPostCount || 0,
    data?.europePostCount || 0,
    data?.africaPostCount || 0,
    data?.americaPostCount || 0,
    data?.oceaniaPostCount || 0
  );
  const backgroundImage = BackgroundHandler(cardtype);

  return (
    <MainCardRayout backgroundImage={backgroundImage} cardtype={cardtype}>
      <CardBack>
        <CardContent>
          <CardTitle>{cardName}</CardTitle>
          <CardDescription>{cardDescription}</CardDescription>

          <Button
            color={"mainCardBtn"}
            margin={" 0 0 0 61px"}
            size={"main"}
            name={"둘러보기"}
          />
        </CardContent>
      </CardBack>
    </MainCardRayout>
  );
}

export default MainCard;

const CardBack = styled.div`
  width: 282px;
  height: 376px;
  flex-grow: 0;

  border-radius: 8px;
  background-image: linear-gradient(to top, rgba(0, 0, 0, 0) 33%, #000 100%);
`;

const MainCardRayout = styled.div<MainCardRayoutProps>`
  width: 282px;
  height: 376px;
  background-image: url(${(props) => props.backgroundImage});
  background-size: cover;
  background-position: center;
  border-radius: 8px;
`;

const CardContent = styled.div`
  padding: 20px;
  color: white;
`;

const CardTitle = styled.h2`
  width: 184px;
  height: 36px;
  margin: 10px 1px 12px 0;
  font-size: 24px;
  margin-bottom: 10px;
  font-family: Pretendard;
  font-size: 30px;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #fff;
`;

const CardDescription = styled.p`
  width: 174px;
  height: 24px;
  margin: 12px 11px 193px 0;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #fff;
`;
