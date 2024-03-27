package com.ruoyi.project.business.menber.type.mapper;

import com.ruoyi.project.business.menber.type.domain.MemberType;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 会员表(MemberType)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:57:22
 */
@Mapper
public interface MemberTypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MemberType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param memberType 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<MemberType> queryAllByLimit(MemberType memberType, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param memberType 查询条件
     * @return 总行数
     */
    long count(MemberType memberType);

    /**
     * 新增数据
     *
     * @param memberType 实例对象
     * @return 影响行数
     */
    int insert(MemberType memberType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MemberType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MemberType> entities);

    /**
     * 修改数据
     *
     * @param memberType 实例对象
     * @return 影响行数
     */
    int update(MemberType memberType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteByIds(Long[] ids);

    List<MemberType> list(MemberType buMemberType);
}

