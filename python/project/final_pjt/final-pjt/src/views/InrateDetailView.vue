<template>
  <div class="me-2 ms-2">
    <div class="container mt-5">
      <button class="back-btn" @click="navigateTo()">
        <!-- <i class="fa fa-arrow-left"></i> -->
        <span class="ms-2 all-css">전체 목록 보기</span>
      </button>
      <h2 class="fw-bold mb-5 mt-5">예금 상세 정보</h2>
      <div class="product-detail mt-5">
        <h3 class="product-name">{{ product.fin_prdt_nm }}</h3>
        <hr>
        <p class="bank-name">은행: {{ product.kor_co_nm }}</p>
        <p class="interest-rate">금리: {{ intr_rate }}%</p>
        <p class="interest-rate2">최고 금리: {{ intr_rate2 }}%</p>
        <p class="interest-type">금리 유형: {{ intr_rate_type_nm }}</p>
        <p class="term">기간: {{ save_trm }}개월</p>
        <div v-if="!store.isLogin">
          <button class="btn joinbutton" @click.prevent="alertsign()">가입하기</button>
        </div>
        <div v-else>
        <button v-if="isIdMatched" class="btn notjoinbutton" @click.prevent="Joinin(id)">탈퇴하기</button>
        <button v-else class="btn joinbutton" @click.prevent="Joinin(id)">가입하기</button>
      </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { useCounterStore } from '@/stores/counter';
import { useRouter } from 'vue-router'
const store = useCounterStore()
const route = useRoute();
const router = useRouter()
const product = ref({});
const fin_prdt_cd = ref('');
const intr_rate = ref(0);
const intr_rate2 = ref(0);
const intr_rate_type_nm = ref('');
const save_trm = ref(0);
const id = ref(0);
const userProducts = ref([]);

axios({
  method: 'get',
  url: `http://127.0.0.1:8000/fins/deposit_product_option/${route.params.id}/`,
})
  .then(res => {
    console.log(res.data);
    const data = res.data;
    product.value = data.product;
    intr_rate.value = data.intr_rate;
    intr_rate2.value = data.intr_rate2;
    intr_rate_type_nm.value = data.intr_rate_type_nm;
    save_trm.value = data.save_trm;
    id.value = data.id
  })
  .catch(err => {
    console.log(err);
  });


axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/accounts/user/',
  headers: {
    Authorization: `Token ${store.token}`
  }
})
  .then(res => {
    console.log(res.data.fins);
    userProducts.value = res.data.fins;
  })
  .catch(err => {
    console.log(err);
  });

const isIdMatched = computed(() => {
  // console.log(userProducts.value)
  return userProducts.value.some(product => product.id === id.value);
});


const Joinin = function (id) {
  store.joinin(id)
  window.location.reload();
}
const navigateTo = () => {
  router.push({name:'inrate'})
}

const alertsign = function () {
  alert("로그인이 필요합니다.");
  router.push({name: 'login'})
}

</script>

<style scoped>

.all-css {
  opacity: 0.6;
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

.joinbutton {
  width: 100%;
  margin-top: 20px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.notjoinbutton {
  width: 100%;
  margin-top: 20px;
  background-color: white;
  color: black;
  cursor: pointer;
}

.joinbutton:hover {
  background-color: #0056b3;
}
.notjoinbutton:hover {
  background-color: #0056b3;
  color: white
}

.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

h2 {
  text-align: center;
  color: #343a40;
}

.product-detail {
  margin-top: 20px;
}

.product-detail h3 {
  font-size: 24px;
  margin-bottom: 10px;
}

.product-detail p {
  font-size: 18px;
  margin-bottom: 8px;
}

.product-name {
  font-weight: bold;
}

.bank-name,
.interest-rate,
.interest-rate2,
.interest-type,
.term,
.product-code {
  color: #555;
}
</style>
