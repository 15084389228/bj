package com.ch.web.controller.business.clothes.price;


import com.ch.business.clothes.price.domain.ClothesPrice;
import com.ch.business.clothes.price.service.ClothesPriceService;
import com.ch.common.core.controller.BaseController;
import com.ch.common.core.domain.AjaxResult;
import com.ch.common.core.page.TableDataInfo;
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

