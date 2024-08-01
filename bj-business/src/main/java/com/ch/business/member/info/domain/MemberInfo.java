package com.ch.business.member.info.domain;


import com.ch.business.member.type.domain.MemberType;
import com.ch.common.annotation.Excel;
import com.ch.common.core.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员对象 bu_member_info
 *
 * @author ruoyi
 * @date 2024-03-27
 */
@Data
public class MemberInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 会员名称
     */
    @Excel(name = "会员名称")
    private String memberName;

    /**
     * 会员手机号
     */
    @Excel(name = "会员手机号")
    private String memberPhone;

    /**
     * 会员余额
     */
    @Excel(name = "会员余额")
    @Setter(AccessLevel.NONE)
    private BigDecimal memberPrice;

    /**
     * 会员卡id
     */
    @Excel(name = "会员卡id")
    private Long memberTypeId;

    /**
     * 会员折扣(专属折扣,覆盖会员卡折扣)
     */
    @Excel(name = "会员折扣(专属折扣,覆盖会员卡折扣)")
    private Long memberDiscount;

    /**
     * 会员状态
     */
    @Excel(name = "会员状态")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    private MemberType memberType;
    private List<Long> ids;

    public void recharge(BigDecimal rechargeAmount) {
        BigDecimal add = this.memberPrice.add(rechargeAmount);
        this.memberPrice = add;
    }

    public void consume(BigDecimal consumeAmount) {
        BigDecimal subtract = this.memberPrice.subtract(consumeAmount);
        this.memberPrice = subtract;

    }

}
