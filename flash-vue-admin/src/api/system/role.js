import request from '@/utils/request'

export default {
  getList: function (params) {
    return request({
      url: '/role/list',
      method: 'get',
      params
    })
  },
  save: function (params) {
    return request({
      url: '/role',
      method: 'post',
      data: params
    })
  },
  remove: function (roleId) {
    return request({
      url: '/role',
      method: 'delete',
      params: {
        roleId: roleId
      }
    })
  },
  roleTreeListByIdUser: function (idUser) {
    return request({
      url: '/role/roleTreeListByIdUser',
      method: 'get',
      params: {
        idUser: idUser
      }
    })
  },
  savePermissons: function (params) {
    return request({
      url: '/role/savePermisson',
      method: 'post',
      params
    })
  }
}
