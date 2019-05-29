import request from '@/utils/request'

export function tree() {
  return request({
    url: '/dept/tree',
    method: 'get',
  })
}

export function list() {
  return request({
    url: '/dept/list',
    method: 'get',
  })
}

export function save(params) {
  return request({
    url: '/dept',
    method: 'post',
    params: params
  })
}

export function del(id) {
  return request({
    url: '/dept',
    method: 'delete',
    params: {
      id: id
    }
  })
}
