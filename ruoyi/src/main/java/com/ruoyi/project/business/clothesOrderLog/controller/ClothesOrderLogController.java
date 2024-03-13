package com.ruoyi.project.business.clothesOrderLog.controller;

import com.ruoyi.project.business.clothesOrderLog.domain.ClothesOrderLog;
import com.ruoyi.project.business.clothesOrderLog.service.ClothesOrderLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 洗衣订单表(ClothesOrderLog)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:29:18
 */
@RestController
@RequestMapping("clothesOrderLog")
public class ClothesOrderLogController {
    /**
     * 服务对象
     */
    @Resource
    private ClothesOrderLogService clothesOrderLogService;

    /**
     * 分页查询
     *
     * @param clothesOrderLog 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ClothesOrderLog>> queryByPage(ClothesOrderLog clothesOrderLog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.clothesOrderLogService.queryByPage(clothesOrderLog, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ClothesOrderLog> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.clothesOrderLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param clothesOrderLog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ClothesOrderLog> add(ClothesOrderLog clothesOrderLog) {
        return ResponseEntity.ok(this.clothesOrderLogService.insert(clothesOrderLog));
    }

    /**
     * 编辑数据
     *
     * @param clothesOrderLog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ClothesOrderLog> edit(ClothesOrderLog clothesOrderLog) {
        return ResponseEntity.ok(this.clothesOrderLogService.update(clothesOrderLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.clothesOrderLogService.deleteById(id));
    }

}

