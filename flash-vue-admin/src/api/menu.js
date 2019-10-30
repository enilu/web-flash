import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/menu/list',
    method: 'get',
    params
  })
}
