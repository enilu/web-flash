import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/user/list',
    method: 'get',
    params
  })
}

export function saveUser(params) {
  return request({
    url: '/user',
    method: 'post',
    data: params
  })
}

export function remove(userId) {
  return request({
    url: '/user',
    method: 'delete',
    params: {
      userId
    }
  })
}

export function setRole(params) {
  return request({
    url: '/user/setRole',
    method: 'post',
    params
  })
}

export function changeStatus(userId) {
  return request({
    url: '/user/changeStatus',
    method: 'get',
    params:{
      userId
    }
  })
}


export function resetPassword(userId) {
  return request({
    url: '/user/resetPassword',
    method: 'post',
    params: {
      userId
    }
  })
}
