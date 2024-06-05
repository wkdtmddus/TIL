<template>
  <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="https://blog.kakaocdn.net/dn/brdk7g/btsHvYAxLqI/D73VyNlJTc3HpJvuCF66Kk/img.png" class="d-block w-100" alt="...">
        <div class="container">
          <div class="carousel-caption text-start">
            <h1 class="text-black fw-bold">JS금융에서 카드 신청하면,<br>
              캐쉬백 1만원!
            </h1>
            <!-- <p class="opacity-75">Some representative placeholder content for the first slide of the carousel.</p> -->
            <p class="mt-4"><a class="btn btn-lg btn-primary fw-medium" href="#" @click.prevent="gotoCard">내게 맞는 카드 추천받기</a></p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="https://blog.kakaocdn.net/dn/dEVQZY/btsHyTSaj4V/qTFNiB9NdQEQdqOvKqF7j0/img.png" class="d-block w-100" alt="...">
        <div class="container">
          <div class="carousel-caption text-start">
            <h1 class="text-white fw-bold">예/적금 금리 비교하고,<br>
              지금 바로 신청하세요!
            </h1>
            <!-- <p class="opacity-75">Some representative placeholder content for the first slide of the carousel.</p> -->
            <p class="mt-4"><a class="btn btn-lg fw-bold btn-white" href="#" @click.prevent="gotoInrate">예/적금 금리 비교하기</a></p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="https://blog.kakaocdn.net/dn/bv2qjR/btsHxzOalDx/hZDQw7uRiAmwd9PW55BrUk/img.png" class="d-block w-100" alt="...">
        <div class="container">
          <div class="carousel-caption text-start">
            <h1 class="text-white fw-bold">여행갈 때는 JS금융,<br>
              환율 체크하세요!
            </h1>
            <!-- <p class="opacity-75">Some representative placeholder content for the first slide of the carousel.</p> -->
            <p class="mt-4"><a class="btn btn-lg btn-white fw-bold" href="#" @click.prevent="gotoExrate">환율 보기</a></p>
          </div>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  </template>
  
  <script setup>
   import { useRouter } from 'vue-router'

const router = useRouter()

const gotoCard = function() {
 router.push({name:'card'})
}

const gotoInrate = function () {
  router.push({name:'inrate'})
}

const gotoExrate = function () {
  router.push({name:'exrate'})
}

  </script>
  
  <style scoped>
  .btn-white {
    color: white;
    border: 3px white solid;
    border-radius: 10px;
  }
  .btn-white:hover {
    background-color: white;
    color: black;
    border: 3px white solid;
    border-radius: 10px;
  }
  .carousel-item img {
  height: 700px; /* 높이를 300px로 설정 */
  width: 100%;
  object-fit: cover;
/*/* 이미지 비율을 유지하면서 영역을 채움 */
}
.carousel-caption {
  top: 50%;  /* 상단으로부터 50% 위치에 배치 */
  transform: translateY(-50%);
  left: 22%; 
  right: auto;  /* 우측 속성 제거 */
  bottom: auto;  /* 하단 속성 제거 */
  text-align: left;
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
  border-color: #004085;
}

  </style>