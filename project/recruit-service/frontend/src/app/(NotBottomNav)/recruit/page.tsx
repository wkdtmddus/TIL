'use client'

import React, { useState, useEffect, useRef } from "react";
import axios from 'axios';
import InputField from "@/app/components/Input/InputField";
import InputAreaField from "@/app/components/Input/InputAreaField";
import Button from '../../components/Button/Button';
import { useAnnouncementStore } from '@/app/store/useAnnouncementStore';
import { useRouter } from 'next/navigation';
import Header from "@/app/components/Header/Header";
import styles from "./announcement.module.css";
import '@/app/typography.css';
import CalendarModal from "@/app/components/calendar/CalendarModal";
import TimePickerDrawer from "@/app/components/calendar/TimePickerDrawer";
import AddressSearchModal from "@/app/components/address/AddressSearchModal ";
import apiImg from '../../../../pages/api/apiImg'
import RecruitPosterModal from "@/app/components/Ocr/RecruitPosterModal"; // Import the modal component
import OpenAI from 'openai';

const categories = [
  { name: '백화점', icon: '/image/drop-icon.png', activeIcon: '/image/drop-active-icon.png' },
  { name: '음식점', icon: '/image/drop2-icon.png', activeIcon: '/image/drop2-active-icon.png' },
  { name: '팝업스토어', icon: '/image/drop3-icon.png', activeIcon: '/image/drop3-active-icon.png' },
  { name: '카페', icon: '/image/drop4-icon.png', activeIcon: '/image/drop4-active-icon.png' },
];

const serviceTypes = [
  { name: '줄서기 대행', icon: '/image/line-up-black.png', activeIcon: '/image/line-up.png' },
  { name: '구매 대행', icon: '/image/line-up-black.png', activeIcon: '/image/line-up.png' },
];

const times = [
  "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
  "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
  "18:00", "19:00", "20:00", "21:00", "22:00", "23:00",
];

