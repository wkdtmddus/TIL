<script>
export default {
  created() {
    console.log(this.$isMobile())
  }
}
</script>

<template>
  <div v-if="$isMobile()" class="login-container mt-5">
    <div class="mobile-card p-4">
      <div class="card-title text-center mb-4">
        <h3 class="fw-bold text-start">로그인</h3>
      </div>
      <div class="form-container">
        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">아이디</label>
            <input type="text" id="username" class="form-control" v-model.trim="username">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" id="password" class="form-control" v-model.trim="password">
            <div v-if="passwordTooShort" class="text-danger">비밀번호는 8자리 이상이며 영문, 숫자, 특수문자((!@#$%^&*()))를 포함해야 합니다</div>
          </div>
          <button type="submit" class="btn btn-primary w-100">로그인</button>
        </form>
      </div>
      <div class="text-center mt-3">
        <a href="#" class="sign-a" @click.prevent="gotoSignup()">회원가입</a>
      </div>
    </div>
  </div>
  <div v-else class="login-container mt-5">
    <div class="card shadow-lg p-4">
      <div class="card-title text-center mb-4">
        <h3 class="fw-bold">로그인</h3>
      </div>
      <div class="form-container">
        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">아이디</label>
            <input type="text" id="username" class="form-control" v-model.trim="username">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" id="password" class="form-control" v-model.trim="password">
            <div v-if="passwordTooShort" class="text-danger">비밀번호는 8자리 이상이며 영문, 숫자, 특수문자((!@#$%^&*()))를 포함해야 합니다</div>
          </div>
          <button type="submit" class="btn btn-primary w-100">로그인</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>

import { ref, computed } from 'vue'
import { useCounterStore } from '@/stores/counter'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const username = ref(null)
const password = ref(null)


const store = useCounterStore()

const passwordTooShort = computed(() => password.value && password.value.length < 8)

const login = () => {
  const payload = {
    username: username.value,
    password: password.value,
  }
  store.login(payload)
}

const gotoSignup = function () {
  router.push({name: 'signup'})
}

</script>



<style scoped>
.sign-a {
  /* text-decoration: none; */
  border: none;
  color: #787878;
  border-radius: 0;
  font-size: 13px;
  letter-spacing: unset;
  line-height: 20px;
}

.background-color {
  background-color: #DBEAFF;
}

.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.card {
  max-width: 500px;
  width: 100%;
  border-radius: 10px;
}

.mobile-card {
  max-width: 500px;
  width: 100%;
}

.form-label {
  font-weight: bold;
}

.form-control {
  border-radius: 5px;
}

.btn {
  border-radius: 5px;
}

.shadow-lg {
  box-shadow: 0 1rem 3rem rgba(0, 0, 0, 0.1);
}

.text-danger {
  color: #dc3545;
  font-size: 0.875em;
  margin-top: 0.25em;
}
</style>
