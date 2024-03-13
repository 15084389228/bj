package com.ruoyi.project.business.menberType.service.impl;

import com.ruoyi.project.business.menberType.domain.MemberType;
import com.ruoyi.project.business.menberType.mapper.MemberTypeMapper;
import com.ruoyi.project.business.menberType.service.MemberTypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 会员表(MemberType)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:57:23
 */
@Service("memberTypeService")
public class MemberTypeServiceImpl implements MemberTypeService {
    @Resource
    private MemberTypeMapper memberTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MemberType queryById(Integer id) {
        return this.memberTypeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param memberType  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MemberType> queryByPage(MemberType memberType, PageRequest pageRequest) {
        long total = this.memberTypeDao.count(memberType);
        return new PageImpl<>(this.memberTypeDao.queryAllByLimit(memberType, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param memberType 实例对象
     * @return 实例对象
     */
    @Override
    public MemberType insert(MemberType memberType) {
        this.memberTypeDao.insert(memberType);
        return memberType;
    }

    /**
     * 修改数据
     *
     * @param memberType 实例对象
     * @return 实例对象
     */
    @Override
    public MemberType update(MemberType memberType) {
        this.memberTypeDao.update(memberType);
        return this.queryById(memberType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.memberTypeDao.deleteById(id) > 0;
    }
}
