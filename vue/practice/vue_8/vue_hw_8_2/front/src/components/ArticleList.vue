<template>
  <div>
    <p
      v-for="article in store.articles"
      :key="article.id"
      :article="article"
      >
      {{ article }}
    </p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useArticleStore } from '@/stores/articles'
const store = useArticleStore()

onMounted(() => {
  store.getArticles()
})
</script>

<style lang="scss" scoped>

</style>