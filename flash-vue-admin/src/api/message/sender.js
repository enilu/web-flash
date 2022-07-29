import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/message/sender/list',
      method: 'get',
      params
    })
  },
  queryAll: function (params) {
    return request({
      url: '/message/sender/queryAll',
      method: 'get'
    })
  },
  save: function (params) {
    return request({
      url: '/message/sender',
      method: 'post',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/message/sender',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
