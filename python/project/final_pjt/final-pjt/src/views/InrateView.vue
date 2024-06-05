<template>
  <div class="ms-2 me-2">
    <div class="interest-rate-container mt-5">
      <h2 class="mt-4 fw-bold">예금 상품 금리 비교</h2>
      <div class="d-flex justify-content-center align-items-center">
        <div>
          <a class="activeChoose m-1 p-1 fw-bold" @click.prevent="gotoInrate">예금보기</a>
        </div>
        <div>
          <a class="choose-item m-1 p-1 fw-bold" @click.prevent="gotoInrate2">적금보기</a>
        </div>
      </div>
      <div class="table-header mt-5 align-items-center">
        <div>
          <span class="me-2 fw-bold">기간 :</span>
          <select v-model="selectedTerm" @change="filterByTerm">
            <option v-for="term in terms" :key="term" :value="term">
              <p v-if="term == '전체'">{{ term }}</p>
              <p v-else>{{ term }}개월</p>
            </option>
          </select>
        </div>
        <span class="d-flex align-items-center justify-content-end">
          <div>
            <!-- <span class="text-danger">(최고 금리)</span> -->
            <span class="inrate-margin">최고 금리 순으로 정렬</span>
          </div>
          <div>
            <span class="sort-icons d-flex flex-column" @click="sorting">
              <i class="fas fa-caret-up icon-box" :class="isAscendSort ? '' : 'opacity-25'"></i>
              <i class="fas fa-caret-down icon-box" :class="isAscendSort ? 'opacity-25' : ''"></i>
            </span>
          </div>
        </span>
      </div>
      <div v-if="products.length">
        <div class="product-list">
          <div v-for="product in filteredProducts" :key="product.id" class="product-row text-start"
            >
            <div v-if="product.product.kor_co_nm == '수협은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_SH_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>수협은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '우리은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_WOORI_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>우리은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '전북은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_JEONBUK_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>전북은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '주식회사 케이뱅크'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_K_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>케이뱅크</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '농협은행주식회사'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_NH_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>농협은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '한국스탠다드차타드은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://lh6.googleusercontent.com/proxy/-fHKd1Gi4KIjBfk2h7L9ClrLqVoWucJRx_JrvMvWn04fSrq1xt8fxXIPJtqWCUC99zHnHp0b2kv9hJopMHf5VR55RIByoEah6l6Kh6qbQxWJIvug8YiVXY9FDjafyXuubxK4VgeXZ-4OR5mJYh2CM_AFZ0kDd_MAVq4AHUF-JqD7Jew63WzSC-CX6odz_AtG_IXCmxsNfnXUpKwMKon_u81s1zAi3q2sNe7W7m_O7I1rHNKBZ9oN-Ji9UxLV6BWo3Ts7q36HeLhiWYdlQgoIYzdPYW2mNa8fqda9ymrgor7MFpOVSr3-4jBSY5Uu1r_ddj7foAn4256eMkYv9o-fiuEQq2cQkokPX5DV7oIja30N"
                    alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>스탠다드차타드은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '광주은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_KWANGJU_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>광주은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '주식회사 카카오뱅크'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_KAKAO_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>카카오뱅크</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '제주은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_JEJU_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>제주은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '대구은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/IS_DGB_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>대구은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '부산은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_BUSAN_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>부산은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '신한은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_SHINHAN_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>신한은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '하나은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_HANA_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>하나은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '국민은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_KB_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>국민은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '한국산업은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_KDB_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>산업은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '중소기업은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_IBK_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>기업은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '경남은행'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_KYOUNGNAM_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>경남은행</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
              <div class="view-more">
                <span @click="gotoDetail(product.id)">자세히 보기</span>
              </div>
            </div>
            <div v-else-if="product.product.kor_co_nm == '토스뱅크 주식회사'" class="d-flex justify-content-between bank-relative">
              <div class="d-flex">
                <div>
                  <img class="bank-logo-img"
                    src="https://financial.pstatic.net/pie/common-bi/0.11.0/images/BK_TOSS_Profile.png" alt="">
                </div>
                <div>
                  <span class="fw-bold item-font">{{ product.product.fin_prdt_nm }}</span>
                  <p>토스뱅크</p>
                </div>
              </div>
              <div>
                <span class="text-end text-danger rate-font fw-bold">{{ product.intr_rate2 }}%</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <p class="text-primary fw-bold">로딩 중...</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, computed } from 'vue'
