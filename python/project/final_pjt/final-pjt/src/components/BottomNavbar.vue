<template>
  <div>
    <!-- Sidebar -->
    <div :class="['sidebar', { open: isSidebarOpen }]">
      <button class="close-btn" @click="closeSidebar">&times;</button>
      <!-- <h2>카테고리</h2> -->
      <!-- Add your category items here -->
      <div class="logo">
        <img src="@/assets/logo.png" alt="Logo">
      </div>
      <p class="side-margin fw-bold">서비스</p>
      <hr>
      <h3 class="fw-bold" @click="gotoInrate">예/적금 금리 비교</h3>
      <h3 class="mt-3 fw-bold" @click="gotoExrate">환율</h3>
      <h3 class="mt-3 fw-bold" @click="gotoMapsearch">주변 은행 찾기</h3>
      <h3 class="mt-3 fw-bold" @click="gotoCommunity">커뮤니티</h3>
      <!-- Add more categories as needed -->
    </div>

    <!-- Main content with overlay -->
    <div :class="['main-content', { shift: isSidebarOpen }]">
      <div class="overlay" v-if="isSidebarOpen" @click="closeSidebar"></div>

      <!-- Bottom navigation bar -->
      <div :class="['navbar', { hidden: isNavHidden }]">
        <a :class="{ active: activeMenu === 'category' }" @click.prevent="toggleSidebar">
          <i class="fa fa-th-list"></i>
          카테고리
        </a>
        <a :class="{ active: activeMenu === 'card' }" @click.prevent="navigateTo('card')">
          <i class="fa fa-credit-card"></i>
          카드추천
        </a>
        <a :class="{ active: activeMenu === 'home' }" @click.prevent="navigateTo('home')">
          <i class="fa fa-home"></i>
          홈
        </a>
        <a v-if="store.isLogin" :class="{ active: activeMenu === 'profile' }" @click.prevent="navigateTo('profile')">
          <i class="fa fa-user"></i>
          내정보
        </a>
        <a v-else :class="{ active: activeMenu === 'login' }" @click.prevent="navigateTo('login')">
          <i class="fa fa-user"></i>
          로그인
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router'
import { ref, watch, onMounted, onUnmounted } from 'vue'
import { useCounterStore } from '@/stores/counter'
// import router from '@/router'
const router = useRouter()

export default {
  setup() {
    const router = useRouter()
    const route = useRoute()
    const activeMenu = ref(route.name)
    const isSidebarOpen = ref(false)
    const isNavHidden = ref(false)
    const store = useCounterStore()
    let lastScrollTop = 0

    const navigateTo = (menu) => {
      activeMenu.value = menu
      router.push({ name: menu })
      isSidebarOpen.value = false
    }

    const toggleSidebar = () => {
      isSidebarOpen.value = !isSidebarOpen.value
    }

    const closeSidebar = () => {
      isSidebarOpen.value = false
    }
    const gotoInrate = () => {
      window.location.href = router.resolve({ name: 'inrate' }).href
    }
    const gotoExrate = function () {
      window.location.href = router.resolve({ name: 'exrate' }).href
    }
    const gotoMapsearch = function () {
      window.location.href = router.resolve({ name: 'mapsearch' }).href
    }
    const gotoCommunity = function () {
      window.location.href = router.resolve({ name: 'community' }).href
    }
    const handleScroll = () => {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop
      if (scrollTop > lastScrollTop) {
        isNavHidden.value = true
      } else {
        isNavHidden.value = false
      }
      lastScrollTop = scrollTop <= 0 ? 0 : scrollTop
    }

    onMounted(() => {
      window.addEventListener('scroll', handleScroll)
    })

    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll)
    })

    watch(route, (newRoute) => {
      activeMenu.value = newRoute.name
    })

    return {
      activeMenu,
      isSidebarOpen,
      isNavHidden,
      store,
      navigateTo,
      toggleSidebar,
      closeSidebar,
      gotoInrate,
      gotoExrate,
      gotoMapsearch,
      gotoCommunity
    }
  }
}


</script>

<style scoped>

.logo img {
  height: 40px;
}

.side-margin {
  margin-top: 5rem;
}

.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 90%;
  height: 100%;
  background-color: white;
  color: black;
  padding: 20px;
  transform: translateX(-100%);
  transition: transform 0.3s ease;
  z-index: 6;
}

.sidebar.open {
  transform: translateX(0);
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 20px;
  background: none;
  border: none;
  color: black;
  font-size: 30px;
  cursor: pointer;
}

.main-content {
  transition: margin-left 0.3s ease;
}

.main-content.shift {
  margin-left: 250px;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 2;
}

.navbar {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: white;
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 60px;
  box-shadow: 0 0px 5px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease;
  z-index: 1;
}

.navbar.hidden {
  transform: translateY(100%);
}

.navbar a {
  flex: 1;
  text-align: center;
  padding-bottom: 10px;
  color: black;
  text-decoration: none;
  font-size: 14px;
  opacity: 0.4;
}

.navbar a.active {
  opacity: 1;
  font-weight: 700;
}

.navbar i {
  display: block;
  font-size: 24px;
  margin-bottom: 5px;
}</style>
