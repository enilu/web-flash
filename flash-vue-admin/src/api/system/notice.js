import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/notice/list',
    method: 'get',
    params
  })
}
