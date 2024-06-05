<script setup>
import Navbar from '@/components/Navbar.vue';
import Footer from '@/components/Footer.vue';
import BottomNavbar from './components/BottomNavbar.vue';
import MobileTopbar from './components/MobileTopbar.vue';
</script>
<script>
export default {
  created() {
    console.log(this.$isMobile())
  }
}
</script>

<template>
  <div v-if="$isMobile()">
  <MobileTopbar />
  </div>
  <div v-else>
  <Navbar />
  </div>
  <div v-if="$isMobile()">
  </div>
  <div v-else class="default-margin">
  </div>
  <RouterView />
  <div v-if="$isMobile()">
  <BottomNavbar/>
  </div>
  <Footer />
</template>

<style scoped>
.default-margin {
  height :100px;
}
</style>
