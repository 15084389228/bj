package com.ruoyi.project.business.clothes.order.domain;

import com.ruoyi.project.business.clothes.price.domain.ClothesPrice;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 洗衣订单对象 bu_clothes_order
 *
 * @author ruoyi
 * @date 2024-03-30
 */
@Data
public class ClothesOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户信息主键
     */
    @Excel(name = "客户信息主键")
    private Long clientId;

    /**
     * 客户手机号
     */
    @Excel(name = "客户手机号")
    private String clientPhone;

    /**
     * 所洗衣服id
     */
    @Excel(name = "所洗衣服id")
    private String clientName;

    /**
     * 订单总价格
     */
    @Excel(name = "订单总价格")
    private Double orderPrice;

    /**
     * 所享折扣
     */
    @Excel(name = "所享折扣")
    private Long orderDiscount;

    /**
     * 实付价格
     */
    @Excel(name = "实付价格")
    private Double actualPrice;

    /**
     * 纸质订单号
     */
    @Excel(name = "纸质订单号")
    private String paperNum;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNum;

    /**
     * 订单状态(1:待处理  2:处理中  3:可取衣  4:已取)
     */
    @Excel(name = "订单状态(1:待处理  2:处理中  3:可取衣  4:已取)")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;


    private List<ClothesPrice> clothesPriceList;


}
