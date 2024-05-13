<template>
  <div class="m-2">
  <div v-if="movie">
    <div class="card" style="width: 18rem;">
      <img :src="movie.snippet.thumbnails.default.url" class="card-img-top" alt="...">
      <div class="card-body">
        <p class="card-text">{{ movie.snippet.title }}</p>
      </div>
    </div>
  </div>
  <div v-else>
    로딩 중...
  </div>
</div>

</template>

<script setup>
defineProps({
  movie: Object
})
</script>

<style scoped></style>