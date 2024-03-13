package com.ruoyi.project.business.menberInfo.controller;

import com.ruoyi.project.business.menberInfo.domain.MemberInfo;
import com.ruoyi.project.business.menberInfo.service.MemberInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员表(MemberInfo)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:55:58
 */
@RestController
@RequestMapping("memberInfo")
public class MemberInfoController {
    /**
     * 服务对象
     */
    @Resource
    private MemberInfoService memberInfoService;

    /**
     * 分页查询
     *
     * @param memberInfo  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MemberInfo>> queryByPage(MemberInfo memberInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.memberInfoService.queryByPage(memberInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MemberInfo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.memberInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param memberInfo 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MemberInfo> add(MemberInfo memberInfo) {
        return ResponseEntity.ok(this.memberInfoService.insert(memberInfo));
    }

    /**
     * 编辑数据
     *
     * @param memberInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MemberInfo> edit(MemberInfo memberInfo) {
        return ResponseEntity.ok(this.memberInfoService.update(memberInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.memberInfoService.deleteById(id));
    }

}

