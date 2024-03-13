package com.ruoyi.project.business.menberInfo.mapper;

import com.ruoyi.project.business.menberInfo.domain.MemberInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 会员表(MemberInfo)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:55:58
 */
public interface MemberInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MemberInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param memberInfo 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<MemberInfo> queryAllByLimit(MemberInfo memberInfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param memberInfo 查询条件
     * @return 总行数
     */
    long count(MemberInfo memberInfo);

    /**
     * 新增数据
     *
     * @param memberInfo 实例对象
     * @return 影响行数
     */
    int insert(MemberInfo memberInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MemberInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MemberInfo> entities);

    /**
     * 修改数据
     *
     * @param memberInfo 实例对象
     * @return 影响行数
     */
    int update(MemberInfo memberInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

