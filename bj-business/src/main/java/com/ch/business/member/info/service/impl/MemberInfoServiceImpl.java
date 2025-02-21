package com.ch.business.member.info.service.impl;

import com.ch.business.member.info.domain.MemberInfo;
import com.ch.business.member.info.mapper.MemberInfoMapper;
import com.ch.business.member.info.service.IBuMemberInfoService;
import com.ch.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-27
 */
@Service
public class MemberInfoServiceImpl implements IBuMemberInfoService {
    @Autowired
    private MemberInfoMapper buMemberInfoMapper;

    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public MemberInfo selectBuMemberInfoById(Long id) {
        return buMemberInfoMapper.selectBuMemberInfoById(id);
    }

    /**
     * 查询会员列表
     *
     * @param memberInfo 会员
     * @return 会员
     */
    @Override
    public List<MemberInfo> selectBuMemberInfoList(MemberInfo memberInfo) {
        return buMemberInfoMapper.selectBuMemberInfoList(memberInfo);
    }

    /**
     * 新增会员
     *
     * @param memberInfo 会员
     * @return 结果
     */
    @Override
    public int insertBuMemberInfo(MemberInfo memberInfo) {
        memberInfo.setCreateTime(DateUtils.getNowDate());
        return buMemberInfoMapper.insertBuMemberInfo(memberInfo);
    }

    /**
     * 修改会员
     *
     * @param memberInfo 会员
     * @return 结果
     */
    @Override
    public int updateBuMemberInfo(MemberInfo memberInfo) {
        memberInfo.setUpdateTime(DateUtils.getNowDate());
        return buMemberInfoMapper.updateBuMemberInfo(memberInfo);
    }

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员ID
     * @return 结果
     */
    @Override
    public int deleteBuMemberInfoByIds(Long[] ids) {
        return buMemberInfoMapper.deleteBuMemberInfoByIds(ids);
    }

    /**
     * 删除会员信息
     *
     * @param id 会员ID
     * @return 结果
     */
    @Override
    public int deleteBuMemberInfoById(Long id) {
        return buMemberInfoMapper.deleteBuMemberInfoById(id);
    }

    /**
     * 会员充值
     * @param memberInfo
     * @return
     */
    @Override
    public int recharge(MemberInfo param) {
        List<MemberInfo> memberInfoList = buMemberInfoMapper.listByIds(param.getIds());
        memberInfoList.forEach(memberInfo -> {
            memberInfo.recharge(param.getMemberPrice());
        });
        return buMemberInfoMapper.updateMemberPriceBatch(memberInfoList);
    }

    @Override
    public int consume(Long memberId, BigDecimal consumeAmount) {
        MemberInfo memberInfo = this.selectBuMemberInfoById(memberId);
        memberInfo.consume(consumeAmount);
        return buMemberInfoMapper.updateMemberPrice(memberInfo);
    }
}
