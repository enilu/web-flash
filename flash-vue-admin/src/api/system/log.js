import request from '@/utils/request'
export  default {
  getList: function (params) {
    return request({
      url: '/log/list',
      method: 'get',
      params
    })
  },
  queryByUser: function () {
    return request({
      url: '/log/queryByUser',
      method: 'get'
    })
  },
  clear: function () {
    return request({
      url: '/log',
      method: 'delete'
    })
  }
}