import axios from 'axios'
const isAscendSort = ref(null)
const selectedTerm = ref('전체')
const products = ref([])
const router = useRouter()

axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/fins/deposit_products/',
})
  .then(res => {
    products.value = res.data
  })
  .catch(err => {
    console.log(err)
  })

const terms = computed(() => {
  const uniqueTerms = [...new Set(products.value.map(product => product.save_trm))]
  uniqueTerms.sort((a, b) => a - b)
  return ['전체', ...uniqueTerms]
})

const filteredProducts = computed(() => {
  const productSet = new Set()
  const filtered = selectedTerm.value === '전체' ? products.value : products.value.filter(product => product.save_trm == selectedTerm.value)
  const uniqueFiltered = filtered.filter(product => {
    const productIdentifier = `${product.product.fin_prdt_nm}-${product.intr_rate2}`
    if (productSet.has(productIdentifier)) {
      return false
    } else if (product.intr_rate2 != null) {
      productSet.add(productIdentifier)
      return true
    }
  })
  return uniqueFiltered.slice().sort((a, b) => {
    return sortOption.value === 'desc' ? b.intr_rate2 - a.intr_rate2 : a.intr_rate2 - b.intr_rate2
  })
})

const sortOption = ref('desc')

const sorting = () => {
  if (isAscendSort.value === true) {
    sortDescending()
  } else {
    sortAscending()
  }
}

const sortDescending = () => {
  sortOption.value = 'desc'
  isAscendSort.value = false
}

const sortAscending = () => {
  sortOption.value = 'asc'
  isAscendSort.value = true
}

const filterByTerm = () => {
  // No additional code needed here as the computed property filteredProducts will reactively update
}

const gotoInrate = function () {
  router.push({ name: 'inrate' })
}

const gotoInrate2 = function () {
  router.push({ name: 'inrate2' })
}

const gotoDetail = function (id) {
  router.push({ name: 'inratedetail', params: { 'id': id } })
  .then((() =>window.scrollTo(0,0) ))
}

</script>

<style scoped>
.bank-relative {
  position: relative;
}
.view-more {
  position: absolute;
  bottom: 0;
  right: 0;
  font-size: 14px;
  opacity: 0.6;
  cursor: pointer;
}

.activeChoose {
  font-size: 1.1rem;
  color: #73a8f7;
  cursor: pointer;
  text-decoration: none;
}

.choose-item {
  font-size: 1.1rem;
  color: black;
  text-decoration: none;
}

.choose-item:hover {
  font-size: 1.1rem;
  color: #0d6efd;
  text-decoration: underline;
  cursor: pointer;
}

.bank-logo-img {
  width: 40px;
  margin-right: 10px;
  margin-top: 2px;
}

.icon-box {
  width: 20px;
  height: 15px;
}

.item-font {
  font-size: 1.2rem;
}

@media (max-width: 768px) {
  .item-font {
    font-size: 1.1rem;
  }
}

.rate-font {
  font-size: 1.2rem;
}

@media (max-width: 768px) {
  .rate-font {
    font-size: 1.1rem;
  }
}

.interest-rate-container {
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
  margin-bottom: 20px;
}

.table-header {
  display: flex;
  justify-content: space-between;
  font-weight: bold;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  margin-bottom: 2px;
}

.table-header span {
  flex: 1;
  text-align: center;
}

.sort-icons {
  margin-left: 5px;
}

.sort-icons i {
  cursor: pointer;
  margin-left: 5px;
}

.product-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-top: 20px;
}

.product-row {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f8f9fa;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);

}

.product-row span {
  flex: 1;
  text-align: left;
}
</style>
