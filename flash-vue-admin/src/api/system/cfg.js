import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/cfg/list',
      method: 'get',
      params
    })
  },
  exportXls: function (params) {
    return request({
      url: '/cfg/export',
      method: 'get',
      params
    })
  },
  add: function (params) {
    return request({
      url: '/cfg',
      method: 'post',
      data: params
    })
  },
  update: function (params) {
    return request({
      url: '/cfg',
      method: 'put',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/cfg',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
