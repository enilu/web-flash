import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/account/login',
    method: 'post',
    params: {
      "username": username,
      "password": password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/account/info',
    method: 'get'
  })
}

export function logout() {
  console.log('logout')
  return request({
    url: '/account/logout',
    method: 'post'
  })
}

export function updatePwd(params) {
  return request({
    url: '/account/updatePwd',
    method: 'post',
    params
  })
}
