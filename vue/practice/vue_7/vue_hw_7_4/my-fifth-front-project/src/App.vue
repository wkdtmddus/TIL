<template>
  <div>
    <nav>
      <RouterLink :to="{ name: 'main' }">main</RouterLink>
    </nav>
    <h1>pinia + router 연습하기</h1>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useBalanceStore } from '@/stores/balance'
const store = useBalanceStore()
</script>


<style scoped>
</style>
