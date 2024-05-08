<template>
  <div>
    <h3>자식 페이지입니다.</h3>
    <p>이름 : {{ prop.name }}</p>
    <p>나이 : {{ prop.age }}</p>
    <p>용돈 : {{ prop.balance }}</p>
    <button @click="giveMeAllowance(prop)">용돈 더 주세요</button>
  </div>
</template>

<script setup>
defineProps({
  prop: Object
})
import { ref } from 'vue'
const emit = defineEmits(['giveMeAllowance'])
const giveMeAllowance = data => {
  emit('giveMeAllowance', data)
}
</script>

<style scoped>
</style>
