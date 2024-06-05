<script>
export default {
  created() {
    console.log(this.$isMobile())
  }
}
</script>

<template>
  <div v-if="$isMobile()" class="mobile-edit-profile-container margin-top">
    <h2 class="fw-bold text-start">회원정보 수정</h2>
    <form @submit.prevent="updateProfile">
      <div class="form-group">
        <label for="username">아이디</label>
        <input
          type="text"
          id="username"
          v-model="store.editusername"
          placeholder="Enter your username"
        />
      </div>
      <div class="form-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="store.editemail"
          placeholder="Enter your email"
        />
      </div>
      <div class="form-group">
        <label for="nickname">닉네임</label>
        <input
          type="text"
          id="nickname"
          v-model="store.editnickname"
          placeholder="Enter your nickname"
        />
      </div>
      <button type="submit">변경하기</button>
    </form>
  </div>
  <div v-else class="edit-profile-container margin-top">
    <h2>회원정보 수정</h2>
    <form @submit.prevent="updateProfile">
      <div class="form-group">
        <label for="username">아이디</label>
        <input
          type="text"
          id="username"
          v-model="store.editusername"
          placeholder="Enter your username"
        />
      </div>
      <div class="form-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="store.editemail"
          placeholder="Enter your email"
        />
      </div>
      <div class="form-group">
        <label for="nickname">닉네임</label>
        <input
          type="text"
          id="nickname"
          v-model="store.editnickname"
          placeholder="Enter your nickname"
        />
      </div>
      <button type="submit">변경하기</button>
    </form>
  </div>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter';
const store = useCounterStore()

const updateProfile = () => {
  // You can add the logic to update the profile information here
  console.log('Profile updated:', {
    username: store.editusername,
    email: store.editemail,
    nickname: store.editnickname
  });
  // For example, make an API call to save the changes
}
</script>

<style scoped>
.mobile-edit-profile-container {
  max-width: 600px;
  margin: 0 auto;
  margin-top: 5rem;
  padding: 20px;
  /* border: 1px solid #ddd; */
  /* border-radius: 10px; */
  /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
  background-color: #fff;
}
.edit-profile-container {
  max-width: 600px;
  margin: 0 auto;
  margin-top: 5rem;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
}

button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #0056b3;
}
</style>
