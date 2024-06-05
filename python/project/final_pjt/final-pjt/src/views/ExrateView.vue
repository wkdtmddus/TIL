<template>
  <div class="me-2 ms-2">
    <div class="exchange-container mt-5">
      <h2 class="text-center mt-3 mb-5 fw-bold">환율계산</h2>
      <div class="exchange-row d-flex flex-column">
        <div class="currency-box">
          <select v-model="fromCountry" @change="convertCurrency">
            <option v-for="(name, country) in currencySymbol" :key="country" :value="country">
              {{ country }} - {{ name }}
            </option>
          </select>
        </div>
        <div class="">
          <input class="text-end" type="number" v-model.number="amount" @input="convertCurrency" />
        </div>
      </div>
      <div class="equal-sign mb-3 text-center">=</div>
      <div class="exchange-row">
        <div class="currency-box">
          <select v-model="toCountry" @change="convertCurrency">
            <option v-for="(name, country) in currencySymbol" :key="country" :value="country">
              {{ country }} - {{ name }}
            </option>
          </select>
        </div>
        <div class="converted-value">
          <span>{{ convertedAmount.toFixed(2) }} {{ currencySymbol[toCountry] }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, watchEffect } from 'vue'
import axios from 'axios'

const amount = ref(1)
const convertedAmount = ref(0)
const fromCountry = ref('USD')
const toCountry = ref('KRW')
const exchangeRates = ref({})
const currencySymbol = {
  AED: '디르함',
  AUD: '호주 달러',
  BHD: '바레인 디나르',
  BND: '브루나이 달러',
  CAD: '캐나다 달러',
  CHF: '스위스 프랑',
  CNH: '중국 위안',
  DKK: '덴마크 크로네',
  EUR: '유로',
  GBP: '파운드',
  HKD: '홍콩 달러',
  'IDR(100)': '인도네시아 루피아(100)',
  'JPY(100)': '일본 엔(100)',
  KWD: '쿠웨이트 디나르',
  MYR: '말레이시아 링깃',
  NOK: '노르웨이 크로네',
  NZD: '뉴질랜드 달러',
  SAR: '사우디 리얄',
  SEK: '스웨덴 크로나',
  SGD: '싱가포르 달러',
  THB: '태국 바트',
  USD: '미국 달러',
  KRW: '원'
}

// Fetch exchange rates from the server
axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/rates/',
})
  .then(res => {
    if (Array.isArray(res.data.response)) {
      res.data.response.forEach(rate => {
        exchangeRates.value[rate.cur_unit] = parseFloat(rate.deal_bas_r.replace(',', ''))
      })
      console.log(exchangeRates.value)
      exchangeRates.value['KRW'] = 1 // Add KRW with its base rate
      convertCurrency() // Convert currency after fetching rates
    } else {
      console.error('Unexpected data format:', res.data)
    }
  })
  .catch(err => {
    console.log(err)
  })

const convertCurrency = () => {
  if (fromCountry.value === toCountry.value) {
    convertedAmount.value = amount.value
    return
  }

  const fromRate = exchangeRates.value[fromCountry.value]
  const toRate = exchangeRates.value[toCountry.value]

  if (fromRate && toRate) {
    const baseToKRW = amount.value * fromRate
    convertedAmount.value = baseToKRW / toRate
  }
}

watchEffect(() => {
  convertCurrency()
})
</script>


<style scoped>
.text-end {
  width: 100%;
  padding: 8px;
  font-size: 18px;
  font-weight: bold;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}
.exchange-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}

.exchange-row {

  justify-content: space-between;
  margin-bottom: 20px;
}

.currency-box {
  align-items: center;
}

.currency-box select {
  padding: 13px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
  width:100%;
  margin-bottom: 0.5rem;
}

.currency-box input {
  padding: 8px;
  font-size: 18px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}


.currency-box input {
  width: 90%;
  text-align: right;
}

.converted-value {
  padding: 8px;
  font-size: 18px;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 100%;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}

.equal-sign {
  font-size: 30px;
  font-weight: bold;
  margin: 0 20px;
}
</style>
