import request from '@/utils/request'

// 查询会员列表
export function listType(query) {
    return request({
        url: '/business/member/type/list',
        method: 'get',
        params: query
    })
}

// 查询会员详细
export function getType(id) {
    return request({
        url: '/business/member/type/' + id,
        method: 'get'
    })
}

// 新增会员
export function addType(data) {
    return request({
        url: '/business/member/type',
        method: 'post',
        data: data
    })
}

// 修改会员
export function updateType(data) {
    return request({
        url: '/business/member/type',
        method: 'put',
        data: data
    })
}

// 删除会员
export function delType(id) {
    return request({
        url: '/business/member/type/' + id,
        method: 'delete'
    })
}

// 导出会员
export function exportType(query) {
    return request({
        url: '/business/member/type/export',
        method: 'get',
        params: query
    })
}
