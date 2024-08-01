package com.ch.business.member.type.service.impl;


import com.ch.business.member.type.domain.MemberType;
import com.ch.business.member.type.mapper.MemberTypeMapper;
import com.ch.business.member.type.service.MemberTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public int insert(MemberType memberType) {
        return this.memberTypeDao.insert(memberType);
    }

    /**
     * 修改数据
     *
     * @param memberType 实例对象
     * @return 实例对象
     */
    @Override
    public int update(MemberType memberType) {

        return this.memberTypeDao.update(memberType);
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

    @Override
    public int deleteByIds(Long[] ids) {
        return this.memberTypeDao.deleteByIds(ids) ;
    }

    @Override
    public List<MemberType> selectBuMemberTypeList(MemberType buMemberType) {
        return this.memberTypeDao.list(buMemberType);
    }
}
