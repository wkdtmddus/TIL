<template>
  <div>
    <h1>데이터 수정 페이지</h1>
    <p>이름: {{ balance.name }}</p>
    <p>잔고: {{ balance.balance }}</p>
    <button @click="upBalance(balance)">+</button>
  </div>
</template>

<script setup>
import { useBalanceStore } from '@/stores/balance'
import { useRoute } from 'vue-router'
const store = useBalanceStore()
const route = useRoute()
const name = route.params.name
const balance = store.findBalance(name)
const upBalance = function (balance) {
  store.increaseBalance(balance)
}
</script>

<style scoped>
</style>