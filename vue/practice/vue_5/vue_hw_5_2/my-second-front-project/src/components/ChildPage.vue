<template>
  <div class="style-1">
    <h3>자식 페이지입니다.</h3>
    <p>이름 : {{ prop.name }}</p>
    <p>나이 : {{ prop.age }}</p>
  </div>
</template>

<script setup>
defineProps({
  prop: Object
})

</script>

<style scoped>

</style>
