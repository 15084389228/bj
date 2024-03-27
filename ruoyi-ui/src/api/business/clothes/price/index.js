import request from '@/utils/request'


// 查询公告列表
export function list(query) {
  return request({
    url: '/business/clothesPrice/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getClothesPrice(id) {
  return request({
    url: '/business/clothesPrice/' + id,
    method: 'get'
  })
}

// 新增公告
export function addClothesPrice(data) {
  return request({
    url: 'business/clothesPrice',
    method: 'post',
    data:data
  })
}

// 修改公告
export function updateClothesPrice(data) {
  return request({
    url: '/business/clothesPrice',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delClothesPrice(ids) {
  return request({
    url: '/business/clothesPrice/' + ids,
    method: 'delete'
  })
}
