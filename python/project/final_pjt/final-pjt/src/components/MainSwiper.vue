<template>
  <swiper-container class="mySwiper" slides-per-view="1" centered-slides="true" autoplay-delay="3200"
    autoplay-disable-on-interaction="false" loop="true" :pagination="{
      type: 'custom',
      clickable: true,
      renderCustom: function (swiper, current, total) {
        return `<div class='num-wrap'><span class='current-num'>${current}</span><span class='line'>/</span><span class='total-num'>${total}</span><div>`;
      }
    }" ref="swiperRef" init="false">
    <!-- pagination주는 거 -->
    <!-- <swiper-container 
    class="mySwiper" 
    slides-per-view="1" 
    centered-slides="true"
    autoplay-delay="2500"
    autoplay-disable-on-interaction="false"
    loop="true"
    :pagination="{
    clickable: true,
    type: 'fraction' , 
    }"
    > -->

    <swiper-slide v-for="(slide, index) in slides" :key="index" class="slide">
      <div class="" @click="goto(slide.url)">
        <a href="#"><img :src="slide.image" class="card-img-top" alt="..."></a>
        <div class="card-body">
          <!-- <h5 class="">{{ slide.title }}</h5>
          <p class="">{{ slide.description }}</p> -->
        </div>
      </div>
      <div class="slide-text">
        <div class="t1">{{ slide.t1 }}</div>
        <div class="t2">{{ slide.t2 }}</div>
        <div class="t3">{{ slide.t3 }}</div>
      </div>
    </swiper-slide>
  </swiper-container>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router'
import { register } from "swiper/element/bundle";
const router = useRouter()
register();

const goto = function (urlname) {
  if (urlname === 'card') {
    router.push({name:'card'})
  }
  if (urlname === 'inrate') {
    router.push({name:'inrate'})
  }
  if (urlname === 'exrate') {
    router.push({name:'exrate'})
  }
  if (urlname === 'mapsearch') {
    router.push({name:'mapsearch'})
  }
  if (urlname === 'community') {
    router.push({name:'community'})
  }
}
// 슬라이드 데이터 정의
const slides = ref([
  {
    image: 'https://blog.kakaocdn.net/dn/Qp8Yc/btsHwPCPq4c/U76ppRFAJ8asktXtblU1K1/img.jpg',
    title: 'Card 1',
    t1: '지금 카드 등록하면',
    t2: '1만원 캐쉬백',
    t3: '#1만원 #캐쉬백 #두둑한 지갑',
    url: 'card'
  },
  {
    image: 'https://blog.kakaocdn.net/dn/b2EBuU/btsHwfIM6mM/WkRVrvyDqMtl9b5CViozO0/img.jpg',
    title: 'Card 2',
    t1: '금리 비교하고',
    t2: '최고의 예/적금 선택하세요!',
    t3: '#금리비교 #맞춤형 예/적금',
    url: 'inrate',
  },
  {
    image: 'https://blog.kakaocdn.net/dn/bg80kE/btsHvYN3zJS/CQgAbKxR9uIkulN85CgMCk/img.jpg',
    title: 'Card 3',
    t1: '환율 우대!',
    t2: '수수료 최저!',
    t3: '#달러 #엔 #유로',
    url: 'exrate'
  },
  {
    image: 'https://blog.kakaocdn.net/dn/bgbBlk/btsHw3VbNZ0/0WccrVXiBRc3EI2zGYdIrK/img.jpg',
    title: 'Card 4',
    t1: '내 주변에',
    t2: '가장 가까운 은행은 어디?',
    t3: '#주변 은행 #검색',
    url: 'mapsearch'
  },
  {
    image: 'https://blog.kakaocdn.net/dn/cf0Zqu/btsHxjjdiwn/0csskYkqe48R3EKMlcyeF1/img.jpg',
    title: 'Card 5',
    t1: '커뮤니티에서',
    t2: '금융 지식 공유합니다!',
    t3: '#화기애애 #친목 #지식',
    url: 'community'
  },
]);
// https://peamexx.tistory.com/201 커스텀 css 적용하기
const swiperRef = ref();
watch(swiperRef, (n, o) => {
  if (n != o) {
    const params = {
      injectStyles: [`
        .line {
          opacity: 0.4;
          font-weight: 300;
          font-size: 12px;
          padding: 0 1px;
          color: #ffffff;
        }
        .current-num {
          font-weight: 500;
          font-size: 12px;
          color: #ffffff;
          padding: 0 1px;
        }
        .total-num {
          font-size: 12px;
          color: #ffffff;
          padding: 0 1px;
          opacity: 0.5;
          font-weight: 500;
        }
        .swiper-pagination {
          
        }
        .num-wrap {
          background: rgba(0, 0, 0, 0.3);
          border-radius: 14px;
          padding: 3px 10px 3px;
         
          float:right;
          margin-right:0.5rem;
          font-size: 0.9rem;
          display: flex;
          align-items: center;
          justify-content: center;
        }
      `]
    };

    Object.assign(swiperRef.value, params);
    swiperRef.value.initialize();
  }
});

</script>

<style scoped>
@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateX(50px);
  }

  100% {
    opacity: 1;
    transform: translateX(0);
  }
}

.slide-text {
  position: absolute;
  width: calc(100% - 40px);
  left: 30px;
  bottom: 70px;
  color: white;
}

.t1 {
  display: block;
  font-size: 27px;
  font-weight: 550;
  margin: 0 0 6px;
  line-height: 1.15;
  letter-spacing: -0.7px;
  animation: fadeIn 400ms ease-out both;
}

.t2 {
  display: block;
  font-size: 27px;
  font-weight: 550;
  margin: 0 0 15px;
  line-height: 1.15;
  letter-spacing: -0.7px;
  animation: fadeIn 400ms ease-out both;
  animation-delay: 100ms;
}

.t3 {
  display: block;
  font-size: 17px;
  font-weight: 300;
  margin: 0 0 0;
  line-height: 1.15;
  letter-spacing: -0.5px;
  animation: fadeIn 400ms ease-out both;
  animation-delay: 200ms;
}
</style>
