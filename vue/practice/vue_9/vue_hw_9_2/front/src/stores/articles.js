import axios from 'axios'
import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

export const useArticleStore = defineStore('article', () => {
  const articles = ref([])
  const router = useRouter()
  const token = ref(null)
  const isLogin = computed(() => {
    if (token.value === null) {
      return false
    } else {
      return true
    }
  })

  const getArticles = function () {
    axios({
      method: 'get',
      url: 'http://127.0.0.1:8000/api/v1/articles/'
    })
    .then(res => articles.value = res.data)
  }

  const createArticle = function ({ title, content}) {
    axios({
      method: 'post',
      url: 'http://127.0.0.1:8000/api/v1/articles/',
      data: {
        title,
        content
      }
    })
    .then(res => console.log(res))
  }

  const signup = function(payload) {
    const { username, password1, password2 } = payload
    axios({
      method: 'post',
      url: 'http://127.0.0.1:8000/accounts/signup/',
      data: { username, password1, password2 }
    })
      .then((response) => {
        console.log('회원가입 성공')
        router.push({ name : 'Home' })
      })
      .catch((error) => {
        console.log(error)
      })
  }

  const signin = function(payload) {
    const { username, password } = payload
    axios({
      method: 'post',
      url: 'http://127.0.0.1:8000/accounts/login/',
      data: { username, password }
    })
      .then((response) => {
        console.log('로그인 성공')
        console.log(response)
        token.value = response.data.key
        router.push({ name : 'home' })
      })
      .catch((error) => {
        console.log(error)
      })
  }
  return { articles, getArticles, createArticle, signup, signin, isLogin, token }
})
