<template>
  <div v-if="movie">
    <h1>{{ movie[0].snippet.title }}</h1>
    <p>업로드 날짜 : {{ datetime }}</p>
    <!-- {{ movieUrl }} -->
    <iframe width="560" height="315" :src="movieUrl" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    <p>{{ movie[0].snippet.description }}</p>

    <button class="btn btn-primary" type="submit" @click="likemovie(movie)">동영상 저장</button>
  </div>
  <div v-else>
    로딩 중...
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
import { useMovieStore } from '@/stores/movie'
const store = useMovieStore()
const route = useRoute()
const movie = ref([])
const movieUrl = ref('')
const datetime = ref('')
axios({
      method: 'get',
      url: `https://www.googleapis.com/youtube/v3/videos?part=snippet&id=${route.params.videoId}&key=AIzaSyD8zT4zscEYXo0ndCnJ_E0HitP-eM3J9lc`
    }).then((response) => {
      // console.log(response.data.items)
      console.log(response)
      movie.value = response.data.items
      movieUrl.value = `http://www.youtube.com/embed/${response.data.items[0].id}`
      datetime.value = response.data.items[0].snippet.publishedAt.split('T')[0]
    }).catch((error) => {
      console.log(error)
    })




// const detailMovie = function () {
//   store.getMovieStore(route.params.videoId)
// }

// const demovie = ref(detailMovie())
// console.log(demovie.value)

const likemovie = function (movie) {
  // console.log(movie[0].snippet)
  console.log(store.likemovie)
  const check = store.likemovie.findIndex(movie)
  if (check === -1) {
    store.likemovie.push(movie)
  } else {
    store.likemovie.slice(check, 1)
  }
}

</script>

<style scoped>

</style>