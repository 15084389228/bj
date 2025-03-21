package com.ch.business.member.info.service;


import com.ch.business.member.info.domain.MemberInfo;

import java.math.BigDecimal;
import java.util.List;


/**
 * 会员Service接口
 *
 * @author ruoyi
 * @date 2024-03-27
 */
public interface IBuMemberInfoService {
    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    public MemberInfo selectBuMemberInfoById(Long id);

    /**
     * 查询会员列表
     *
     * @param memberInfo 会员
     * @return 会员集合
     */
    public List<MemberInfo> selectBuMemberInfoList(MemberInfo memberInfo);

    /**
     * 新增会员
     *
     * @param memberInfo 会员
     * @return 结果
     */
    public int insertBuMemberInfo(MemberInfo memberInfo);

    /**
     * 修改会员
     *
     * @param memberInfo 会员
     * @return 结果
     */
    public int updateBuMemberInfo(MemberInfo memberInfo);

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员ID
     * @return 结果
     */
    public int deleteBuMemberInfoByIds(Long[] ids);

    /**
     * 删除会员信息
     *
     * @param id 会员ID
     * @return 结果
     */
    public int deleteBuMemberInfoById(Long id);

    /**
     * 会员充值
     * @param memberInfo
     * @return
     */
    int recharge(MemberInfo memberInfo);

    int consume(Long memberId, BigDecimal consumeAmount);
}
