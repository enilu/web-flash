import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/workflow/request/list',
      method: 'get',
      params
    })
  },
  getTasks: function (params) {
    return request({
      url: '/workflow/request/tasks',
      method: 'get',
      params
    })
  },
  completeTask: function (params) {
    return request({
      url: '/workflow/request/completeTask',
      method: 'post',
      params
    })
  },
  add: function (params) {
    return request({
      url: '/workflow/request',
      method: 'post',
      params
    })
  },
  update: function (params) {
    return request({
      url: '/workflow/request',
      method: 'PUT',
      params
    })
  },
  remove: function (id) {
    return request({
      url: '/workflow/request',
      method: 'delete',
      params: {
        id: id
      }
    })
  }
}
