<template>
  <div class="me-2 ms-2">
    <div class="board-container mt-5">
      <h2 class="mt-3 text-center fw-bold">커뮤니티</h2>
      <div class="posts mt-5">
        <div v-for="post in posts" :key="post.id" class="post d-flex justify-content-between align-items-center" @click="gotoDetail(post.id)">
          <div>
            <h3>{{ post.title }}</h3>
            <p class="author-time">{{ post.nickname }} · {{ formattedDate(post.created_at) }}</p>
          </div>
          <div class="comments text-center">
            <p class="comment-sum">{{ post.comment_count }}</p>
            <p class="comment">댓글</p>
          </div>
        </div>
      </div>
      <div class="text-right">
        <button class="pt-2 pb-2 ps-3 pe-3" @click="goToAddPost">글쓰기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const posts = ref([])

axios({
  method: 'get',
  url: 'http://127.0.0.1:8000/api/v1/articles/',
})
  .then(res => {
    // console.log(res.data)
    posts.value = res.data
    console.log(posts.value)
  })
  .catch(err => {
    console.log(err)
  })


const router = useRouter()

const goToAddPost = () => {
  router.push({ name: 'communityaddpost' })
}

const gotoDetail = function (id) {
  router.push({ name: 'communitydetail', params: { 'id': id } })
}
const formattedDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString('ko-KR');
};

</script>

<style scoped>
.board-container {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}


button {
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  margin-bottom: 20px;
}

button:hover {
  background-color: #0056b3;
}

.posts {
  display: flex;
  flex-direction: column;
}

.post {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.author-time {
  font-size: 14px;
  color: #888;
}

.comments {
  /* text-align: right; */
  /* font-size: 14px; */
  /* color: #888; */
  border: 0px solid #ddd;
  border-radius: 5px;
  background-color: gainsboro;
  padding-top: 15px;
  /* padding-bottom: 7px; */
  padding-left: 10px;
  padding-right: 10px;
}

.comment-sum {
  margin-bottom: -1px;
  font-size: 18px;
  font-weight: bold;
}

.comment {
  font-size: 14px;
}

.text-right {
  text-align: right;
}

.shadow-lg {
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
}
</style>
