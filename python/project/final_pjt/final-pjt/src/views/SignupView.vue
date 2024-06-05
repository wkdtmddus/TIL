<script>
export default {
  created() {
    console.log(this.$isMobile())
  }
}
</script>

<template>
  <div v-if="!$isMobile()" class="container text-center p-5 mt-5">
    <h2 class="fw-bold">지금 회원가입 후 카드 신청시</h2>
    <h2 class="fw-bold">캐쉬백 1만원!</h2>
  </div>
  <div v-if="$isMobile()" class="signup-container mt-5">
    <div class="mobile-card p-4">
      <div class="card-title text-center mb-4">
        <h3 class="fw-bold text-start">회원가입</h3>
      </div>
      <div class="form-container">
        <form @submit.prevent="signUp">
          <div class="mb-3">
            <label for="username" class="form-label">아이디</label>
            <input type="text" id="username" class="form-control" v-model.trim="username">
          </div>
          <div class="mb-3">
            <label for="password1" class="form-label">비밀번호</label>
            <input type="password" id="password1" class="form-control" v-model.trim="password1">
            <div v-if="passwordTooShort" class="text-danger">비밀번호는 8자리 이상이며 영문, 숫자, 특수문자((!@#$%^&*()))를 포함해야 합니다</div>
          </div>
          <div class="mb-3">
            <label for="password2" class="form-label">비밀번호 재확인</label>
            <input type="password" id="password2" class="form-control" v-model.trim="password2">
            <div v-if="passwordMismatch" class="text-danger">비밀번호가 일치하지 않습니다.</div>
            <div v-if="passwordMatch" class="text-ok">비밀번호가 일치 합니다.</div>
            
          </div>
          <div class="mb-3">
            <label for="nickname" class="form-label">닉네임</label>
            <input type="text" id="nickname" class="form-control" v-model.trim="nickname">
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">이메일</label>
            <input type="email" id="email" class="form-control" v-model.trim="email">
          </div>
          <button type="submit" class="btn btn-primary w-100">회원가입</button>
          <div v-if="signUpError" class="text-danger mb-1 text-center">{{ signUpError }}</div>
        </form>
      </div>
    </div>
  </div>

  <div v-else class="signup-container mt-5">
    <div class="card shadow-lg p-4">
      <div class="card-title text-center mb-4">
        <h3 class="fw-bold">회원가입</h3>
      </div>
      <div class="form-container">
        <form @submit.prevent="signUp">
          <div class="mb-3">
            <label for="username" class="form-label">아이디</label>
            <input type="text" id="username" class="form-control" v-model.trim="username">
          </div>
          <div class="mb-3">
            <label for="password1" class="form-label">비밀번호</label>
            <input type="password" id="password1" class="form-control" v-model.trim="password1">
            <div v-if="passwordTooShort" class="text-danger">비밀번호는 8자리 이상이며 영문, 숫자, 특수문자((!@#$%^&*()))를 포함해야 합니다</div>
          </div>
          <div class="mb-3">
            <label for="password2" class="form-label">비밀번호 재확인</label>
            <input type="password" id="password2" class="form-control" v-model.trim="password2">
            <div v-if="passwordMismatch" class="text-danger">비밀번호가 일치하지 않습니다.</div>
            <div v-if="passwordMatch" class="text-ok">비밀번호가 일치 합니다.</div>
            
          </div>
          <div class="mb-3">
            <label for="nickname" class="form-label">닉네임</label>
            <input type="text" id="nickname" class="form-control" v-model.trim="nickname">
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">이메일</label>
            <input type="email" id="email" class="form-control" v-model.trim="email">
          </div>
          <button type="submit" class="btn btn-primary w-100">회원가입</button>
          <div v-if="signUpError" class="text-danger mb-1 text-center">{{ signUpError }}</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useCounterStore } from '@/stores/counter'

const username = ref(null)
const nickname = ref(null)
const email = ref(null)
const password1 = ref(null)
const password2 = ref(null)
const signUpError = ref(null)

const store = useCounterStore()

const passwordTooShort = computed(() => password1.value && password1.value.length < 8)
const passwordMismatch = computed(() => password1.value && password2.value && password1.value !== password2.value)
const passwordMatch = computed(() => password1.value && password2.value && password1.value == password2.value)

const signUp = () => {
  // 회원가입 조건 검사
  if (passwordTooShort.value) {
    signUpError.value = "비밀번호를 더 복잡하게 만들어 주세요."
    return
  }
  
  if (passwordMismatch.value) {
    signUpError.value = "비밀번호가 일치하지 않습니다."
    return
  }

  // 회원가입 조건을 모두 만족하면 경고 메시지를 지웁니다.
  signUpError.value = null

  const payload = {
    username: username.value,
    email: email.value,
    password1: password1.value,
    password2: password2.value,
    nickname: nickname.value,
  }
  store.signUp(payload)
}
</script>

<style scoped>
.text-ok {
  color: #33ff33;
  /* color: red; */
  font-size: 0.875em;
  margin-top: 0.25em;
}

.background-color {
  background-color: #DBEAFF;
}

.signup-container {
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
