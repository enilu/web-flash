import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/message/template/list',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/message/template',
      method: 'post',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/message/template',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
