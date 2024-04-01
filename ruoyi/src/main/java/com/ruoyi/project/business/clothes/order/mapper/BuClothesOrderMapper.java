package com.ruoyi.project.business.clothes.order.mapper;

import java.util.List;

import com.ruoyi.project.business.clothes.order.domain.ClothesOrder;
import com.ruoyi.project.business.clothes.price.domain.ClothesPrice;
import io.lettuce.core.dynamic.annotation.Param;


/**
 * 洗衣订单Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-30
 */
public interface BuClothesOrderMapper {
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
     * 删除洗衣订单
     *
     * @param id 洗衣订单ID
     * @return 结果
     */
    public int deleteBuClothesOrderById(Long id);

    /**
     * 批量删除洗衣订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuClothesOrderByIds(Long[] ids);

    int insertDetail(@Param("list") List<ClothesPrice> clothesPriceList,@Param("orderId") Long orderId);
}
