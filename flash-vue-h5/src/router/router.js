import Vue from 'vue'
import Router from 'vue-router'
import App from '../App'
import * as types from '../store/types'
import store from '../store/store'


const index = r => require.ensure([], () => r(require('../page/official_site/index.vue')), 'index')
const news = r => require.ensure([], () => r(require('../page/official_site/news.vue')), 'news')
const article = r => require.ensure([], () => r(require('../page/official_site/article.vue')), 'article')
const contact = r => require.ensure([], () => r(require('../page/official_site/contact.vue')), 'contact')
const more = r => require.ensure([], () => r(require('../page/official_site/more.vue')), 'more')
const about = r => require.ensure([], () => r(require('../page/official_site/about.vue')), 'about')
const cases = r => require.ensure([], () => r(require('../page/official_site/case.vue')), 'case')
const product = r => require.ensure([], () => r(require('../page/official_site/product.vue')), 'product')
const solution = r => require.ensure([], () => r(require('../page/official_site/solution.vue')), 'solution')

Vue.use(Router)
const routes = [
  {
    path: '/',
    component: App,
    children: [
      {
        path: '/product',
        component: product
      },
      {
        path: '/solution',
        component: solution
      }, {
        path: '/index',
        component: index
      }, {
        path: '/news',
        component: news
      }, {
        path: '/article',
        component: article
      }, {
        path: '/contact',
        component: contact
      }, {
        path: '/more',
        component: more
      },
      {
        path: '/about',
        component: about
      },
      {
        path: '/case',
        component: cases
      }
    ]
  }
]

if (window.localStorage.getItem('token')) {
  var data = {
    token: localStorage.getItem('token'),
    account: localStorage.getItem('account')
  }
  store.commit(types.LOGIN, data)
}

const router = new Router({
  routes
})

router.afterEach((to, from) => {
  reloadInterceptor(to, from)
})
const reloadInterceptor = (to, from) => {
  if ((from.path != '/collectProtocol' && from.path != '/prepareBindCard') && to.path == '/bankCard') {
    let isRefresh = sessionStorage.getItem('isRefresh')
    if (isRefresh === '0') {
      sessionStorage.setItem('isRefresh', null)
      window.location.reload()
    } else {
      sessionStorage.setItem('isRefresh', '0')
    }
  }
}
router.beforeEach((to, from, next) => {
  if (to.matched.some(r => r.meta.requireAuth)) {
    if (store.state.token) {
      next()
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})

export default router
