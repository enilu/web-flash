import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/message/list',
      method: 'get',
      params
    })
  },
  clear: function () {
    return request({
      url: '/message',
      method: 'delete'
    })
  }

}
