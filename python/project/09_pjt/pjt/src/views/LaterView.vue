<template>
    <div class="d-flex flex-wrap">
    <Card v-for="movie in store.likemovies"
    :key="movie.id"
    :movie="movie[0]"/>
    <!-- {{ store.likemovies[0] }} -->
  </div>
</template>

<script setup>
import { useMovieStore } from '@/stores/movie'
import Card from '@/components/Card.vue';

const store = useMovieStore()
console.log(store.likemovies)
</script>

<style scoped>

</style>