package com.ruoyi.project.business.clothesOrderLog.service;

import com.ruoyi.project.business.clothesOrderLog.domain.ClothesOrderLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 洗衣订单表(ClothesOrderLog)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:29:19
 */
public interface ClothesOrderLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClothesOrderLog queryById(Integer id);

    /**
     * 分页查询
     *
     * @param clothesOrderLog 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<ClothesOrderLog> queryByPage(ClothesOrderLog clothesOrderLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param clothesOrderLog 实例对象
     * @return 实例对象
     */
    ClothesOrderLog insert(ClothesOrderLog clothesOrderLog);

    /**
     * 修改数据
     *
     * @param clothesOrderLog 实例对象
     * @return 实例对象
     */
    ClothesOrderLog update(ClothesOrderLog clothesOrderLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
