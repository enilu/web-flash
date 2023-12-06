import request from '@/utils/request'

export default {

  getList: function (params) {
    return request({
      url: '/dict/list',
      method: 'get',
      params
    })
  },

  save: function (params) {
    return request({
      url: '/dict',
      method: 'post',
      data: params
    })
  },


  update: function (params) {
    return request({
      url: '/dict',
      method: 'put',
      data: params
    })
  },

  remove: function (id) {
    return request({
      url: '/dict',
      method: 'delete',
      params: {
        id: id
      }
    })
  },
  getDicts: function (dictName) {
    return request({
      url: '/dict/getDicts',
      method: 'get',
      params: {
        dictName: dictName
      }
    })
  }
}
