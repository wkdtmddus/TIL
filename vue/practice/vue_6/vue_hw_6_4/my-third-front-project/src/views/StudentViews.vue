<template>
  <div>
    <h1>학생 상세 정보 목록 입니다.</h1>
    <p v-for="student in students" :key="student.id" @click="goStudent(student)">
        {{ student.name }} 학생 정보 보기
    </p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter  } from 'vue-router'
const students = ref([
  { id: 1, name: '김하나'},
  { id: 2, name: '김두리'},
  { id: 3, name: '김서이'}
])
const router = useRouter()
const goStudent = event => {
  router.push({ name: 'student', params: { name: event.name } })
}
</script>

<style scoped>

</style>
