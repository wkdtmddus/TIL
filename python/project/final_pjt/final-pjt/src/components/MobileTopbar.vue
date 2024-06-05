<template>
  <div class="top-bar">
    <div class="container">
      <div class="left-section">
        <div v-if="isHomePage" class="logo">
          <img src="@/assets/logo.png" alt="Logo">
        </div>
        <div v-else class="page">
          <button class="back-btn" @click="navigateTo()">
            <i class="fa fa-arrow-left"></i>
          </button>
        </div>
      </div>
    </div>
  </div>

  <div v-if="isCardPage" class="menu-title">
    <div class="text-center">카드 추천</div>
  </div>
  <div v-else-if="isProfilePage" class="menu-title">
    <div class="text-center">프로필</div>
  </div>
  <div v-else-if="isCommunityPage" class="menu-title">
    <div class="text-center">커뮤니티</div>
  </div>
  <div v-else-if="isInratePage" class="menu-title">
    <div class="text-center">예/적금 금리 비교</div>
  </div>
  <div v-else-if="isInrate2Page" class="menu-title">
    <div class="text-center">예/적금 금리 비교</div>
  </div>
  <div v-else-if="isExratePage" class="menu-title">
    <div class="text-center">환율 계산</div>
  </div>

</template>

<script>
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

export default {
  setup() {
    const router = useRouter()
    const route = useRoute()

    const isHomePage = computed(() => route.name === 'home')
    const isCardPage = computed(() => route.name === 'card')
    const isProfilePage = computed(() => route.name === 'profile')
    const isCommunityPage = computed(() => route.name === 'community')
    const isMapsearchPage = computed(() => route.name === 'mapsearch')
    const isExratePage = computed(() => route.name === 'exrate')
    const isInratePage = computed(() => route.name === 'inrate')
    const isInrate2Page = computed(() => route.name === 'inrate2')

    const navigateTo = () => {
      router.go(-1)
    }

    return {
      isHomePage,
      isCardPage,
      isProfilePage,
      isCommunityPage,
      isMapsearchPage,
      isExratePage,
      isInratePage,
      isInrate2Page,
      navigateTo
    }
  }
}
</script>

<style scoped>
.menu-title {
  position: absolute;
  width: 100%;
  top: 0;
  padding: 10px;
}
.top-bar {
  background-color: transparent;
  position: absolute;
  width: 100%;
  top: 0;
  z-index: 6;
}

.container {
  display: flex;
  align-items: center;
  padding: 10px 10px;
}

.left-section {
  display: flex;
  align-items: center;
}

.logo img {
  height: 40px;
}

.page {
  display: flex;
  align-items: center;
}

.text-center {
  font-size: 16px;
  font-weight: 800;
}

.back-btn {
  background: none;
  border: none;
  color: black;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  align-items: center;
}

.back-btn i {
  font-size: 20px;
}
</style>
