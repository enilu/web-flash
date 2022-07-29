import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/task/list',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/task',
      method: 'post',
      data: params
    })
  },
  remove: function (id) {
    return request({
      url: '/task',
      method: 'delete',
      params: {
        id: id
      }
    })
  },
  logList: function (params) {
    return request({
      url: '/task/logList',
      method: 'get',
      params
    })
  },
  enable: function (id) {
    return request({
      url: '/task/enable/',
      method: 'POST',
      params: {
        taskId: id
      }
    })
  },
  disable: function (id) {
    return request({
      url: '/task/disable/',
      method: 'POST',
      params: {
        taskId: id
      }
    })
  }

}
