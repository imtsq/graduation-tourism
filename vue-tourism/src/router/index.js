import Vue from 'vue'
import VueRouter from 'vue-router'

// 主页
import layoutindex from '@/views/layout/index.vue'
import HomeIndex from '@/views/layout/home/index.vue'
import Book from '@/views/layout/Book/index.vue'
import BookDetail from '@/views/layout/Book/BookDetail.vue'

// 登录注册
import Register from '@/views/login/User/register.vue'
import UserLogin from '@/views/login/User/login.vue'

// 管理员
import adminLogin from '@/views/login/admin/adminLogin.vue'
import BackPassword from '@/views/login/User/BackPassword.vue'
// 后台
import serve from '@/views/serve/index.vue'
import Ticket from '@/views/serve/Tickets/index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: layoutindex,
    children: [
      { path: '', redirect: '/index' },
      { path: 'index', component: HomeIndex },
      { path: 'UserRegister', component: Register },
      { path: 'UserLogin', component: UserLogin },
      { path: 'Book', component: Book },
      { path: 'BookDetail/:id', component: BookDetail }, // 点单详情
      { path: 'BackPassword/:token', component: BackPassword },
      { path: 'adminLogin', component: adminLogin }
    ]
  },

  {
    path: '/serve',
    component: serve,
    children: [
      { path: 'Ticket', component: Ticket }
    ]
  }

]

const router = new VueRouter({
  routes
})

export default router
