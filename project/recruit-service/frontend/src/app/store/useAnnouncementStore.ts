import create from 'zustand';

interface AnnouncementStore {
  title: string;
  successReward: string;
  failureReward: string;
  address: string;
  category: string;
  detailContent: string;
  latitude: string;
  longitude: string;
  serviceType: string;
  contracteeDeposit: string;
  announceImg: File | null;  
  startDate: string;  // 라인업 날짜 추가
  endDate: string;  // 라인업 날짜 추가
  startTime: string;   // 라인업 시작 시간
  endTime: string;     // 라인업 끝나는 시간
  district: string; // 구
  setTitle: (title: string) => void;
  setSuccessReward: (reward: string) => void;
  setFailureReward: (reward: string) => void;
  setAddress: (address: string) => void;
  setCategory: (category: string) => void;
  setDetailContent: (content: string) => void;
  setLatitude: (latitude: string) => void;
  setLongitude: (longitude: string) => void;
  setServiceType: (serviceType: string) => void;
  setContracteeDeposit: (deposit: string) => void;
  setAnnounceImg: (image: File | null) => void; 
  setStartDate: (startDate: string) => void; // 라인업 날짜 설정 함수 추가
  setEndDate: (endDate: string) => void; // 라인업 날짜 설정 함수 추가
  setStartTime: (startTime: string) => void;   // 시작 시간 설정 함수 추가
  setEndTime: (endTime: string) => void;       // 끝나는 시간 설정 함수 추가
  setDistrict: (district: string) => void;       // 끝나는 시간 설정 함수 추가
  reset: () => void;
}

export const useAnnouncementStore = create<AnnouncementStore>((set) => ({
  title: '',
  successReward: '',
  failureReward: '',
  address: '',
  category: '',
  detailContent: '',
  latitude: '',
  longitude: '',
  serviceType: '',
  contracteeDeposit: '',
  announceImg: null,  
  startDate: '', // 라인업 날짜 초기값
  endDate: '', // 라인업 날짜 초기값
  startTime: '',  // 시작 시간 초기값
  endTime: '',    // 끝나는 시간 초기값
  district: '',
  setTitle: (title) => set({ title }),
  setSuccessReward: (reward) => set({ successReward: reward }),
  setFailureReward: (reward) => set({ failureReward: reward }),
  setAddress: (address) => set({ address }),
  setCategory: (category) => set({ category }),
  setDetailContent: (content) => set({ detailContent: content }),
  setLatitude: (latitude) => set({ latitude }),
  setLongitude: (longitude) => set({ longitude }),
  setServiceType: (serviceType) => set({ serviceType }),
  setContracteeDeposit: (deposit) => set({ contracteeDeposit: deposit }),
  setAnnounceImg: (image) => set({ announceImg: image }), 
  setStartDate: (startDate) => set({ startDate }), // 라인업 날짜 설정
  setEndDate: (endDate) => set({ endDate }), // 라인업 날짜 설정
  setStartTime: (startTime) => set({ startTime }),   // 시작 시간 설정
  setEndTime: (endTime) => set({ endTime }),         // 끝나는 시간 설정
  setDistrict: (district) => set({ district }),         // 끝나는 시간 설정
  reset: () => set({
    title: '',
    successReward: '',
    failureReward: '',
    address: '',
    category: '',
    detailContent: '',
    latitude: '',
    longitude: '',
    serviceType: '',
    contracteeDeposit: '',
    announceImg: null,  
    startDate: '', // 초기화
    endDate: '', // 초기화
    startTime: '',  // 초기화
    endTime: '',     // 초기화
    district: '',
  }),
}));
