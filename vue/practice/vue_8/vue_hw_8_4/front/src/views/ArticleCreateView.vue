<template>
  <div>
    <h1>게시글 생성 페이지</h1>
    <form @submit.prevent="create">
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model="title">
      <label for="content">내용 : </label>
      <textarea id="content" v-model="content"></textarea>
      <button type="submit">create</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useArticleStore } from '@/stores/articles.js'
const store = useArticleStore()
const title = ref(null)
const content = ref(null)
const create = function () {
  store.createArticle(title.value, content.value)
}
</script>

<style lang="scss" scoped>

</style>