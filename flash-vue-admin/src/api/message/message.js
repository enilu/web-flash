import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/message/list',
        method: 'get',
        params
    })
}


export function save(params) {
    return request({
        url: '/message',
        method: 'post',
        params
    })
}

export function remove(id) {
    return request({
        url: '/message',
        method: 'delete',
        params: {
            id: id
        }
    })
}
