import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SignUpView from '@/views/SignUpView.vue'
import SignInView from '@/views/SignInView.vue'
import ArticleCreateView from '../views/ArticleCreateView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/create',
      name: 'create',
      component: ArticleCreateView
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUpView
    },
    {
      path: '/signin',
      name: 'signin',
      component: SignInView
    },
  ]
})

import { useArticleStore } from '@/stores/articles'

router.beforeEach((to, from) => {
  const store = useArticleStore()
  if ((to.name === 'signup' || to.name === 'signin') && (store.isLogin === true)) {
    window.alert('이미 로그인 했습니다.')
    return { name: 'home' }
  }
})

export default router
