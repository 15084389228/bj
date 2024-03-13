package com.ruoyi.project.business.clothesPrice.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * 衣服价格表(ClothesPrice)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
public class ClothesPrice implements Serializable {
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClothesType() {
        return clothesType;
    }

    public void setClothesType(Integer clothesType) {
        this.clothesType = clothesType;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public Double getClothesPrice() {
        return clothesPrice;
    }

    public void setClothesPrice(Double clothesPrice) {
        this.clothesPrice = clothesPrice;
    }

    public Integer getClothesGroup() {
        return clothesGroup;
    }

    public void setClothesGroup(Integer clothesGroup) {
        this.clothesGroup = clothesGroup;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

