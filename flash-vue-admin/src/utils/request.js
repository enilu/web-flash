import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 10000

})


// request拦截器
service.interceptors.request.use(
  config => {
    var token = getToken()
    if (token) {
      config.headers['Authorization'] = token // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    // config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
    return config
  },
  error => {
    // Do something with request error
    console.log('error',error) // for debug
    Promise.reject(error)
  }
)

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非20000是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code !== 20000) {

      // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
      }
      return Promise.reject(res.msg)
    } else {
      return response.data
    }
  },
  error => {
    //debug
    if(error.response && error.response.data.message) {
      Message({
        message: error.response.data.message,
        type: 'error',
        duration: 5 * 1000
      })
    }else{
      Message({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
