'use client'

import React, { useState, useEffect } from "react";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import styles from './KakaoMapModal.module.css';
import Header from "../Header/Header";

interface KakaoMapModalProps {
  keyword: string;
  onClose: () => void;
}

interface MarkerInfo {
  position: {
    lat: number;
    lng: number;
  };
  content: string;
}

const KakaoMapModal: React.FC<KakaoMapModalProps> = ({ keyword, onClose }) => {
  const [info, setInfo] = useState<MarkerInfo | null>(null);
  const [marker, setMarker] = useState<MarkerInfo | null>(null);
  const [map, setMap] = useState<kakao.maps.Map>();
  const [copyMessage, setCopyMessage] = useState<string | null>(null);

  useEffect(() => {
    if (!map || !keyword) return;

    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(keyword, (data, status) => {
      if (status === kakao.maps.services.Status.OK && data[0]) {
        const position = {
          lat: parseFloat(data[0].y),
          lng: parseFloat(data[0].x),
        };

        setMarker({
          position,
          content: data[0].address_name,
        });

        map.setCenter(new kakao.maps.LatLng(position.lat, position.lng));
      } else {
        alert("Address not found!");
      }
    });
  }, [map, keyword]);

  const handleCopy = () => {
    navigator.clipboard.writeText(keyword)
      .then(() => {
        setCopyMessage('주소가 복사되었습니다.');
        setTimeout(() => setCopyMessage(null), 2000); // Hide message after 3 seconds
      })
      .catch((err) => {
        console.error('Failed to copy: ', err);
      });
  };

  return (
    <div className={styles.modalBackground}>
      <Header
        imagesSrc="/image/back-icon.png"
        altText="뒤로 가기"
        href="/home"
        navigateType='not'
        title='라인업 지역'
        onClick={onClose}
      />
      
      <div className={styles.keywordContainer}>
        {keyword}
        <button className={styles.copyButton} onClick={handleCopy}>복사</button>
      </div>

      {/* Notification for copy message */}
      {copyMessage && <div className={styles.copyNotification}>{copyMessage}</div>}
      
      <div className={styles.modalContainer} onClick={(e) => e.stopPropagation()}>
        <Map
          center={{
            lat: 37.566826,
            lng: 126.9786567,
          }}
          style={{
            width: "100%",
            height: "100%",
          }}
          level={3}
          onCreate={setMap}
        >
          {marker && (
            <MapMarker
              position={marker.position}
              onClick={() => setInfo(marker)}
            >
              {info && info.content === marker.content && (
                <div style={{ color: "#000" }}>{marker.content}</div>
              )}
            </MapMarker>
          )}
        </Map>
      </div>
    </div>
  );
};

export default KakaoMapModal;
