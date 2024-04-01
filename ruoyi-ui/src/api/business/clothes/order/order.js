import request from '@/utils/request'

// 查询洗衣订单列表
export function listOrder(query) {
    return request({
        url: '/business/order/list',
        method: 'get',
        params: query
    })
}

// 查询洗衣订单详细
export function getOrder(id) {
    return request({
        url: '/business/order/' + id,
        method: 'get'
    })
}

// 新增洗衣订单
export function addOrder(data) {
    return request({
        url: '/business/order',
        method: 'post',
        data: data
    })
}

// 修改洗衣订单
export function updateOrder(data) {
    return request({
        url: '/business/order',
        method: 'put',
        data: data
    })
}

// 删除洗衣订单
export function delOrder(id) {
    return request({
        url: '/business/order/' + id,
        method: 'delete'
    })
}

// 导出洗衣订单
export function exportOrder(query) {
    return request({
        url: '/business/order/export',
        method: 'get',
        params: query
    })
}
