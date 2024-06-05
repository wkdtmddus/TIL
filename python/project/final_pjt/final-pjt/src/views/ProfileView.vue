<template>
  <div class="ms-2 me-2">
    <div class="profile-container mt-5">
      <div class="profile-header">
        <img src="https://via.placeholder.com/150" alt="Profile Picture" class="profile-picture">
        <h2>{{ username }}</h2>
      </div>
      <div class="profile-details">
        <p><strong>아이디:</strong></p>
        <p>{{ username }}</p>
        <p class="mt-4"><strong>Email:</strong></p>
        <p>{{ email }}</p>
        <p class="mt-4"><strong>닉네임:</strong></p>
        <p>{{ nickname }}</p>
        <p class="mt-5 text-center join-content-title fw-bold">가입 상품</p>
        <hr>
        <p v-for="inrate_content in inrate_contents" :key="inrate_content.id">
        <div class="main-relative">
          <div >
            <p class="fw-bold">상품명 : {{ inrate_content.product.fin_prdt_nm }}</p>
            <p>은행 : {{ inrate_content.product.kor_co_nm }}</p>
            <p>개월 수 : {{ inrate_content.save_trm }}</p>
            <p>단리/복리 : {{ inrate_content.intr_rate_type_nm }}</p>
            <p>기본금리 : {{ inrate_content.intr_rate }}</p>
            <p class="text-danger">최고금리 : {{ inrate_content.intr_rate2 }}</p>
          </div>
          <div class="detail-absolute bank-item" @click="gotoDetail(inrate_content.id)">
            자세히 보기
          </div>
        </div>
        <hr>
        </p>
      </div>
      <div class="profile-details mb-4">
        <p class="mt-3 text-center join-content-title fw-bold">그래프 비교</p>
        <Chart :chartData="inrate_contents" />
      </div>
      <div class="profile-actions">
        <div class="top-buttons mt-2">
          <button class="btn btn-info mb-2" @click="editProfile">내정보 수정</button>
          <button class="btn btn-secondary mb-2" @click="logout">로그아웃</button>
        </div>
        <button class="btn btn-white" @click="openDeleteModal">회원탈퇴</button>
        <div v-if="isModalOpen" class="modal-overlay">
          <div class="modal-content">
            <h2>회원탈퇴</h2>
            <p>정말로 회원탈퇴 하시겠습니까?</p>
            <p>아래 비밀번호를 입력하세요.</p>
            <input type="password" v-model="password" placeholder="비밀번호를 입력해주세요" />
            <div class="modal-buttons">
              <button class="btn btn-secondary" @click="closeDeleteModal">취소</button>
              <button class="btn btn-danger" @click="confirmDelete">확인</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="profile-container mt-5">
      <p class="mt-2 text-center join-content-title fw-bold">포트폴리오 추천받기</p>
      <div class="profile-details">
        <hr>
      </div>
      <p class="mt-2 text-center join-content-title fw-bold">은행선택</p>
      <div class="bank-selection">
        <button v-for="bank in banks" :key="bank" @click="selectBank(bank)" :class="{ 'selected': selectedBank === bank }"
          class="btn btn-outline-primary">
          {{ bank }}
        </button>
      </div>
      <p class="mt-2 text-center join-content-title fw-bold">유형 선택</p>
      <div class="type-selection">
        <button @click="selectType('예금')" :class="{ 'selected': selectedType === '예금' }" class="btn btn-outline-primary">
          예금
        </button>
        <button @click="selectType('적금')" :class="{ 'selected': selectedType === '적금' }" class="btn btn-outline-primary">
          적금
        </button>
      </div>
      <button class="btn btn-primary mt-3" @click="submitPortfolio">추천받기</button>
      <div class="profile-details">
        <hr>
      </div>
      <div class="profile-details">
        <div v-for="port in portlist" :key="port.id">
          <div class="main-relative">
            <div>
              <p class="fw-bold">상품명 : {{ port.product.fin_prdt_nm }}</p>
              <p>은행 : {{ port.product.kor_co_nm }}</p>
              <p>기본금리 : {{ port.intr_rate }}</p>
              <p class="text-danger">최고 금리: {{ port.intr_rate2 }}</p>
            </div>
            <div class="detail-absolute bank-item" @click="gotoDetail(port.id)">
              자세히 보기
            </div>
          </div>
          <hr>
        </div>
      </div>
    </div>
  </div>
  <div class="container margin-top">
    <hr class="featurette-divider">
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useCounterStore } from '@/stores/counter';
import { useRouter } from 'vue-router'
import Chart from '@/components/Chart.vue'

