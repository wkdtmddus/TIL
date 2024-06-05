import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import CardView from '@/views/CardView.vue'
import LoginView from '@/views/LoginView.vue'
import SignupView from '@/views/SignupView.vue'
import ExrateView from '@/views/ExrateView.vue'
import InrateView from '@/views/InrateView.vue'
import Inrate2View from '@/views/Inrate2View.vue'
import MapsearchView from '@/views/MapsearchView.vue'
import ProfileView from '@/views/ProfileView.vue'
import EditProfileView from '@/views/EditProfileView.vue'
import CommunityView from '@/views/CommunityView.vue'
import CommunityDetailView from '@/views/CommunityDetailView.vue'
import CommunityEditView from '@/views/CommunityEditView.vue'
import CommunityAddPostView from '@/views/CommunityAddPostView.vue'
import InrateDetailView from '@/views/InrateDetailView.vue'
import Inrate2DetailView from '@/views/Inrate2DetailView.vue'

import { useCounterStore } from '@/stores/counter'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/card',
      name: 'card',
      component: CardView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignupView
    },
    {
      path: '/exrate',
      name: 'exrate',
      component: ExrateView
    },
    {
      path: '/inrate',
      name: 'inrate',
      component: InrateView
    },
    {
      path: '/inrate2',
      name: 'inrate2',
      component: Inrate2View
    },
    {
      path: '/inrate/:id',
      name: 'inratedetail',
      component: InrateDetailView
    },
    {
      path: '/inrate2/:id',
      name: 'inrate2detail',
      component: Inrate2DetailView
    },
    {
      path: '/mapsearch',
      name: 'mapsearch',
      component: MapsearchView
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    },
    {
      path: '/profile/:username',
      name: 'editprofile',
      component: EditProfileView
    },
    {
      path: '/community',
      name: 'community',
      component: CommunityView
    },
    {
      path: '/community/:id',
      name: 'communitydetail',
      component: CommunityDetailView
    },
    {
      path: '/community/edit/:id',
      name: 'communityedit',
      component: CommunityEditView
    },
    {
      path: '/communityaddpost',
      name: 'communityaddpost',
      component: CommunityAddPostView
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

router.beforeEach((to, from) => {
  const store=useCounterStore()
  if ((to.name ==='profile' || to.name ==='editprofile' || to.name ==='communityaddpost' || to.name ==='communityedit') && (!store.isLogin)) {
    window.alert('로그인이 필요합니다.')
    return { name:'login'}
  }
  if ((to.name === 'signup' || to.name === 'login') && (store.isLogin)) {
    window.alert('이미 로그인 되어있습니다.')
    return { name: 'home'}
  }
  if ((to.name ==='editprofile') && (to.params.username != store.editusername)) {
    window.alert('잘못된 접근입니다.')
    return {name:'profile'}
  }
})

export default router
