<template>
  <footer class="footer bg-white py-5 margin-top">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <a href="/" class="d-flex align-items-center mb-3 link-dark text-decoration-none">
            <img src="https://blog.kakaocdn.net/dn/LjpBp/btsHv41MuEH/HkZCYbZoefvrFyUKS8HUK1/img.png" alt="JS금융 로고" width="100" height="50">
          </a>
          <!-- <p class="text-muted">&copy; 2024 JS금융. All rights reserved.</p> -->
        </div>
        <div class="col-md-4">
          <h5>많이 찾는 서비스</h5>
          <ul class="list-unstyled">
            <li><a href="#" @click="gotoCard">카드 찾기</a></li>
            <li><a href="#" @click="gotoInrate">금리 비교</a></li>
            <li><a href="#" @click="gotoExrate">환율</a></li>
            <li><a href="#" @click="gotoSearchBank">은행 찾기</a></li>
            <li><a href="#" @click="gotoCommunity">커뮤니티</a></li>
          </ul>
        </div>
        <!-- <div class="col-md-3">
          <h5>문의사항</h5>
          <ul class="list-unstyled">
            <li><a href="#">Personal Finance</a></li>
            <li><a href="#">Credit Score</a></li>
            <li><a href="#">Investments</a></li>
            <li><a href="#">Insurance</a></li>
          </ul>
        </div> -->
        <div class="col-md-4">
          <h5>JS금융</h5>
          <ul class="list-unstyled">
            <li><a href="#">소개</a></li>
            <li><a href="#">문의사항</a></li>
            <li><a href="#">개인정보처리방침</a></li>
            <!-- <li><a href="#">Terms of Service</a></li> -->
          </ul>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-6">
          <p class="text-muted">&copy; 2024 JS금융. All rights reserved.</p>
        </div>
        <div class="col-md-6 text-md-end">
          <a href="#" class="me-2"><i class="bi bi-facebook"></i></a>
          <a href="#" class="me-2"><i class="bi bi-twitter"></i></a>
          <a href="#" class="me-2"><i class="bi bi-instagram"></i></a>
          <a href="#" class="me-2"><i class="bi bi-linkedin"></i></a>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
 import { useRouter } from 'vue-router'


 const router = useRouter()

const gotoCard = function () {
  router.push({name:'card'})
}

const gotoInrate = function () {
  router.push({name:'inrate'})
}
const gotoExrate = function () {
  router.push({name:'exrate'})
}
const gotoSearchBank = function () {
  router.push({name:'mapsearch'})
}
const gotoCommunity = function () {
  router.push({name:'community'})
}

</script>

<style scoped>

.margin-top {
  margin-top: 150px;
}
.footer {
  background-color: #f8f9fa;
}

.footer h5 {
  font-weight: bold;
  margin-bottom: 20px;
}

.footer ul {
  padding-left: 0;
  list-style: none;
}

.footer ul li {
  margin-bottom: 10px;
}

.footer ul li a {
  color: #6c757d;
  text-decoration: none;
}

.footer ul li a:hover {
  text-decoration: underline;
}

.footer .text-muted {
  margin-bottom: 0;
}

.footer .text-md-end {
  text-align: end;
}

.footer a i {
  font-size: 1.5rem;
  color: #6c757d;
}

.footer a i:hover {
  color: #343a40;
}
</style>
