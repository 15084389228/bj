package com.ruoyi.project.business.clothes.order.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.business.clothes.order.domain.ClothesOrder;
import com.ruoyi.project.business.clothes.order.mapper.BuClothesOrderMapper;
import com.ruoyi.project.business.clothes.order.service.IBuClothesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 洗衣订单Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-30
 */
@Service
public class BuClothesOrderServiceImpl implements IBuClothesOrderService {
    @Autowired
    private BuClothesOrderMapper buClothesOrderMapper;

    /**
     * 查询洗衣订单
     *
     * @param id 洗衣订单ID
     * @return 洗衣订单
     */
    @Override
    public ClothesOrder selectBuClothesOrderById(Long id) {
        return buClothesOrderMapper.selectBuClothesOrderById(id);
    }

    /**
     * 查询洗衣订单列表
     *
     * @param buClothesOrder 洗衣订单
     * @return 洗衣订单
     */
    @Override
    public List<ClothesOrder> selectBuClothesOrderList(ClothesOrder buClothesOrder) {
        return buClothesOrderMapper.selectBuClothesOrderList(buClothesOrder);
    }

    /**
     * 新增洗衣订单
     *
     * @param buClothesOrder 洗衣订单
     * @return 结果
     */
    @Override
    public int insertBuClothesOrder(ClothesOrder buClothesOrder) {
        buClothesOrder.setCreateTime(DateUtils.getNowDate());
        int i = buClothesOrderMapper.insertBuClothesOrder(buClothesOrder);
        buClothesOrderMapper.insertDetail(buClothesOrder.getClothesPriceList(),buClothesOrder.getId());
        return i;
    }

    /**
     * 修改洗衣订单
     *
     * @param buClothesOrder 洗衣订单
     * @return 结果
     */
    @Override
    public int updateBuClothesOrder(ClothesOrder buClothesOrder) {
        buClothesOrder.setUpdateTime(DateUtils.getNowDate());
        return buClothesOrderMapper.updateBuClothesOrder(buClothesOrder);
    }

    /**
     * 批量删除洗衣订单
     *
     * @param ids 需要删除的洗衣订单ID
     * @return 结果
     */
    @Override
    public int deleteBuClothesOrderByIds(Long[] ids) {
        return buClothesOrderMapper.deleteBuClothesOrderByIds(ids);
    }

    /**
     * 删除洗衣订单信息
     *
     * @param id 洗衣订单ID
     * @return 结果
     */
    @Override
    public int deleteBuClothesOrderById(Long id) {
        return buClothesOrderMapper.deleteBuClothesOrderById(id);
    }
}
