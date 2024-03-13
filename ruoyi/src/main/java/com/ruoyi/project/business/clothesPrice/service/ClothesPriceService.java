package com.ruoyi.project.business.clothesPrice.service;

import com.ruoyi.project.business.clothesPrice.domain.ClothesPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 衣服价格表(ClothesPrice)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
public interface ClothesPriceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClothesPrice queryById(Integer id);

    /**
     * 分页查询
     *
     * @param clothesPrice 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<ClothesPrice> queryByPage(ClothesPrice clothesPrice, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param clothesPrice 实例对象
     * @return 实例对象
     */
    ClothesPrice insert(ClothesPrice clothesPrice);

    /**
     * 修改数据
     *
     * @param clothesPrice 实例对象
     * @return 实例对象
     */
    ClothesPrice update(ClothesPrice clothesPrice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
