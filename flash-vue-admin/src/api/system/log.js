import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/log/list',
    method: 'get',
    params
  })
}

export function clear() {
  return request({
    url: '/log',
    method: 'delete'
  })
}
