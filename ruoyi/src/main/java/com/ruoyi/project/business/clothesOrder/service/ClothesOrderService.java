package com.ruoyi.project.business.clothesOrder.service;

import com.ruoyi.project.business.clothesOrder.domain.ClothesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 洗衣订单表(ClothesOrder)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:27:32
 */
public interface ClothesOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClothesOrder queryById(Integer id);

    /**
     * 分页查询
     *
     * @param clothesOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<ClothesOrder> queryByPage(ClothesOrder clothesOrder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param clothesOrder 实例对象
     * @return 实例对象
     */
    ClothesOrder insert(ClothesOrder clothesOrder);

    /**
     * 修改数据
     *
     * @param clothesOrder 实例对象
     * @return 实例对象
     */
    ClothesOrder update(ClothesOrder clothesOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
