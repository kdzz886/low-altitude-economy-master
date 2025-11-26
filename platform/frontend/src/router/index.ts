import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../store/user'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: () => import('../views/Home.vue') },
    { path: '/login', component: () => import('../views/Login.vue') },
    { path: '/register', component: () => import('../views/Register.vue') },
    { path: '/demands', component: () => import('../views/Demands.vue') },
    { 
      path: '/demands/publish',
      component: () => import('../views/DemandPublish.vue'),
      meta: { auth: true }
    },
    { path: '/demands/:id', component: () => import('../views/DemandDetail.vue') },
    { path: '/dashboard', component: () => import('../views/DashboardEcharts.vue') }
  ]
})

// 权限控制
router.beforeEach((to, from, next) => {
  const store = useUserStore()
  if (to.meta.auth && !store.token) {
    return next('/login')
  }
  next()
})

export default router
