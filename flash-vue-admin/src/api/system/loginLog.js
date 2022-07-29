import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/loginLog/list',
      method: 'get',
      params
    })
  },
  clear: function () {
    return request({
      url: '/loginLog',
      method: 'delete'
    })
  }
}
