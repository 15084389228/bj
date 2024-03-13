package com.ruoyi.project.business.clothesOrder.mapper;

import com.ruoyi.project.business.clothesOrder.domain.ClothesOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 洗衣订单表(ClothesOrder)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:27:32
 */
public interface ClothesOrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClothesOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param clothesOrder 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<ClothesOrder> queryAllByLimit(ClothesOrder clothesOrder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param clothesOrder 查询条件
     * @return 总行数
     */
    long count(ClothesOrder clothesOrder);

    /**
     * 新增数据
     *
     * @param clothesOrder 实例对象
     * @return 影响行数
     */
    int insert(ClothesOrder clothesOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClothesOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ClothesOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClothesOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ClothesOrder> entities);

    /**
     * 修改数据
     *
     * @param clothesOrder 实例对象
     * @return 影响行数
     */
    int update(ClothesOrder clothesOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

