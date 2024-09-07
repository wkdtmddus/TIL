import React, { useEffect, useState } from "react";
import { useMutation, useQuery } from "react-query";
import { editPost, getDetailPosts } from "../../api/postApi";
import { locationFormValues, postFormValues } from "../../types/posts";
import { useNavigate, useParams } from "react-router-dom";
import Map from "../Post/PostComponents/Map";
import { styled } from "styled-components";
import Input from "../../components/Input";
import Button from "../../components/Button";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import NavigationBox from "../Post/PostComponents/NavigationBox";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  selectedCountryState,
  selectedDateState,
  sliderValueState,
} from "../../recoil/NavigationBar";

function EditPost() {
  const param = useParams().id;
  let category = "";
  let postId = "";
  const { isLoading, isError, data } = useQuery(
    ["detailPost", category, postId],
    () => getDetailPosts(+category, +postId)
  );
  const [title, setTitle] = useState(data.title);
  const [contents, setContents] = useState(data.contents);
  const [showAlert, setShowAlert] = useState(false);
  const [showContentsAlert, setShowContentsAlert] = useState(false);
  if (param?.includes("&")) {
    [category, postId] = param.split("&");
  }
  const selectedCountry = useRecoilValue(selectedCountryState);
  const [, setFormattedDate] = useRecoilState(selectedDateState);
  const [, setSelectedCountry] = useRecoilState(selectedCountryState);
  const formattedDate = useRecoilValue(selectedDateState);
  const selectedPeple = useRecoilValue(sliderValueState);
  const [, setSelectedPeple] = useRecoilState(sliderValueState);

  const [MarkerPosition, setMarkerPosition] =
    useState<null | locationFormValues>(null);
  const [latitudeMarkerPosition, setLatitudeMarkerPosition] =
    useState<number>(0);
  const [longitudeMarkerPosition, setLongitudeMarkerPosition] =
    useState<number>(0);
  const navigate = useNavigate();

  const handleMarkerPositionChange = (newPosition: locationFormValues) => {
    if (newPosition) {
      setLatitudeMarkerPosition(newPosition.latitude);
      setLongitudeMarkerPosition(newPosition.longitude);
      setMarkerPosition(newPosition);
    }
  };
  // 제목 30자 이상 안넘어가게하는 핸들러
  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newTitle = event.target.value;

    if (newTitle.length <= 20) {
      setTitle(newTitle);
      setShowAlert(false);
    } else {
      setShowAlert(true);
    }
  };

  const handleContentsChange = (
    event: React.ChangeEvent<HTMLTextAreaElement>
  ) => {
    const newContents = event.target.value;

    if (newContents.length <= 1000) {
      setContents(newContents);
      setShowContentsAlert(false);
    } else {
      setShowContentsAlert(true);
    }
  };
  // ----------------------------------------게시글 수정
  const editPostMutation = useMutation(
    (postData: postFormValues) => editPost(+category, +postId, postData),
    {
      onSuccess: () => {
        navigate(-1);
        setFormattedDate("");
        setSelectedCountry("");
      },
    }
  );

  const handleEditPost = (event: React.FormEvent) => {
    event.preventDefault();

    const postData: postFormValues = {
      title,
      contents,
      people: selectedPeple,
      country: selectedCountry,
      meetDate: formattedDate,
      latitude: latitudeMarkerPosition,
      longitude: longitudeMarkerPosition,
    };

    editPostMutation.mutate(postData);
  };
  useEffect(() => {
    setFormattedDate("");
    setSelectedCountry("");
  }, []);

  return (
    <div>
      <Header />
      <NavigationBox id={+category} />
      <Layout>
        <StInputLabel>제목</StInputLabel>
        <Input
          type="text"
          placeholder="제목을 입력해주세요"
          value={title}
          onChange={handleTitleChange}
          size={"postTitle"}
          color={"#cfced7"}
          maxLength={20}
        />
        {showAlert && (
          <div style={{ color: "red" }}>20자 이내로 작성해 주세요.</div>
        )}

        <StInputLabel>내용</StInputLabel>
        <ContentInput
          placeholder="내용을 입력해주세요"
          value={contents}
          onChange={handleContentsChange}
          maxLength={1000}
        />
        {showContentsAlert && (
          <div style={{ color: "red" }}>1000자 이내로 작성해 주세요.</div>
        )}
        <StInputLabel>위치</StInputLabel>
        <Map
          onMarkerPosition={MarkerPosition}
          onMarkerPositionChange={handleMarkerPositionChange}
        />

        <StButtonSet>
          <Button
            name={"취소"}
            size={"post"}
            color={"negative"}
            onClick={() => navigate(-1)}
          />
          <Button
            name={"작성완료"}
            size={"post"}
            color={""}
            onClick={handleEditPost}
          />
        </StButtonSet>
      </Layout>
      <Footer />
    </div>
  );
}

export default EditPost;

const ContentInput = styled.textarea`
  width: 1200px;
  height: 180px;
  font-size: 16px;
  margin: 40px auto 60px;
  padding-left: 20px;
  padding-top: 20px;
  border-radius: 8px;
  border: solid 1px #cfced7;
  background-color: #fff;
  outline: none;
  color: #484848;
  &::placeholder {
    color: #dddce3;
  }
`;

const Layout = styled.div`
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
`;
const StCalendar = styled.div`
  width: 100%;
`;

const StInputLabel = styled.div`
  padding-top: 20px;
  font-size: 28px;
  color: #484848;
`;
const StButtonSet = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 80px 0 120px 0;
`;
