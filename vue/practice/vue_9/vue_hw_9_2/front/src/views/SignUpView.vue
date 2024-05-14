<template>
  <div>
    <h1>회원 가입 페이지</h1>
    <form @submit.prevent="signup">
      <div>
        <label for="username">username : </label>
        <input type="text" v-model.trim="username" id="username">
      </div>
      <div>
        <label for="password1">password : </label>
        <input type="password" v-model.trim="password1" id="password1">
      </div>
      <div>
        <label for="password2">password Confirmation : </label>
        <input type="password" v-model.trim="password2" id="password2">
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
const password1 = ref(null)
const password2 = ref(null)
const signup = function () {
  const payload = {
    username: username.value,
    password1: password1.value,
    password2: password2.value
  }
  store.signup(payload)
}
</script>

<style lang="scss" scoped>

</style>