<template>
  <div>
    <div v-if="$isMobile()" class="mainswiper">
      <MainSwiper />
    </div>
    <div v-else>
      <HomeCarousel />
    </div>
    <div v-if="$isMobile()" class="swiper-margin">
      <div class="d-flex justify-content-start align-items-center ms-3 mb-4">
        <div>
          <img src="https://blog.kakaocdn.net/dn/eJ3CMM/btsHyTqD4E0/lRoaULwQhRX7dKP5J4EQiK/img.png" alt=""
            class="img-width">
        </div>
        <div class="align-self-center">
          <h2 class="fw-bold">금주의 추천 Pick!</h2>
        </div>
      </div>
      <Swiper />
    </div>
    <div v-else>
      <HomeContainer />
    </div>
  </div>
</template>

<script setup>
import HomeCarousel from '@/components/HomeCarousel.vue'
import HomeContainer from '@/components/HomeContainer.vue'
import MainSwiper from '@/components/MainSwiper.vue'
import Swiper from '@/components/Swiper.vue'
</script>
<script>
export default {
  created() {
    console.log(this.$isMobile())
  }
}
</script>

<style scoped>
.swiper-margin {
  margin-top: 4rem;
}

.img-width {
  width: 40px;
}

.fw-bold {
  margin: 0 0 0 0;
}
</style>