<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-white fixed-top" id="total_nav">
      <div class="container-fluid">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none me-5" id="logo">
          <div class="height-div  d-flex align-items-center">
            <img src="https://blog.kakaocdn.net/dn/LjpBp/btsHv41MuEH/HkZCYbZoefvrFyUKS8HUK1/img.png" alt="Bootstrap"
              width="100" height="50">
          </div>
        </a>
        <!-- <a class="navbar-brand" href="#">Navbar</a> -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link me-2" href="#" @click="gotoCard">카드추천</a>
            </li>
            <li class="nav-item">
              <a class="nav-link me-2" href="#" @click="gotoInrate">금리비교</a>
            </li>
            <li class="nav-item">
              <a class="nav-link me-2" href="#" @click="gotoExrate">환율</a>
            </li>
            <li class="nav-item">
              <a class="nav-link me-2" href="#" @click="gotoMapsearch">주변은행</a>
            </li>
            <li class="nav-item">
              <a class="nav-link me-2" href="#" @click="gotoCommunity">커뮤니티</a>
            </li>
            <!-- <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li> -->
            <!-- <li class="nav-item">
          <a class="nav-link disabled" aria-disabled="true">Disabled</a>
        </li> -->
          </ul>
          <div v-if="!store.isLogin">
            <button class="btn btn-outline-secondary me-2" @click="gotoLogin">로그인</button>
            <button class="btn btn-primary" role="button" id="nav-button" @click="gotoSignup">회원가입</button>
          </div>
          <div v-else>
            <button class="btn btn-outline-secondary" role="button" id="nav-button" @click="gotoProfile">내정보</button>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useCounterStore } from '@/stores/counter';

const router = useRouter()
const store = useCounterStore(); // 스토어 인스턴스 생성

//새로고침 안하면 nav바 모바일로 되었을 때 컴포넌트만 바뀌어서 nav바가 안사라짐. 그래서 새로고침 함.
const gotoCard = function () {
  window.location.href = router.resolve({ name: 'card' }).href
  // router.push({name:'card'})
  // router.go(0)
}
const gotoSignup = function () {
  window.location.href = router.resolve({ name: 'signup' }).href
}

const gotoLogin = function () {
  window.location.href = router.resolve({ name: 'login' }).href
}

const gotoProfile = function () {
  window.location.href = router.resolve({ name: 'profile' }).href
}

const gotoExrate = function () {
  window.location.href = router.resolve({ name: 'exrate' }).href
}
const gotoInrate = function () {
  window.location.href = router.resolve({ name: 'inrate' }).href
}
const gotoMapsearch = function () {
  window.location.href = router.resolve({ name: 'mapsearch' }).href
}
const gotoCommunity = function () {
  window.location.href = router.resolve({ name: 'community' }).href
}

</script>
 
<style scoped>
.height-div {
  height: 85px;
}

#logo {
  margin-left: 300px;
}

@media (max-width: 1500px) {
  #logo {
    margin-left: 0;
  }
}

#nav-button {
  margin-right: 300px;
}

@media (max-width: 1500px) {
  #nav-button {
    margin-right: 0;
  }
}

@media (max-width: 992px) {
  #nav-button {
    margin-right: 0;
  }
}


.nav-link {
  font-size: 20px;
  color: black;
  font-weight: 900;
}

.nav-link:hover {
  color: #0d6efd;
}
</style>