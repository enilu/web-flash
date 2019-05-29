import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/dict/list',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/dict',
    method: 'post',
    params
  })
}


export function update(params) {
  return request({
    url: '/dict',
    method: 'put',
    params
  })
}

export function remove(id) {
  return request({
    url: '/dict',
    method: 'delete',
    params: {
      id:id
    }
  })
}
