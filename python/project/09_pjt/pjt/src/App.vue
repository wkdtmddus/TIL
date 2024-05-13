<script setup>

</script>

<template>
  <div>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/search">Search</RouterLink>
      <RouterLink to="/later">Later</RouterLink>
    </nav>
  </div>
  <div class="container">
  <RouterView />
</div>
</template>

<style scoped>

</style>
