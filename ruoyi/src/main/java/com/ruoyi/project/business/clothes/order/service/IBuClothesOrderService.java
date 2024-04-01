package com.ruoyi.project.business.clothes.order.service;

import com.ruoyi.project.business.clothes.order.domain.ClothesOrder;

import java.util.List;


/**
 * 洗衣订单Service接口
 *
 * @author ruoyi
 * @date 2024-03-30
 */
public interface IBuClothesOrderService {
    /**
     * 查询洗衣订单
     *
     * @param id 洗衣订单ID
     * @return 洗衣订单
     */
    public ClothesOrder selectBuClothesOrderById(Long id);

    /**
     * 查询洗衣订单列表
     *
     * @param buClothesOrder 洗衣订单
     * @return 洗衣订单集合
     */
    public List<ClothesOrder> selectBuClothesOrderList(ClothesOrder buClothesOrder);

    /**
     * 新增洗衣订单
     *
     * @param buClothesOrder 洗衣订单
     * @return 结果
     */
    public int insertBuClothesOrder(ClothesOrder buClothesOrder);

    /**
     * 修改洗衣订单
     *
     * @param buClothesOrder 洗衣订单
     * @return 结果
     */
    public int updateBuClothesOrder(ClothesOrder buClothesOrder);

    /**
     * 批量删除洗衣订单
     *
     * @param ids 需要删除的洗衣订单ID
     * @return 结果
     */
    public int deleteBuClothesOrderByIds(Long[] ids);

    /**
     * 删除洗衣订单信息
     *
     * @param id 洗衣订单ID
     * @return 结果
     */
    public int deleteBuClothesOrderById(Long id);
}
