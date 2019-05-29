import axios from 'axios'
import qs from 'qs'

import * as _ from '../util/tool'
import router from '../router/router'
import store from '../store/store'
import * as types from '../store/types'

// const imgBaseUrl='http://htwx.xinshucredit.com/api'
const imgBaseUrl = env.IMG_URL

// axios 配置
axios.defaults.timeout = 60000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.defaults.baseURL = ''

// POST传参序列化
axios.interceptors.request.use((config) => {
  if (config.method === 'post') {
    config.data = qs.stringify(config.data)
  }

  if (store.state.token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
    config.headers.Authorization = `${store.state.token}`
  }
  if (store.state.appKey) {
    config.headers.appKey = `${store.state.appKey}`
  }
  return config
}, (error) => {
  _.toast('错误的传参', 'fail')
  return Promise.reject(error)
})

// 返回状态判断
axios.interceptors.response.use((res) => {
  if (res.headers['content-type'] === 'images/jpeg') {
    return res
  }
  return res
}, (error) => {
  if (error.response) {
    switch (error.response.status) {
      case 401:
        // 返回 401 清除token信息并跳转到登录页面
        store.commit(types.LOGOUT)
        router.replace({
          path: 'login',
          query: {redirect: router.currentRoute.fullPath}
        })
        break
      case 500:
        router.replace({
          path: 'error',
          query: {redirect: router.currentRoute.fullPath}
        })
        break
    }
  }
  _.toast('网络异常', 'fail')
  return Promise.reject(error)
})

export function post (url, params) {
  return new Promise((resolve, reject) => {
    axios.post(url, params)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

export function get (url, params) {
  return new Promise((resolve, reject) => {
    axios.get(url, params)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

export default {

  imgBaseUrl,
  getOffcialSite () {
    return get('/api/offcialSite')
  },
  getProductList () {
    return get('/api/offcialSite/product')
  },

  getSolutionList () {
    return get('/api/offcialSite/solution')
  },

  getCaseList () {
    return get('/api/offcialSite/case')
  },
  getNewsList () {
    return get('/api/offcialSite/news')
  },
  getArticle (id, type) {
    return get('/api/offcialSite/article?id=' + id + '&type=' + type)
  },
  saveContact (params) {
    return post('/api/offcialSite/contact', params)
  }
}
