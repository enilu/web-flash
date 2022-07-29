import request from '@/utils/request'

export default {
  getList: function () {
    return request({
      url: '/menu/list',
      method: 'get'
    })
  },
  getMenuTree: function () {
    return request({
      url: '/menu/tree',
      method: 'get'
    })
  },
  listForRouter: function (params) {
    return request({
      url: '/menu/listForRouter',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/menu',
      method: 'post',
      data: params
    })
  },
  delMenu: function (id) {
    return request({
      url: '/menu',
      method: 'delete',
      params: {
        id: id
      }
    })
  },
  menuTreeListByRoleId: function (roleId) {
    return request({
      url: '/menu/menuTreeListByRoleId',
      method: 'get',
      params: {
        roleId: roleId
      }
    })
  }
}
