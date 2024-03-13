package com.ruoyi.project.business.clothesOrder.controller;

import com.ruoyi.project.business.clothesOrder.domain.ClothesOrder;
import com.ruoyi.project.business.clothesOrder.service.ClothesOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 洗衣订单表(ClothesOrder)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:27:31
 */
@RestController
@RequestMapping("clothesOrder")
public class ClothesOrderController {
    /**
     * 服务对象
     */
    @Resource
    private ClothesOrderService clothesOrderService;

    /**
     * 分页查询
     *
     * @param clothesOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ClothesOrder>> queryByPage(ClothesOrder clothesOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.clothesOrderService.queryByPage(clothesOrder, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ClothesOrder> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.clothesOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param clothesOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ClothesOrder> add(ClothesOrder clothesOrder) {
        return ResponseEntity.ok(this.clothesOrderService.insert(clothesOrder));
    }

    /**
     * 编辑数据
     *
     * @param clothesOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ClothesOrder> edit(ClothesOrder clothesOrder) {
        return ResponseEntity.ok(this.clothesOrderService.update(clothesOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.clothesOrderService.deleteById(id));
    }

}

