package com.ruoyi.project.business.menber.info.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.menber.info.domain.MemberInfo;
import com.ruoyi.project.business.menber.info.service.IBuMemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员Controller
 *
 * @author ruoyi
 * @date 2024-03-27
 */
@RestController
@RequestMapping("/business/member/info")
public class MemberInfoController extends BaseController {
    @Autowired
    private IBuMemberInfoService buMemberInfoService;

    /**
     * 查询会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberInfo memberInfo) {
        startPage();
        List<MemberInfo> list = buMemberInfoService.selectBuMemberInfoList(memberInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MemberInfo memberInfo) {
        List<MemberInfo> list = buMemberInfoService.selectBuMemberInfoList(memberInfo);
        ExcelUtil<MemberInfo> util = new ExcelUtil<MemberInfo>(MemberInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(buMemberInfoService.selectBuMemberInfoById(id));
    }

    /**
     * 新增会员
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberInfo memberInfo) {
        return toAjax(buMemberInfoService.insertBuMemberInfo(memberInfo));
    }

    /**
     * 修改会员
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberInfo memberInfo) {
        return toAjax(buMemberInfoService.updateBuMemberInfo(memberInfo));
    }

    /**
     * 删除会员
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(buMemberInfoService.deleteBuMemberInfoByIds(ids));
    }
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PutMapping("/recharge")
    public AjaxResult recharge(@RequestBody MemberInfo memberInfo) {
        return toAjax(buMemberInfoService.recharge(memberInfo));
    }
}
