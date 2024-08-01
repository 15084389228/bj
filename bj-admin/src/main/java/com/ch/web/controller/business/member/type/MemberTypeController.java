package com.ch.web.controller.business.member.type;


import com.ch.business.member.type.domain.MemberType;
import com.ch.business.member.type.service.MemberTypeService;
import com.ch.common.annotation.Log;
import com.ch.common.core.controller.BaseController;
import com.ch.common.core.domain.AjaxResult;
import com.ch.common.core.page.TableDataInfo;
import com.ch.common.enums.BusinessType;
import com.ch.common.utils.poi.ExcelUtil;
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
@RequestMapping("/business/member/type")
public class MemberTypeController extends BaseController {
    @Autowired
    private MemberTypeService buMemberTypeService;

    /**
     * 查询会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberType buMemberType) {
        startPage();
        List<MemberType> list = buMemberTypeService.selectBuMemberTypeList(buMemberType);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MemberType buMemberType) {
        List<MemberType> list = buMemberTypeService.selectBuMemberTypeList(buMemberType);
        ExcelUtil<MemberType> util = new ExcelUtil<MemberType>(MemberType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(buMemberTypeService.queryById(id));
    }

    /**
     * 新增会员
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberType buMemberType) {
        return toAjax(buMemberTypeService.insert(buMemberType));
    }

    /**
     * 修改会员
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberType buMemberType) {
        return toAjax(buMemberTypeService.update(buMemberType));
    }

    /**
     * 删除会员
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(buMemberTypeService.deleteByIds(ids));
    }

    @GetMapping("/select")
    public AjaxResult getSelect() {
        return AjaxResult.success(buMemberTypeService.selectBuMemberTypeList(new MemberType()));
    }
}
