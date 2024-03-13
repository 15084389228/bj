package com.ruoyi.project.business.menberType.controller;

import com.ruoyi.project.business.menberType.domain.MemberType;
import com.ruoyi.project.business.menberType.service.MemberTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员表(MemberType)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:57:22
 */
@RestController
@RequestMapping("memberType")
public class MemberTypeController {
    /**
     * 服务对象
     */
    @Resource
    private MemberTypeService memberTypeService;

    /**
     * 分页查询
     *
     * @param memberType  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MemberType>> queryByPage(MemberType memberType, PageRequest pageRequest) {
        return ResponseEntity.ok(this.memberTypeService.queryByPage(memberType, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MemberType> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.memberTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param memberType 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MemberType> add(MemberType memberType) {
        return ResponseEntity.ok(this.memberTypeService.insert(memberType));
    }

    /**
     * 编辑数据
     *
     * @param memberType 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MemberType> edit(MemberType memberType) {
        return ResponseEntity.ok(this.memberTypeService.update(memberType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.memberTypeService.deleteById(id));
    }

}

