<template>
  <div class="container mt-5">
    <div class="text-center mb-4">
      <h2 class="fw-bold">주변 은행 찾기</h2>
    </div>
    <div class="search-bar mb-3">
      <input type="text" class="form-control" id="search" placeholder="지역 + 은행 검색" @keyup.enter="searchPlaces" />
    </div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      ps: null,
      markers: [], // 마커들을 저장할 배열
      infowindow: null, // 현재 열려 있는 인포윈도우를 추적하기 위한 변수
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e00f0e22cf21847de7d9f8c28e32a103';
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      this.mapContainer = document.getElementById('map'); // 지도를 표시할 div
      this.mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨 => 숫자 낮을 수록 지도 많이 확대 됨
      };

      // 지도를 생성합니다
      this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places();
    },
    searchPlaces() {
      const query = document.getElementById('search').value;
      if (!query) {
        alert('검색어를 입력해 주세요!');
        return;
      }
      // 이전에 생성된 마커들을 제거합니다
      this.clearMarkers();

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(query, this.placesSearchCB);
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기 위해 LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        this.map.setBounds(bounds);
      } else {
        alert('주변 은행이 없습니다!');
      }
    },
    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      const marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      // 생성된 마커를 배열에 추가합니다
      this.markers.push(marker);

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', () => {
        // 기존 인포윈도우가 열려 있으면 닫습니다
        if (this.infowindow) {
          this.infowindow.close();
        }

        // 새로운 인포윈도우를 생성합니다
        this.infowindow = new kakao.maps.InfoWindow({
          // 인라인으로 css안넣으면 적용 안됨.
          content: `
            <div style="width: 250px; padding: 10px; border-radius: 8px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); background-color: #fff; font-size: 14px; color: #333; position: relative; border: 1px solid #ddd;">
              <div style="padding-bottom: 10px; border-bottom: 1px solid #ddd; margin-bottom: 10px;">
                <h6 style="font-size: 16px; font-weight: bold; margin: 0;">${place.place_name}</h6>
              </div>
              <div style="padding-top: 10px;">
                <a href="https://map.kakao.com/link/to/${place.id}" target="_blank" style="display: inline-block; padding: 8px 12px; border-radius: 5px; background-color: #007bff; color: #fff; text-decoration: none; font-size: 14px; text-align: center;">길찾기</a>
              </div>
            </div>
          `,
          removable: true // 닫기 버튼을 추가합니다
        });
        this.infowindow.open(this.map, marker);
      });
    },
    clearMarkers() {
      // 배열에 추가된 모든 마커들을 지도에서 제거합니다
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      // 마커 배열을 초기화합니다
      this.markers = [];
    }
  },
};
</script>

<style scoped>
.container {
  max-width: 900px;
}

.search-bar {
  width: 100%;
}

#map {
  width: 100%;
  height: 600px;
  border: 2px solid #ddd;
  border-radius: 10px;
}

input[type="text"] {
  padding: 15px;
  font-size: 1.1em;
  border-radius: 5px;
  border: 1px solid #ccc;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

input[type="text"]:focus {
  border-color: #007bff;
  box-shadow: 0px 3px 6px rgba(0, 123, 255, 0.25);
}

</style>
