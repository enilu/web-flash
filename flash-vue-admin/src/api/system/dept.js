import request from '@/utils/request'

export default {
  tree: function tree() {
    return request({
      url: '/dept/tree',
      method: 'get',
    })
  },
  list: function () {
    return request({
      url: '/dept/list',
      method: 'get',
    })
  },
  save: function (params) {
    return request({
      url: '/dept',
      method: 'post',
      data: params
    })
  },
  del: function (id) {
    return request({
      url: '/dept',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
