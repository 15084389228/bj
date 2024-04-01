package com.ruoyi.project.business.clothes.order.controller;

import java.util.List;

import com.ruoyi.project.business.clothes.order.domain.ClothesOrder;
import com.ruoyi.project.business.clothes.order.service.IBuClothesOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 洗衣订单Controller
 *
 * @author ruoyi
 * @date 2024-03-30
 */
@RestController
@RequestMapping("/business/order")
public class BuClothesOrderController extends BaseController {
    @Autowired
    private IBuClothesOrderService buClothesOrderService;

/**
 * 查询洗衣订单列表
 */
@PreAuthorize("@ss.hasPermi('system:order:list')")
@GetMapping("/list")
        public TableDataInfo list(ClothesOrder buClothesOrder) {
        startPage();
        List<ClothesOrder> list = buClothesOrderService.selectBuClothesOrderList(buClothesOrder);
        return getDataTable(list);
    }
    
    /**
     * 导出洗衣订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "洗衣订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ClothesOrder buClothesOrder) {
        List<ClothesOrder> list = buClothesOrderService.selectBuClothesOrderList(buClothesOrder);
        ExcelUtil<ClothesOrder> util = new ExcelUtil<ClothesOrder>(ClothesOrder. class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取洗衣订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(buClothesOrderService.selectBuClothesOrderById(id));
    }

    /**
     * 新增洗衣订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "洗衣订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClothesOrder buClothesOrder) {
        return toAjax(buClothesOrderService.insertBuClothesOrder(buClothesOrder));
    }

    /**
     * 修改洗衣订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "洗衣订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClothesOrder buClothesOrder) {
        return toAjax(buClothesOrderService.updateBuClothesOrder(buClothesOrder));
    }

    /**
     * 删除洗衣订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "洗衣订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(buClothesOrderService.deleteBuClothesOrderByIds(ids));
    }
}
