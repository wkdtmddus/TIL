import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'


export const useCounterStore = defineStore('counter', () => {
  const router = useRouter()
  const API_URL = 'http://127.0.0.1:8000'
  const token = ref(null)
  const editusername = ref(null)
  const editemail = ref(null)
  const editnickname = ref(null)
  const isLogin = computed(() => {
    if (token.value === null) {
      return false
    } else {
      return true
    }
  })

  const signUp = function (payload) {
    const username = payload.username
    const email = payload.email
    const password1 = payload.password1
    const password2 = payload.password2
    const nickname = payload.nickname
    axios({
      method: 'post',
      url: `${API_URL}/accounts/signup/`,
      data: {
        username, email, password1, password2, nickname
      }
    })
      .then(res => {
        console.log('회원가입이 완료되었습니다.')
        const password = password1
        login({ username, password })
      })
      .catch(err => {
        console.log(err)
      })
  }
  const login = function (payload) {
    const username = payload.username
    const password = payload.password
    editusername.value = payload.username
    axios({
      method: 'post',
      url: `${API_URL}/accounts/login/`,
      data: {
        username, password
      }
    })
      .then(res => {
        console.log('로그인이 완료되었습니다.')
        token.value = res.data.key
        router.push({name:'home'})
        console.log(res.data)
      })
      .catch(err => {
        console.log(err)
      })
  }
  const logout = function () {
    axios({
      method: 'post',
      url: `${API_URL}/accounts/logout/`,
      headers: {
        Authorization: `Token ${token.value}`
      }
    })
      .then(res => {
        console.log('로그아웃 완료되었습니다.')
        localStorage.removeItem('counter')
        window.location.href = router.resolve({ name: 'login' }).href
      })
      .catch(err => {
        console.log(err)
      })
  }
  const editprofile = function () {
    axios({
      method: 'get',
      url: `${API_URL}/accounts/user/`,
      headers: {
        Authorization: `Token ${token.value}`
      }
    })
      .then(res => {
        console.log(res.data)
        editemail.value = res.data.email
        editusername.value = res.data.username
        editnickname.value = res.data.nickname
        router.push({name: 'editprofile', params:{'username': res.data.username}})
      })
      .catch(err => {
        console.log(err)
      })
  }
  const joinin = function (id) {
    axios({
    method: 'post',
    url: `http://127.0.0.1:8000/fins/deposit_product_option/${id}/`,
    headers: {
          Authorization: `Token ${token.value}`
        }
    })
    .then(res => {
      console.log(res.data);
    })
    .catch(err => {
      console.log(err);
    });
  }
  const deleteaccount = function (password) {
    axios({
    method: 'post',
    url: `http://127.0.0.1:8000/accounts/delete/`,
    headers: {
          Authorization: `Token ${token.value}`
        },
    data: {
      'password1': password
    } 
    })
    .then(res => {
      console.log('탈퇴 성공')
      localStorage.removeItem('counter')
      window.location.href = router.resolve({ name: 'home' }).href
    })
    .catch(err => {
      console.log(err);
    });
  }

  const addpost = function (title, content) {axios({
    method: 'post',
    url: 'http://127.0.0.1:8000/api/v1/articles/',
    headers: {
      Authorization: `Token ${token.value}`
    },
    data: {
      'title': title,
      'content': content,
    }
  })
    .then(res => {
      console.log(res.data)
      router.push({name:'community'})
    })
    .catch(err => {
      console.log(err)
    })
  }

  const addcomment = function (comment, article_id) {
    axios({
      method: 'post',
      url: `http://127.0.0.1:8000/api/v1/articles/${article_id}/comments/`,
      headers: {
        Authorization: `Token ${token.value}`
      },
      data: {
        'content': comment,
      }
    })
      .then(res => {
        console.log(res.data)
        window.location.reload();
      })
      .catch(err => {
        console.log(err);
      })
  }
  const editPost = function (title, content, article_id) {
    axios({
      method: 'put',
      url: `http://127.0.0.1:8000/api/v1/articles/${article_id}/`,
      headers: {
        Authorization: `Token ${token.value}`
      },
      data: {
        'title': title,
        'content': content,
      }
    })
      .then(res => {
        console.log(res.data)
        router.push({name:'community'})
      })
      .catch(err => {
        console.log(err);
      })
  }
  const deletePost = function (article_id) {
    axios({
      method: 'delete',
      url: `http://127.0.0.1:8000/api/v1/articles/${article_id}/`,
      headers: {
        Authorization: `Token ${token.value}`
      },
    })
      .then(res => {
        console.log(res.data)
        router.push({name:'community'})
      })
      .catch(err => {
        console.log(err);
      })
  }
  const editComment = function (comment_id, content) {
    axios({
      method: 'put',
      url: `http://127.0.0.1:8000/api/v1/comments/${comment_id}/`,
      headers: {
        Authorization: `Token ${token.value}`
      },
      data : {
        'content': content
      }
    })
      .then(res => {
        console.log(res.data)
        location.reload(); 
      })
      .catch(err => {
        console.log(err);
      })
  }
  const deleteComment = function (comment_id) {
    axios({
      method: 'delete',
      url: `http://127.0.0.1:8000/api/v1/comments/${comment_id}`,
      headers: {
        Authorization: `Token ${token.value}`
      },
    })
      .then(res => {
        console.log(res.data)
        location.reload(); 
      })
      .catch(err => {
        console.log(err);
      })
  }




  return { API_URL, editPost, deletePost, signUp, login, logout, editprofile, joinin, deleteaccount, addpost, addcomment, editComment, deleteComment, token, isLogin, editusername, editemail, editnickname}
}, {persist: true})
