import request from '@/utils/request'

// 查询会员列表
export function listInfo(query) {
    return request({
        url: '/business/member/info/list',
        method: 'get',
        params: query
    })
}

// 查询会员详细
export function getInfo(id) {
    return request({
        url: '/business/member/info/' + id,
        method: 'get'
    })
}

// 新增会员
export function addInfo(data) {
    return request({
        url: '/business/member/info',
        method: 'post',
        data: data
    })
}

// 修改会员
export function updateInfo(data) {
    return request({
        url: '/business/member/info',
        method: 'put',
        data: data
    })
}

// 删除会员
export function delInfo(id) {
    return request({
        url: '/business/member/info/' + id,
        method: 'delete'
    })
}

// 导出会员
export function exportInfo(query) {
    return request({
        url: '/business/member/info/export',
        method: 'get',
        params: query
    })
}
