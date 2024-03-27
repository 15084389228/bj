package com.ruoyi.project.business.clothesPrice.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.clothesPrice.domain.ClothesPrice;
import com.ruoyi.project.business.clothesPrice.service.ClothesPriceService;
import com.ruoyi.project.system.domain.SysConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 衣服价格表(ClothesPrice)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:55:19
 */
@RestController
@RequestMapping("business/clothesPrice")
public class ClothesPriceController extends BaseController {
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
    @GetMapping("/list")
    public TableDataInfo queryByPage(ClothesPrice clothesPrice) {
        startPage();
        List<ClothesPrice> list = clothesPriceService.queryByPage(clothesPrice);
        return getDataTable(list);

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public AjaxResult queryById(@PathVariable("id") Integer id) {
        return AjaxResult.success(this.clothesPriceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param clothesPrice 实体
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult add(@RequestBody ClothesPrice clothesPrice) {
        return AjaxResult.success(this.clothesPriceService.insert(clothesPrice));
    }

    /**
     * 编辑数据
     *
     * @param clothesPrice 实体
     * @return 编辑结果
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ClothesPrice clothesPrice) {
        return AjaxResult.success(this.clothesPriceService.update(clothesPrice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{ids}")
    public AjaxResult deleteById(@PathVariable("ids") Integer[] ids) {
        return AjaxResult.success(this.clothesPriceService.deleteByIds(ids));
    }

}

