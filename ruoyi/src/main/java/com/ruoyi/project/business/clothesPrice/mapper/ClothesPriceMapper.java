package com.ruoyi.project.business.clothesPrice.mapper;

import com.ruoyi.project.business.clothesPrice.domain.ClothesPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 衣服价格表(ClothesPrice)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
public interface ClothesPriceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClothesPrice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param clothesPrice 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<ClothesPrice> queryAllByLimit(ClothesPrice clothesPrice, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param clothesPrice 查询条件
     * @return 总行数
     */
    long count(ClothesPrice clothesPrice);

    /**
     * 新增数据
     *
     * @param clothesPrice 实例对象
     * @return 影响行数
     */
    int insert(ClothesPrice clothesPrice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClothesPrice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ClothesPrice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClothesPrice> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ClothesPrice> entities);

    /**
     * 修改数据
     *
     * @param clothesPrice 实例对象
     * @return 影响行数
     */
    int update(ClothesPrice clothesPrice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

