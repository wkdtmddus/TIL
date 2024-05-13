<template>
  <div>
    <h1>게시글 목록 페이지</h1>
    <button @click="goCreate">게시글 생성</button>
    <ArticleList />
  </div>
</template>

<script setup>
import ArticleList from '../components/ArticleList.vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const goCreate = function () {
  router.push({ name: 'create'})
}
</script>

<style lang="scss" scoped>

</style>