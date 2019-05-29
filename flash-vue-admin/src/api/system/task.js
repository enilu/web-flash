import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/task/list',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/task',
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: '/task',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function logList(params) {
  return request({
    url: '/task/logList',
    method: 'get',
    params
  })
}

export function enable(id) {
  return request({
    url: '/task/enable/',
    method: 'POST',
    params: {
      taskId: id
    }
  })
}

export function disable(id) {
  return request({
    url: '/task/disable/',
    method: 'POST',
    params: {
      taskId: id
    }
  })
}
