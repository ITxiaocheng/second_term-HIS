import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/index',
    component: () => import('@/views/Index')
  },
  {
    path: '/login',
    name: 'login',
    meta: { title: "怡成医疗管理系统" },
    component: () => import('@/views/Login')
  },
  {
    path: '/index',
    name: 'index',
    meta: { title: "湖南省第二人民医院" },
    component: () => import('@/views/Index')
  },
  {
    path: '/reg',
    name: 'reg',
    meta: { title: "湖南省第二人民医院" },
    component: () => import('@/views/Reg')
  },
  {
    path: '/home',
    name: 'home',
    // redirect: '/aa',
    meta: {
      isAtuth: true,
      title: "怡成医疗管理系统"
    },
    component: () => import('@/views/home/Home'),
    children: [
      { path: '/aa', name: 'aa', component: () => import('@/views/home/rightnav/Aa') },
      { path: '/addReg', name: 'addReg', component: () => import('@/views/home/rightnav/sublevelTwo/AddReg') },
      { path: '/bb', name: 'bb', component: () => import('@/views/home/rightnav/Bb') },
      { path: '/cc', name: 'cc', component: () => import('@/views/home/rightnav/Cc') },
      { path: '/dd', name: 'dd', component: () => import('@/views/home/rightnav/Dd') },
      { path: '/ee', name: 'ee', component: () => import('@/views/home/rightnav/Ee') },
      { path: '/ff', name: 'ff', component: () => import('@/views/home/rightnav/Ff') },
      { path: '/wei1', name: 'wei1', component: () => import('@/views/home/rightnav/Wei1') },
      { path: '/qq', name: 'qq', component: () => import('@/views/home/rightnav/Qq') },
      { path: '/addQq', name: 'addQq', component: () => import('@/views/home/rightnav/sublevelTwo/AddQq') },
      { path: '/addDoctor', name: 'addDoctor', component: () => import('@/views/home/rightnav/sublevelTwo/addDoctor') },
      { path: '/bingLiDetail', name: 'bingLiDetail', component: () => import('@/views/home/rightnav/sublevelTwo/BingLiDetail') },
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

//路由导航导卫(拦截器)
router.beforeEach((to, from, next) => {    // to 去的位置 from 来自来里  next 去到那里
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.meta.isAtuth) {    //to.meta.isAtuth 判断是够需要鉴权
    if (sessionStorage.getItem('username')) {
      next()  // 放行
    } else {
      alert('请先登录。无权限查看 ')
      next("/login")  // 放行
    }

  } else {
    next()
  }
})

export default router
