<template>
  <div v-if="store.articles">
    <div
      v-for="(article, index) in store.articles"
      :key="index+1"
      :article="article"
    >
    <h3>{{ index+1 }}번 게시글</h3>
    <p>제목 : {{ article.title }}</p>
    <p>내용 : {{ article.content }}</p>
    <hr>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useArticleStore } from '@/stores/articles'
const store = useArticleStore()
onMounted(() => {
  store.getArticles()
})
</script>

<style lang="scss" scoped>

</style>