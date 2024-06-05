<template>
  <swiper-container 
    class="mySwiper fade-in-up"
    space-between="15" 
    slides-per-view="1.2"
    slidesOffsetBefore="20"
    slidesOffsetAfter="10"
  >
    <swiper-slide v-for="(slide, index) in slides" :key="index">
      <div class="">
        <img :src="slide.image" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title mt-3 fw-bold">{{ slide.title }}</h5>
          <!-- <p class="card-text mt-2">{{ slide.description }}</p> -->
        </div>
      </div>
    </swiper-slide>
  </swiper-container>
</template>

<script setup>
import { ref } from 'vue'
import 'swiper/swiper-bundle.css'

const slides = ref([
  {
    image: 'https://blog.kakaocdn.net/dn/btWGuQ/btsHx1bTuvo/FwmbrFRp8eBZnLVJkgv6o1/img.png',
    title: 'KDB BestONE E 하나기업카드',
    description: '#기업/개인 사업자',
  },
  {
    image: 'https://blog.kakaocdn.net/dn/bqCnTX/btsHysUDSXW/htEAYLycgRTSDv6ySBYO7k/img.png',
    title: 'KDB Choice 하이브리드 카드',
    description: 'Description for card 2',
  },
  {
    image: 'https://blog.kakaocdn.net/dn/TkHIA/btsHxJWRPiv/FoLr4eElVpkCySkkI7PZUK/img.png',
    title: 'KDB 삼성카드',
    description: 'Description for card 3',
  },
  {
    image: 'https://blog.kakaocdn.net/dn/do1H4w/btsHxzG0w3O/78RUfH79fhMNieB2KNZSM1/img.png',
    title: 'KDB Choice 체크카드',
    description: 'Description for card 4',
  },
  {
    image: 'https://blog.kakaocdn.net/dn/mLlbV/btsHym7ZUGz/g7bGsezTPHex7QkrhPguvK/img.png',
    title: '현대카드X CHECK, 현대카드X HYBRID',
    description: 'Description for card 5',
  },
]);
</script>

<style scoped>
.fade-in-up {
  animation: fadeInUp 1s ease-in-out;
}

@keyframes fadeInUp {
  from {
    transform: translateY(20px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

.swiper-button-next,
.swiper-button-prev {
  color: #000; /* 네비게이션 버튼 색상 */
}

.swiper-pagination-bullet {
  background-color: #000; /* 페이지네이션 점 색상 */
}
</style>
