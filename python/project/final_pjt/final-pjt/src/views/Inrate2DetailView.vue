<template>
  <div>
    적금 상세
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute()

axios({
  method: 'get',
  url: `http://127.0.0.1:8000/fins/deposit_product_option/${route.params.id}/`,
})
  .then(res => {
    console.log(res.data)
  })
  .catch(err => {
    console.log(err)
  })
</script>

<style scoped>

</style>