package com.ruoyi.project.business.clothesOrder.service.impl;

import com.ruoyi.project.business.clothesOrder.domain.ClothesOrder;
import com.ruoyi.project.business.clothesOrder.mapper.ClothesOrderMapper;
import com.ruoyi.project.business.clothesOrder.service.ClothesOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 洗衣订单表(ClothesOrder)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:27:32
 */
@Service("clothesOrderService")
public class ClothesOrderServiceImpl implements ClothesOrderService {
    @Resource
    private ClothesOrderMapper clothesOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClothesOrder queryById(Integer id) {
        return this.clothesOrderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param clothesOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<ClothesOrder> queryByPage(ClothesOrder clothesOrder, PageRequest pageRequest) {
        long total = this.clothesOrderDao.count(clothesOrder);
        return new PageImpl<>(this.clothesOrderDao.queryAllByLimit(clothesOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param clothesOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesOrder insert(ClothesOrder clothesOrder) {
        this.clothesOrderDao.insert(clothesOrder);
        return clothesOrder;
    }

    /**
     * 修改数据
     *
     * @param clothesOrder 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesOrder update(ClothesOrder clothesOrder) {
        this.clothesOrderDao.update(clothesOrder);
        return this.queryById(clothesOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clothesOrderDao.deleteById(id) > 0;
    }
}
