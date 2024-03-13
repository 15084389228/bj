package com.ruoyi.project.business.clothesPrice.controller;

import com.ruoyi.project.business.clothesPrice.domain.ClothesPrice;
import com.ruoyi.project.business.clothesPrice.service.ClothesPriceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 衣服价格表(ClothesPrice)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
@RestController
@RequestMapping("clothesPrice")
public class ClothesPriceController {
    /**
     * 服务对象
     */
    @Resource
    private ClothesPriceService clothesPriceService;

    /**
     * 分页查询
     *
     * @param clothesPrice 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ClothesPrice>> queryByPage(ClothesPrice clothesPrice, PageRequest pageRequest) {
        return ResponseEntity.ok(this.clothesPriceService.queryByPage(clothesPrice, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ClothesPrice> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.clothesPriceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param clothesPrice 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ClothesPrice> add(ClothesPrice clothesPrice) {
        return ResponseEntity.ok(this.clothesPriceService.insert(clothesPrice));
    }

    /**
     * 编辑数据
     *
     * @param clothesPrice 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ClothesPrice> edit(ClothesPrice clothesPrice) {
        return ResponseEntity.ok(this.clothesPriceService.update(clothesPrice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.clothesPriceService.deleteById(id));
    }

}

