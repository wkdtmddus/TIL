<template>
  <div>
    <h1>로그인 페이지</h1>
    <form @submit.prevent="signin">
      <div>
        <label for="username">username : </label>
        <input type="text" id="username" v-model.trim="username">
      </div>
      <div>
        <label for="password">password : </label>
        <input type="password" id="password" v-model.trim="password">
      </div>
      <button>submit</button>
    </form>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue'
import { useArticleStore } from '@/stores/articles'
const router = useRouter()
const store = useArticleStore()
const username = ref(null)
const password = ref(null)
const signin = function () {
  const payload = {
    username: username.value,
    password: password.value
  }
  store.signin(payload)
}
</script>

<style lang="scss" scoped>

</style>