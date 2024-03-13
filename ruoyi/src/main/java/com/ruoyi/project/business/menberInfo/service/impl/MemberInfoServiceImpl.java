package com.ruoyi.project.business.menberInfo.service.impl;

import com.ruoyi.project.business.menberInfo.domain.MemberInfo;
import com.ruoyi.project.business.menberInfo.mapper.MemberInfoMapper;
import com.ruoyi.project.business.menberInfo.service.MemberInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 会员表(MemberInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:55:59
 */
@Service("memberInfoService")
public class MemberInfoServiceImpl implements MemberInfoService {
    @Resource
    private MemberInfoMapper memberInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MemberInfo queryById(Integer id) {
        return this.memberInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param memberInfo  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MemberInfo> queryByPage(MemberInfo memberInfo, PageRequest pageRequest) {
        long total = this.memberInfoDao.count(memberInfo);
        return new PageImpl<>(this.memberInfoDao.queryAllByLimit(memberInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param memberInfo 实例对象
     * @return 实例对象
     */
    @Override
    public MemberInfo insert(MemberInfo memberInfo) {
        this.memberInfoDao.insert(memberInfo);
        return memberInfo;
    }

    /**
     * 修改数据
     *
     * @param memberInfo 实例对象
     * @return 实例对象
     */
    @Override
    public MemberInfo update(MemberInfo memberInfo) {
        this.memberInfoDao.update(memberInfo);
        return this.queryById(memberInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.memberInfoDao.deleteById(id) > 0;
    }
}
