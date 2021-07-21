import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/workflow/process/definition/list',
            method: 'get',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/workflow/process/definition',
            method: 'post',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/workflow/process/definition',
            method: 'PUT',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/workflow/process/definition',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
