package com.ruoyi.project.business.clothes.price.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 衣服价格表(ClothesPrice)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
@Data
public class ClothesPrice extends BaseEntity {
    private static final long serialVersionUID = 831209068721383210L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 衣服类型
     */
    private Integer clothesType;
    /**
     * 衣服名称
     */
    private String clothesName;
    /**
     * 衣服价格
     */
    private Double clothesPrice;
    /**
     * 衣服分组
     */
    private Integer clothesGroup;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;


}

