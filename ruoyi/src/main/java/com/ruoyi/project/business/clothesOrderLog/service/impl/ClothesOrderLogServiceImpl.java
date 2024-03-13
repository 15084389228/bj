package com.ruoyi.project.business.clothesOrderLog.service.impl;

import com.ruoyi.project.business.clothesOrderLog.domain.ClothesOrderLog;
import com.ruoyi.project.business.clothesOrderLog.mapper.ClothesOrderLogMapper;
import com.ruoyi.project.business.clothesOrderLog.service.ClothesOrderLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 洗衣订单表(ClothesOrderLog)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:29:19
 */
@Service("clothesOrderLogService")
public class ClothesOrderLogServiceImpl implements ClothesOrderLogService {
    @Resource
    private ClothesOrderLogMapper clothesOrderLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClothesOrderLog queryById(Integer id) {
        return this.clothesOrderLogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param clothesOrderLog 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    @Override
    public Page<ClothesOrderLog> queryByPage(ClothesOrderLog clothesOrderLog, PageRequest pageRequest) {
        long total = this.clothesOrderLogDao.count(clothesOrderLog);
        return new PageImpl<>(this.clothesOrderLogDao.queryAllByLimit(clothesOrderLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param clothesOrderLog 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesOrderLog insert(ClothesOrderLog clothesOrderLog) {
        this.clothesOrderLogDao.insert(clothesOrderLog);
        return clothesOrderLog;
    }

    /**
     * 修改数据
     *
     * @param clothesOrderLog 实例对象
     * @return 实例对象
     */
    @Override
    public ClothesOrderLog update(ClothesOrderLog clothesOrderLog) {
        this.clothesOrderLogDao.update(clothesOrderLog);
        return this.queryById(clothesOrderLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clothesOrderLogDao.deleteById(id) > 0;
    }
}
