package com.ruoyi.project.business.clothes.price.service.impl;

import com.ruoyi.project.business.clothes.price.domain.ClothesPrice;
import com.ruoyi.project.business.clothes.price.mapper.ClothesPriceMapper;
import com.ruoyi.project.business.clothes.price.service.ClothesPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 衣服价格表(ClothesPrice)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
@Service("clothesPriceService")
public class ClothesPriceServiceImpl implements ClothesPriceService {
    @Resource
    private ClothesPriceMapper clothesPriceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClothesPrice queryById(Integer id) {
        return this.clothesPriceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param clothesPrice 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public List<ClothesPrice> queryByPage(ClothesPrice clothesPrice) {
       return this.clothesPriceDao.list(clothesPrice);
    }

    /**
     * 新增数据
     *
     * @param clothesPrice 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesPrice insert(ClothesPrice clothesPrice) {
        this.clothesPriceDao.insert(clothesPrice);
        return clothesPrice;
    }

    /**
     * 修改数据
     *
     * @param clothesPrice 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesPrice update(ClothesPrice clothesPrice) {
        this.clothesPriceDao.update(clothesPrice);
        return this.queryById(clothesPrice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clothesPriceDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByIds(Integer[] ids) {
        return this.clothesPriceDao.deleteByIds(ids) > 0;
    }
}
