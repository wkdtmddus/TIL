<template>
  <div>
    <p>이름: {{ balance.name }}</p>
    <p>잔고: {{ balance.balance }}</p>
    <button @click="goBalance(balance.name)">updateBalance</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
defineProps({
  balance: Object
})
const router = useRouter()
const goBalance = event => {
  router.push({ name: 'update', params: { name: event }})
}
</script>

<style scoped>
</style>