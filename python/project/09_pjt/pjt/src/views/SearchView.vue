<template>
  <div>
    <h1>비디오 검색</h1>
    <input type="text" v-model="inputText">
    <button @click="findMovie">찾기</button>
    <!-- {{ store.movies }} -->
    <div class="d-flex flex-wrap">
    <Card
    v-for="movie in store.movies"
    :key="movie.id"
    :movie="movie"
    @click="gotoDetailPage(movie.id.videoId)"/>
    </div>
    
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Card from '@/components/Card.vue'
import { useMovieStore } from '@/stores/movie'
import { useRouter } from 'vue-router'
const router = useRouter()
const store = useMovieStore()

const inputText = ref('')
const findMovie = function () {
  store.findMovieStore(inputText.value)
}

const gotoDetailPage = function (videoId) {
  router.push({name: 'search-detail', params:{'videoId':videoId}})
}
</script>

<style scoped>
</style>