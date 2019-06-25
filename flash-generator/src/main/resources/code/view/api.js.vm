import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '${table.UriPrefix}/list',
        method: 'get',
        params
    })
}


export function save(params) {
    return request({
        url: '${table.UriPrefix}',
        method: 'post',
        params
    })
}

export function remove(id) {
    return request({
        url: '${table.UriPrefix}',
        method: 'delete',
        params: {
            id: id
        }
    })
}
