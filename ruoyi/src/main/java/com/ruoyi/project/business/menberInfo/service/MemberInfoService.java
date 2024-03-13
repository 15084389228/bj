package com.ruoyi.project.business.menberInfo.service;

import com.ruoyi.project.business.menberInfo.domain.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 会员表(MemberInfo)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:55:59
 */
public interface MemberInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MemberInfo queryById(Integer id);

    /**
     * 分页查询
     *
     * @param memberInfo  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MemberInfo> queryByPage(MemberInfo memberInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param memberInfo 实例对象
     * @return 实例对象
     */
    MemberInfo insert(MemberInfo memberInfo);

    /**
     * 修改数据
     *
     * @param memberInfo 实例对象
     * @return 实例对象
     */
    MemberInfo update(MemberInfo memberInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
