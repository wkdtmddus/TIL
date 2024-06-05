<template>
  <div class="container mt-5">
    <h2 class="mb-4">글 수정하기</h2>
    <form @submit.prevent="updatePost(title, content, route.params.id)">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" id="title" v-model="title">
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea class="form-control" id="content" rows="10" v-model="content"></textarea>
      </div>
      <div class="text-end">
      <button type="submit" class="btn btn-primary">저장하기</button>
      <button type="button" class="btn btn-secondary" @click="cancelEdit">취소하기</button>
    </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore()

const route = useRoute();
const router = useRouter();

const title = ref('');
const content = ref('');

onMounted(() => {
  axios({
    method: 'get',
    url: `http://127.0.0.1:8000/api/v1/articles/${route.params.id}`,
  })
    .then(res => {
      console.log(res.data)
      title.value = res.data.title;
      content.value = res.data.content;
    })
    .catch(err => {
      console.log(err);
    });
});

const updatePost = (title, content, article_id) => {
 store.editPost(title, content, article_id)
}

const cancelEdit = () => {
  router.go(-1);
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

h2 {
  text-align: center;
}

.form-label {
  font-weight: bold;
}

.form-control {
  font-size: 16px;
}

.btn {
  margin-right: 10px;
}
</style>