const store = useCounterStore()
const router = useRouter()

const email = ref('')
const username = ref('')
const nickname = ref('')
const inrate_contents = ref([])
const banks = ref(['우리은행', '국민은행', '농협은행', '하나은행', '신한은행']) // 실제 은행 이름으로 교체하세요
const selectedBank = ref(null)
const selectedType = ref(null)
const portlist = ref([])

const editProfile = () => {
  store.editprofile()
}

const isModalOpen = ref(false)
const password = ref('')

const openDeleteModal = () => {
  isModalOpen.value = true
}

const closeDeleteModal = () => {
  isModalOpen.value = false
  password.value = ''
}

const confirmDelete = () => {
  if (password.value) {
    store.deleteaccount(password.value)
    closeDeleteModal()
  } else {
    alert('비밀번호가 필요합니다.')
  }
}

const selectBank = (bank) => {
  selectedBank.value = bank
}

const selectType = (type) => {
  selectedType.value = type
}

const submitPortfolio = () => {
  if (selectedBank.value && selectedType.value) {
    axios({
      method: 'put',
      url: 'http://127.0.0.1:8000/portfolios/',
      headers: {
        Authorization: `Token ${store.token}`
      },
      data: {
        'bank': selectedBank.value,
        'tendency': selectedType.value
      }
    })
      .then(res => {
        // console.log(res.data);
        portlist.value = res.data.recommended_products
        console.log('포트리스트')
        console.log(portlist.value)
        alert('성공적으로 제출되었습니다.');
      })
      .catch(err => {
        console.log(err);
        alert('제출에 실패했습니다.');
      })
  } else {
    alert('은행과 유형을 선택해주세요.');
  }
}

axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/portfolios/',
  headers: {
    Authorization: `Token ${store.token}`
  },
})
  .then(res => {
    console.log('get')
    console.log(res.data);
    portlist.value = res.data.recommended_products
    console.log(portlist.value)
  })
  .catch(err => {
    console.log(err);
  })

axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/accounts/user/',
  headers: {
    Authorization: `Token ${store.token}`
  }
})
  .then(res => {
    console.log(res.data);
    username.value = res.data.username
    email.value = res.data.email
    nickname.value = res.data.nickname
    inrate_contents.value = res.data.fins
    console.log(inrate_contents.value)
  })
  .catch(err => {
    console.log(err);
  });

const gotoDetail = function (id) {

  router.push({ name: 'inratedetail', params: { id: id } })
    .then((() => window.scrollTo(0, 0)))
}

// axios({
//   method: 'get',
//   url: 'http://127.0.0.1:8000/portfolios/',
//   headers: {
//     Authorization: `Token ${store.token}`
//   }
// })
//   .then(res => {
//     console.log(res.data);
//   })
//   .catch(err => {
//     console.log(err);
//   })

const logout = () => {
  store.logout()
}
</script>

<style scoped>
.bank-item {
  cursor: pointer;
}

.main-relative {
  position: relative;
}

.detail-absolute {
  position: absolute;
  right: 0;
  bottom: 0;
  font-size: 14px;
  opacity: 0.5;
}

.join-content-title {
  font-size: 1.5rem;
}

.margin-top {
  margin-top: 100px;
}

.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  max-width: 600px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  margin-left: auto;
  margin-right: auto;
}

.profile-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.profile-picture {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 10px;
}

.profile-details {
  text-align: left;
  width: 100%;
}

.profile-details p {
  margin: 5px 0;
}

.profile-details strong {
  display: inline-block;
  width: 100px;
}

.profile-actions {
  width: 100%;
  margin-top: 20px;
}

.top-buttons {
  display: flex;
  flex-direction: column;
}

.top-buttons .btn {
  width: 100%;
  margin-bottom: 10px;
}

.btn-white {
  width: 100%;
  background-color: white;
  color: black;
}

.btn-info {
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.btn-info:hover {
  background-color: #0056b3;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.modal-buttons .btn {
  width: 45%;
}

input {
  padding: 15px;
  font-size: 1.1em;
  border-radius: 5px;
  border: 1px solid #ccc;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

input:focus {
  border-color: #007bff;
  box-shadow: 0px 3px 6px rgba(0, 123, 255, 0.25);
}

.bank-selection {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.bank-selection .btn {
  margin: 5px;
}

.bank-selection .btn.selected {
  background-color: #007bff;
  color: white;
}

.type-selection {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.type-selection .btn {
  margin: 5px;
}

.type-selection .btn.selected {
  background-color: #007bff;
  color: white;
}
</style>
