<template>
  <div class="add-post-container mt-5">
    <h1>게시물 추가</h1>
    <form @submit.prevent="submitPost(title, content)">
      <input v-model="title" placeholder="제목" required>
      <textarea v-model="content" placeholder="내용" required></textarea>
      <button type="submit">추가</button>
      <button type="button" @click="cancel">취소</button>
    </form>
  </div>
</template>

<script setup>

import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useCounterStore } from '@/stores/counter';
const store = useCounterStore()
const title = ref('')
const content = ref('')
const router = useRouter()

const submitPost = (title, content) => {
  // 새로운 게시물 추가 로직
  store.addpost(title, content)
}

const cancel = () => {
  router.go(-1)
}
</script>

<style scoped>
.add-post-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

h1 {
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

input, textarea {
  margin-bottom: 10px;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  margin-bottom: 10px;
}

button[type="button"] {
  background-color: #6c757d;
}

button:hover {
  background-color: #0056b3;
}

button[type="button"]:hover {
  background-color: #5a6268;
}
</style>
