package com.ch.business.member.type.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员表(MemberType)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:57:22
 */
@Data
public class MemberType implements Serializable {
    private static final long serialVersionUID = -69693560705199230L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 会员卡名称
     */
    private String memberTypeName;
    /**
     * 会员类型
     */
    private String memberType;
    /**
     * 会员折扣
     */
    private BigDecimal memberDiscount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 会员卡状态
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;



}

