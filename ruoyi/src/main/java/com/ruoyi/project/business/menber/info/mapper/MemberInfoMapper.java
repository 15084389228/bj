package com.ruoyi.project.business.menber.info.mapper;

import com.ruoyi.project.business.menber.info.domain.MemberInfo;
import com.ruoyi.project.business.menber.type.domain.MemberType;

import java.util.List;


/**
 * 会员Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-27
 */
public interface MemberInfoMapper {
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
     * 删除会员
     *
     * @param id 会员ID
     * @return 结果
     */
    public int deleteBuMemberInfoById(Long id);

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuMemberInfoByIds(Long[] ids);

    int updateMemberPrice(MemberInfo memberInfo);

    List<MemberInfo> listByIds(List<Long> ids);

    int updateMemberPriceBatch(List<MemberInfo> memberInfoList);

}
