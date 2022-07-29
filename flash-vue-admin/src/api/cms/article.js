import request from '@/utils/request'
export  default {
  getList: function (params) {
    return request({
      url: '/article/list',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/article',
      method: 'post',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/article',
      method: 'delete',
      params: {
        id: id
      }
    })
  },
  get: function (id) {
    return request({
      url: '/article',
      method: 'get',
      params: {
        id: id
      }
    })
  }
}
