<template>
  <div class="ms-2 me-2 card-margin">
    <div class="container">
      <h2 class="fw-bold mb-4 mt-3">카드 추천</h2>
      <hr>
      <div class="filter-section">
        <div class="filter-item">
          <div class="filter-title">유형</div>
          <div class="filter-option" :class="{ 'selected': selectedType.includes('연회비 있음') }"
            @click="toggleSelection('type', '연회비 있음')">연회비</div>
          <div class="filter-option" :class="{ 'selected': selectedType.includes('신용카드') }"
            @click="toggleSelection('type', '신용카드')">신용</div>
          <div class="filter-option" :class="{ 'selected': selectedType.includes('체크카드') }"
            @click="toggleSelection('type', '체크카드')">체크</div>
        </div>
        <hr>
        <div class="filter-item">
          <div class="filter-title">혜택</div>
          <div class="filter-option" :class="{ 'selected': selectedBenefit.includes('교통') }"
            @click="toggleSelection('benefit', '교통')">교통</div>
          <div class="filter-option" :class="{ 'selected': selectedBenefit.includes('캐시백') }"
            @click="toggleSelection('benefit', '캐시백')">캐시백</div>
        </div>
        <hr>
      </div>
      <h4 class="fw-bold mt-5">추천 순위</h4>
      <div v-if="cards.length">
        <div v-if="filteredCards.length" class="card-recommendations">
          <div v-for="card in filteredCards" :key="card.id" class="card">
            <h5 class="fw-bold mt-3">{{ card.prdNm }}</h5>
            <hr>
            <p><strong>유형:</strong> {{ card.cadTpTcNm }}</p>
            <p><strong>가입자:</strong> {{ card.jinTgtCone }}</p>
            <p><strong>연회비:</strong> {{ card.anmfOtl }}</p>
            <p><strong>설명:</strong> {{ card.prdOtl }}</p>
          </div>
        </div>
        <div v-else>
          <p class="text-center">조건에 맞는 추천 카드를 찾을 수 없습니다.</p>
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
import { ref, computed } from 'vue';
import axios from 'axios';

const selectedType = ref([]);
const selectedBenefit = ref([]);

const cards = ref([]);

axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/cards/',
})
  .then(res => {
    console.log(res.data)
    cards.value = res.data.response.items
    console.log(cards.value)
  })
  .catch(err => {
    console.log(err)
  })

const toggleSelection = (category, value) => {
  if (category === 'type') {
    if (selectedType.value.includes(value)) {
      selectedType.value = selectedType.value.filter(item => item !== value);
    } else {
      selectedType.value.push(value);
    }
  } else if (category === 'benefit') {
    if (selectedBenefit.value.includes(value)) {
      selectedBenefit.value = selectedBenefit.value.filter(item => item !== value);
    } else {
      selectedBenefit.value.push(value);
    }
  }
};

const filteredCards = computed(() => {
  return cards.value.filter(card => {
    const hasAnnualFee = card.anmfOtl && card.anmfOtl.includes('있음');
    const isCreditCard = card.cadTpTcNm && card.cadTpTcNm.includes('신용');
    const isCheckCard = card.cadTpTcNm && card.cadTpTcNm.includes('체크');
    const hasTransportBenefit = card.prdOtl && card.prdOtl.includes('교통');
    const hasCashbackBenefit = card.prdOtl && card.prdOtl.includes('캐시백');

    const matchesType = selectedType.value.length === 0 ||
      (selectedType.value.includes('연회비 있음') && hasAnnualFee) ||
      (selectedType.value.includes('신용카드') && isCreditCard) ||
      (selectedType.value.includes('체크카드') && isCheckCard);

    const matchesBenefit = selectedBenefit.value.length === 0 ||
      (selectedBenefit.value.includes('교통') && hasTransportBenefit) ||
      (selectedBenefit.value.includes('캐시백') && hasCashbackBenefit);

    return matchesType && matchesBenefit;
  });
});
</script>


<style scoped>
.card-margin {
  margin-top: 5rem;
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
  color: #343a40;
}

.filter-section {
  margin-bottom: 20px;
}

.filter-item {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  /* 왼쪽 정렬 */
  gap: 10px;
  /* 항목 간격 */
}

.filter-title {
  font-weight: bold;
  margin-right: 20px;
  font-size: 20px;
}

.filter-option {
  padding: 10px;
  /* border: 1px solid #ddd; */
  border-radius: 5px;
  cursor: pointer;
  /* transition: color 0.3s; */
  font-size: 18px;
}

.filter-option.selected {
  font-weight: bold;
  color: #007bff;
  border-color: #007bff;
}

.card-recommendations {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-top: 20px;
}

.card {
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f8f9fa;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card h5 {
  margin: 0 0 10px;
}

.text-center {
  text-align: center;
}
</style>
