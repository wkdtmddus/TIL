<template>
  <div v-if="detailpost" class="me-2 ms-2">
    <div class="post-container mt-5">
      <div class="post-header">
        <div class="d-flex justify-content-between flex-wrap">
          <div>
            <h1 class="post-title mb-3">{{ detailpost.title }}</h1>
          </div>
          <div v-if="store.editusername === detailpost.username" class="d-flex flex-wrap">
            <button type="submit" class="btn btn-primary me-3" @click="goeditpage(detailpost.id)">수정</button>
            <button type="submit" class="btn btn-danger" @click="confirmDeletePost(detailpost.id)">삭제</button>
          </div>
        </div>
        <p class="post-author mt-3">작성자: {{ detailpost.nickname }}</p>
        <p class="post-date">작성 날짜: {{ formattedDate(detailpost.created_at) }}</p>
      </div>
      <div class="post-content">
        <p>{{ detailpost.content }}</p>
      </div>
    </div>
    <div v-if="store.isLogin" class="post-container mt-5">
      <h5 class="fw-bold">댓글 작성</h5>
      <hr>
      <div class="comment-form">
        <form @submit.prevent="addComment(comment, detailpost.id)">
          <textarea v-model="comment" placeholder="댓글 남겨보세요." rows="2" class="form-control mb-3"></textarea>
          <div class="text-end">
            <button type="submit" class="btn btn-primary">작성</button>
          </div>
        </form>
      </div>
    </div>
    <div class="post-container mt-5">
      <h4 class="fw-bold">댓글</h4>
      <hr>
      <div class="post-content">
        <div v-for="comment in detailpost.comment_set" :key="comment.id">
          <p class="post-author fw-bold">작성자 : {{ comment.nickname }}</p>
          <p class="post-date">작성 날짜: {{ formattedDate(comment.created_at) }}</p>
          <p>내용 : {{ comment.content }}</p>
          <div v-if="store.editusername === comment.username" class="d-flex justify-content-end">
            <button class="btn btn-primary me-2" @click="editComment(comment)">수정</button>
            <button class="btn btn-danger" @click="confirmDeleteComment(comment.id)">삭제</button>
          </div>
          <hr>
        </div>
      </div>
    </div>
    <div v-if="isEditingComment" class="modal-overlay">
      <div class="modal-content">
        <h2>댓글 수정</h2>
        <textarea v-model="editingCommentContent" rows="4" class="form-control mb-3"></textarea>
        <div class="modal-buttons">
          <button class="btn btn-secondary" @click="closeEditCommentModal">취소</button>
          <button class="btn btn-primary" @click="confirmEditComment">수정</button>
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <div class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <p class="text-primary fw-bold">로딩 중...</p>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const route = useRoute();
const router = useRouter();

const detailpost = ref([]);
const comment = ref('');
const isEditingComment = ref(false);
const editingCommentId = ref(null);
const editingCommentContent = ref('');

axios({
  method: 'get',
  url: `http://127.0.0.1:8000/api/v1/articles/${route.params.id}`,
})
  .then(res => {
    detailpost.value = res.data;
    console.log(detailpost.value);
  })
  .catch(err => {
    console.log(err);
  });

const formattedDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString('ko-KR');
};

const addComment = (comment, article_id) => {
  store.addcomment(comment, article_id);
};

const goeditpage = (id) => {
  router.push({ name: 'communityedit', params: { id } });
};

const confirmDeletePost = (article_id) => {
  if (confirm('정말로 게시글을 삭제하시겠습니까?')) {
    store.deletePost(article_id);
  }
};

const editComment = (comment) => {
  isEditingComment.value = true;
  editingCommentId.value = comment.id;
  editingCommentContent.value = comment.content;
};

const closeEditCommentModal = () => {
  isEditingComment.value = false;
  editingCommentId.value = null;
  editingCommentContent.value = '';
};

const confirmEditComment = () => {
  if (editingCommentContent.value) {
    store.editComment(editingCommentId.value, editingCommentContent.value)
      .then(() => {
        closeEditCommentModal();
        location.reload(); // 페이지를 새로고침하여 변경 사항을 반영합니다.
      })
      .catch(err => {
        console.log(err);
        alert('댓글 수정에 실패했습니다.');
      });
  } else {
    alert('댓글 내용을 입력해주세요.');
  }
};

const confirmDeleteComment = (comment_id) => {
  if (confirm('정말로 댓글을 삭제하시겠습니까?')) {
    store.deleteComment(comment_id)
      .then(() => {
        location.reload(); // 페이지를 새로고침하여 변경 사항을 반영합니다.
      })
      .catch(err => {
        console.log(err);
        alert('댓글 삭제에 실패했습니다.');
      });
  }
};
</script>

<style scoped>
.post-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.post-header {
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  margin-bottom: 20px;
}

.post-title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.post-author,
.post-date {
  font-size: 14px;
  color: #888;
  margin-bottom: 5px;
}

.post-content {
  font-size: 18px;
  line-height: 1.6;
  color: #444;
}

.comment-form {
  margin-top: 20px;
}

.comment-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ddd;
  resize: none;
}

.comment-form button {
}

.comment {
  margin-top: 20px;
}

.comment .fw-bold {
  font-size: 16px;
}

.comment p {
  margin: 5px 0;
}

.comment hr {
  margin-top: 10px;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.modal-buttons .btn {
  width: 45%;
}
</style>
