package com.ruoyi.project.business.menber.type.service;

import com.ruoyi.project.business.menber.type.domain.MemberType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 会员表(MemberType)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:57:23
 */
public interface MemberTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MemberType queryById(Integer id);

    /**
     * 分页查询
     *
     * @param memberType  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MemberType> queryByPage(MemberType memberType, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param memberType 实例对象
     * @return 实例对象
     */
    int insert(MemberType memberType);

    /**
     * 修改数据
     *
     * @param memberType 实例对象
     * @return 实例对象
     */
    int update(MemberType memberType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int deleteByIds(Long[] ids);

    List<MemberType> selectBuMemberTypeList(MemberType buMemberType);
}
