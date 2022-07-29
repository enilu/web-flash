import request from '@/utils/request'
export  default {
  getList: function (params) {
    return request({
      url: '/channel/list',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/channel',
      method: 'post',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/channel',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