export default function Announcement() {
  const {
    title,
    successReward,
    failureReward,
    address,
    category,
    detailContent,
    serviceType,
    contracteeDeposit,
    announceImg,
    startDate,
    endDate,
    startTime,
    endTime,
    district, //구
    latitude,
    longitude,
    setTitle,
    setSuccessReward,
    setFailureReward,
    setAddress,
    setCategory,
    setDetailContent,
    setServiceType,
    setContracteeDeposit,
    setAnnounceImg,
    setStartDate,
    setEndDate,
    setStartTime,
    setEndTime,
    setDistrict,
    reset,
  } = useAnnouncementStore();

  const [isFormValid, setIsFormValid] = useState(false);
  const [previewImage, setPreviewImage] = useState<string | null>(null); // string 또는 null 타입 설정
  const [isStartTimeDropdownOpen, setIsStartTimeDropdownOpen] = useState(false);
  const [isEndTimeDropdownOpen, setIsEndTimeDropdownOpen] = useState(false);

  const router = useRouter();

  const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;

  useEffect(() => {
    if (announceImg) {
      const imageUrl = URL.createObjectURL(announceImg);
      setPreviewImage(imageUrl);
    }
  }, [announceImg]);

  const handleLineupLocationClick = () => {
    router.replace('/announcement/placesearch');
  };

  useEffect(() => {
    setIsFormValid(
      title.trim().length > 0 &&
      successReward.trim().length > 0 &&
      failureReward.trim().length > 0 &&
      address.trim().length > 0 &&
      category.trim().length > 0 &&
      detailContent.trim().length > 0 &&
      serviceType.trim().length > 0 &&
      contracteeDeposit.trim().length > 0 &&
      startDate.trim().length > 0 &&
      endDate.trim().length > 0 &&
      startTime.trim().length > 0 &&
      endTime.trim().length > 0
    );
  }, [title, successReward, failureReward, address, category, detailContent,
    serviceType, contracteeDeposit, announceImg, startDate, endDate, startTime, endTime]);


  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!isFormValid) {
      alert("모든 필드를 올바르게 입력해주세요.");
      return;
    }

    try {
      // recruitFormRequest JSON 데이터 생성
      const recruitFormRequest = {
        title,
        content: detailContent,
        placeType: category,
        serviceType,
        startDate,
        startAt: startTime,
        endDate,
        endAt: endTime,
        location: {
          streetAddress: address,
          district,
          latitude: parseFloat(latitude),
          longitude: parseFloat(longitude),
        },
        successSalary: parseInt(successReward),
        failSalary: parseInt(failureReward),
        contracteeDeposit: parseInt(contracteeDeposit),
        // recruitImg: announceImg ? null : "", 
      };

      console.log(recruitFormRequest)

      // FormData 객체 생성
      const formData = new FormData();

      // recruitFormRequest JSON 파트 추가
      formData.append('recruitFormRequest', new Blob([JSON.stringify(recruitFormRequest)], { type: 'application/json' }));

      // 이미지 파일 파트 추가 (있을 경우에만)
      if (announceImg) {
        formData.append('recruitImg', announceImg);
      }

      // //데이터 확인
      // formData.forEach((value, key) => {
      //   console.log(`${key}:`, value);

      //   // 만약 value가 Blob인 경우 내용을 읽어서 출력
      //   if (value instanceof Blob) {
      //     const reader = new FileReader();

      //     reader.onload = () => {
      //       console.log(`Blob Content for ${key}:`, reader.result);
      //     };

      //     reader.readAsText(value); // Blob을 텍스트로 읽음 (JSON 데이터일 경우)
      //     // reader.readAsDataURL(value); // Base64로 읽을 경우 이 메서드 사용
      //   }
      // });

      // API 요청
      const response = await apiImg.post(`${baseURL}/recruits`, formData, {

      });

      // 성공적인 응답 처리
      console.log('공고 등록 성공:', response.data);
      console.log(response.data)
      // 홈으로 리디렉션
      router.replace(`/recruit/${response.data}`); // 또는 원하는 경로로 리디렉션

    } catch (error) {
      console.error('제출 실패:', error);
      alert('공고 등록 중 오류가 발생했습니다.');
    }
  };

  const handleHomeClick = () => {
    reset();
  };

  const handleCategorySelect = (categoryName: string) => {
    setCategory(categoryName);
  };

  const handleServiceTypeSelect = (serviceTypeName: string) => {
    setServiceType(serviceTypeName);
  };

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setAnnounceImg(file);
      setPreviewImage(imageUrl);
    }
  };
  const [fileInputKey, setFileInputKey] = useState(Date.now()); // 파일 업로드를 위한 key

  const handleRemoveImage = () => {
    setAnnounceImg(null);
    setPreviewImage(null);
    setFileInputKey(Date.now()); // input 리셋을 위해 key를 변경
  };

  const [isStartCalendarOpen, setIsStartCalendarOpen] = useState(false);
  const [isEndCalendarOpen, setIsEndCalendarOpen] = useState(false);

  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [isAddressSearchOpen, setIsAddressSearchOpen] = useState(false);

  const handleStartCalendarOpen = () => {
    setIsStartCalendarOpen(true);
  };

  const handleEndCalendarOpen = () => {
    setIsEndCalendarOpen(true);
  };

  const handleCalendarClose = () => {
    setIsStartCalendarOpen(false);
    setIsEndCalendarOpen(false);
  };

  const handleCalendarConfirm = (selectedDate: Date | null) => {
    // setSelectedDate(selectedDate);
    setIsStartCalendarOpen(false);
    setIsEndCalendarOpen(false);
  };

  const handleAddressSearchOpen = () => setIsAddressSearchOpen(true);
  const handleAddressSearchClose = () => setIsAddressSearchOpen(false);



  // Scroll lock when modals are open
  useEffect(() => {
    if (isStartCalendarOpen || isEndCalendarOpen || isAddressSearchOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }

    // Clean up the overflow property when component is unmounted
    return () => {
      document.body.style.overflow = 'auto';
    };
  }, [isStartCalendarOpen, isEndCalendarOpen, isAddressSearchOpen]);


  const handleStartTimeSelect = (time: string) => {
    setStartTime(time);
    setIsStartTimeDropdownOpen(false);
  };

  const handleEndTimeSelect = (time: string) => {
    setEndTime(time);
    setIsEndTimeDropdownOpen(false);
  };

  const handleNumericChange = (setter: (value: string) => void) => (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    // 입력값이 비어 있거나 숫자로만 구성되어 있는 경우에만 상태를 업데이트
    if (value === '' || /^[0-9]+$/.test(value)) {
      setter(value);
    }
  };


  //포스터 모달



  const [isUploading, setIsUploading] = useState(false); // State to control modal visibility
  const [ocrResult, setOcrResult] = useState<string | null>(null); // OCR result state
  const [loading, setLoading] = useState(false); // Loading state for OCR
  const [error, setError] = useState(''); // Error state for OCR

  const fileInputRef = useRef<HTMLInputElement>(null);

  // 버튼 클릭 시 파일 인풋을 트리거하고 모달 표시
  const handleUploadButtonClick = () => {
    setIsUploading(true); // 모달을 엽니다
    if (fileInputRef.current) {
      fileInputRef.current.click(); // 파일 선택창을 엽니다
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setPreviewImage(imageUrl);
      setAnnounceImg(file); // 이미지 상태 저장

      // OCR 처리 시작
      handleOcrProcessing(file);
    }

    // 업로드 후 인풋 리셋
    e.target.value = ""; // 파일 인풋 값 초기화
  };


  const handleOcrProcessing = async (file: File) => {
    setLoading(true);
    setError('');
    
    try {
      // Read the file as base64
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = async () => {
        const base64Image = reader.result;
    
        // Step 1: Call the OCR API
        const ocrResponse = await fetch('/api/ocr', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ image: base64Image }),
        });
    
        if (!ocrResponse.ok) {
          throw new Error('Failed to process OCR');
        }
    
        const { inferTexts } = await ocrResponse.json();
        const ocrText = inferTexts.join(' ');
    
        // Step 2: Call the OpenAI API with the OCR text
        const openaiResponse = await fetch('/api/openai', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ ocrText }),
        });
    
        if (!openaiResponse.ok) {
          throw new Error('Failed to fetch OpenAI data');
        }
        
        // Convert response to text and parse it manually to handle any issues
        const responseText = await openaiResponse.text();
        const parsedResponse = JSON.parse(responseText);
        
        // Parse the `data` field which contains the JSON string
        const data = JSON.parse(parsedResponse.data);
  
        // Example extracted data format:
        // data = {
        //   title: "SEOULS AND PARTNERS",
        //   startDate: "2021-11-11",
        //   endDate: "2021-11-21",
        //   location: "서울특별시 마포구 월드컵북로6길88-10",
        //   startTime: "00:00",
        //   endTime: "20:00"
        // }
  
        // Use the data returned from the API route
        // console.log(data);
        // console.log(data.title)
        // Automatically set the extracted data to form fields
        setTitle(data.title || title);
        // setStartDate(data.startDate || startDate);
        // setEndDate(data.endDate || endDate);
        // setStartTime(data.startTime || startTime);
        // setEndTime(data.endTime || endTime);
        setDetailContent(data.detailContent || detailContent);
  
        setIsUploading(false); // Close modal
      };
    } catch (err: any) {
      setError(err.message || 'Error occurred while processing OCR');
      setIsUploading(false); // Close modal on error
    } finally {
      setLoading(false);
    }
  };
  

  // 수동으로 모달 닫기
  const handleCloseModal = () => {
    setIsUploading(false);
  };


  return (
    <div className={styles.announcement}>
      <Header
        imagesSrc="/image/back-icon.png"
        imageRightSrc="/image/threedots-icon.png" // You can provide this but show it false
        altText="홈으로 가기"
        altTextRight="메뉴 열기"
        href="/home"
        hrefRight=""
        navigateType="replace"
        navigateTypeRight="push" // you can have this but won't be used
        title="공고 등록"
        onClick={handleHomeClick}
        showTopLeftButton={true} // This will show the TopLeftButton
        showTopRightButton={false} // Set to false so TopRightButton won't be shown
        onModify={() => { }}  // Can provide dummy functions or leave blank
        onRequestCancel={() => { }}  // Dummy function
      />
      <hr className={styles.divideline}></hr>
      <form onSubmit={handleSubmit}>



        {/* ocr 파일 업로드 */}
        {/* Upload Button */}
        <div className={styles.button}>
        <Button
          label="AI로 공고 자동 등록"
          type="button"
          onClick={handleUploadButtonClick}
        />
        </div>

        <input
          key={fileInputKey} // 파일 업로드 후 리셋을 위해 key를 변경
          type="file"
          accept="image/*"
          ref={fileInputRef}
          style={{ display: 'none' }}
          onChange={handleFileChange}
        />


        {/* Uploading Modal */}
        {isUploading && <RecruitPosterModal isOpen={isUploading} onClose={handleCloseModal} />}

        {/* OCR Loading Indicator */}
        {loading && <p>Processing OCR...</p>}

        {/* Display OCR Results */}
        {ocrResult && (
          <div className={styles.ocrResult}>
            <h3>OCR Result:</h3>
            <pre>{ocrResult}</pre>
          </div>
        )}

        {/* Error Handling */}
        {error && <p style={{ color: 'red' }}>{error}</p>}

        {/* Form submit and other logic here */}





        <div className={styles.categoryInputarea}>
          <div className={styles.locationLabelContainer}>
            <label className="text-large">서비스 종류</label>
          </div>
          <div className={styles.serviceTypeContainer}>
            {serviceTypes.map((serviceTypeItem, index) => (
              <button
                key={index}
                className={`${styles.serviceTypeButton} ${serviceType === serviceTypeItem.name ? styles.active : ''}`}
                onClick={() => handleServiceTypeSelect(serviceTypeItem.name)}
                type="button"
              >
                <img
                  src={serviceType === serviceTypeItem.name ? serviceTypeItem.activeIcon : serviceTypeItem.icon}
                  alt={serviceTypeItem.name}
                  className={styles.categoryIcon}
                />
                {serviceTypeItem.name}
              </button>
            ))}
          </div>
        </div>

        <div className={styles.categoryInputarea}>
          <div className={styles.locationLabelContainer}>
            <label className="text-large">✔ 카테고리</label>
          </div>
          <div className={styles.categoryContainer}>
            {categories.map((categoryItem, index) => (
              <button
                key={index}
                className={`${styles.categoryButton} ${category === categoryItem.name ? styles.active : ''}`}
                onClick={() => handleCategorySelect(categoryItem.name)}
                type="button"
              >
                <img
                  src={category === categoryItem.name ? categoryItem.activeIcon : categoryItem.icon}
                  alt={categoryItem.name}
                  className={styles.categoryIcon}
                />
                {categoryItem.name}
              </button>
            ))}
          </div>
        </div>
        <div className={`${styles.locationInputarea}`}>
          <div className={styles.locationLabelContainer}>
            <label className="text-large">🖼️ 사진 (선택)</label>
            <br />
            <div style={{ marginTop: '5px', }}>
              <label className="text-small">해당 라인업과 관련된 사진을 올려보세요</label>
            </div>
          </div>
        </div>
        <div className={styles.imageUploadContainer}>
          {previewImage && (
            <div className={styles.imagePreview}>
              <img src={previewImage} alt="Uploaded preview" className={styles.previewImg} />
              <img src="/image/upload-cancel.png" alt="" className={styles.removeButton} onClick={handleRemoveImage} />
            </div>
          )}
          <input
            key={fileInputKey} // input 리셋을 위해 key 추가
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            id="file"
            className={styles.uploadInput}
          />
          {!previewImage && (
            <label htmlFor="file" className={styles.uploadButton}>
              <img src="/image/upload-icon.png" alt="Upload" className={styles.uploadIcon} />
            </label>
          )}
        </div>

        {/* <div className={styles.inputarea}>
          <InputField
            label="🛠 서비스 종류"
            placeholder="서비스 종류를 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={serviceType}
            onChange={setServiceType}
          />
        </div> */}
        <div className={styles.inputarea}>
          <InputField
            label="📢 제목"
            placeholder="제목을 입력해주세요. (15자~20자 사이)"
            required
            errorMessage="필수로 입력해주세요."
            value={title}
            onChange={setTitle}
          />
        </div>









        {/* <div className={`${styles.inputarea} ${styles.locationInputarea}`}>
          <div className={styles.locationLabelContainer}>
            <label className="text-large">🏃‍♂️ 라인업 위치</label>
          </div>
          <input
            className={styles.locationInput}
            placeholder="어디에서 라인업 하나요?"
            value={address}
            onClick={handleLineupLocationClick}
            readOnly
          />
          <img
            src='/image/location-icon.png'
            alt="라인업 위치"
            className={styles.icon}
          />
        </div> */}

        {/* 라인업 위치 입력 */}
        <div className={`${styles.inputarea} ${styles.locationInputarea}`}>
          <div className={styles.locationLabelContainer}>
            <label className="text-large">🏃‍♂️ 라인업 위치</label>
          </div>
          <input
            className={styles.locationInput}
            type="text"
            value={address} // Display selected address from Zustand
            placeholder="어디에서 라인업 하나요?"
            readOnly
            onClick={handleAddressSearchOpen} // Open address modal
          />
          <img
            src='/image/location-icon.png'
            alt="라인업 위치"
            className={styles.icon}
          />
          {isAddressSearchOpen && (
            <AddressSearchModal
              onClose={handleAddressSearchClose}
            />
          )}
        </div>


        <div className={styles.dateContainer}>
          <div className={`${styles.inputarea} ${styles.DateInputarea}`}>
            <div className={styles.DateLabelContainer}>
              <label className="text-large">📅 라인업 날짜</label>
            </div>
            <input
              className={styles.DateInput}
              placeholder="시작"
              value={startDate} // Display the selected date from Zustand store
              onClick={handleStartCalendarOpen} // Open calendar modal on click
              readOnly
            />
            <img
              src='/image/calendar-icon.png'
              alt="라인업 날짜"
              className={styles.icon}
            />
            {isStartCalendarOpen && (
              <CalendarModal
                onClose={handleCalendarClose}
                onConfirm={handleCalendarConfirm}
                isStart={true}
              />
            )}
          </div>
          <div className='text-xlarge'> ~ </div>
          <div className={`${styles.inputarea} ${styles.DateInputarea}`}>
            <div className={styles.DateLabelContainer}>
              <label>.</label>
            </div>
            <input
              className={styles.DateInput}
              placeholder="종료"
              value={endDate} // Display the selected date from Zustand store
              onClick={handleEndCalendarOpen} // Open calendar modal on click
              readOnly
            />
            <img
              src='/image/calendar-icon.png'
              alt="라인업 날짜"
              className={styles.icon}
            />
            {isEndCalendarOpen && (
              <CalendarModal
                onClose={handleCalendarClose}
                onConfirm={handleCalendarConfirm}
                isStart={false}
              />
            )}
          </div>
        </div>


        {/* 라인업 시간 입력 */}
        <div className={styles.inputarea}>
          <div className={styles.dateLabelContainer}>
            <label className="text-large">⏰ 라인업 시간</label>
          </div>
          <div className={styles.date}>
            <div>
              <input
                placeholder="시작"
                value={startTime} // 값이 없을 때는 "시작"으로 표시
                className={styles.timeInput}
                onClick={() => setIsStartTimeDropdownOpen(!isStartTimeDropdownOpen)} // 클릭 시 드롭다운 오픈
                readOnly
              />
              {isStartTimeDropdownOpen && (
                <div className={styles.dropdown}>
                  {times.map((time, index) => (
                    <div
                      key={index}
                      className={styles.dropdownItem}
                      onClick={() => handleStartTimeSelect(time)}
                    >
                      {time}
                    </div>
                  ))}
                </div>
              )}
            </div>
            <div className='text-xlarge'> ~ </div>
            <div>
              <input
                placeholder="종료"
                value={endTime} // 값이 없을 때는 "종료"로 표시
                className={styles.timeInput}
                onClick={() => setIsEndTimeDropdownOpen(!isEndTimeDropdownOpen)} // 클릭 시 드롭다운 오픈
                readOnly
              />
              {isEndTimeDropdownOpen && (
                <div className={styles.dropdown}>
                  {times.map((time, index) => (
                    <div
                      key={index}
                      className={styles.dropdownItem}
                      onClick={() => handleEndTimeSelect(time)}
                    >
                      {time}
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>

        <div className={styles.inputarea}>
          <InputField
            label="💰 성공 보수 (숫자)"
            placeholder="성공 보수를 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={successReward}
            type="number" // 숫자만 입력 가능하도록 설정
            onChange={(value) => handleNumericChange(setSuccessReward)({ target: { value } } as React.ChangeEvent<HTMLInputElement>)}
          />
        </div>
        <div className={styles.inputarea}>
          <InputField
            label="💲 실패 보수 (숫자)"
            placeholder="실패 보수를 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={failureReward}
            type="number" // 숫자만 입력 가능하도록 설정
            onChange={(value) => handleNumericChange(setFailureReward)({ target: { value } } as React.ChangeEvent<HTMLInputElement>)}
          />
        </div>
        <div className={styles.inputarea}>
          <InputField
            label="🔒 을의 보증금 (숫자)"
            placeholder="을의 보증금을 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={contracteeDeposit}
            type="number" // 숫자만 입력 가능하도록 설정
            onChange={(value) => handleNumericChange(setContracteeDeposit)({ target: { value } } as React.ChangeEvent<HTMLInputElement>)}
          />
        </div>
        {/* <div className={styles.inputarea}>
          <InputField
            label="📍 위도"
            placeholder="위도를 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={latitude}
            onChange={setLatitude}
          />
        </div>
        <div className={styles.inputarea}>
          <InputField
            label="📍 경도"
            placeholder="경도를 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={longitude}
            onChange={setLongitude}
          />
        </div> */}
        <div className={styles.inputarea}>
          <InputAreaField
            label="상세 내용"
            placeholder="상세 내용을 입력해 주세요."
            required
            errorMessage="필수로 입력해주세요."
            value={detailContent}
            onChange={setDetailContent}
          />
        </div>
        <div className={styles.button}>
          <Button
            label="완료"
            type="submit"
            disabled={!isFormValid}
          />
        </div>
      </form>
    </div>
  );
}
