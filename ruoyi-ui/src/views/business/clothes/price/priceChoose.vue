<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="类型" prop="clothesType">
        <el-select v-model="queryParams.clothesType" placeholder="请选择" clearable>
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="clothesName">
        <el-input
          v-model="queryParams.clothesName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="clothesPriceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="类型"
        align="center"
        prop="clothesType"
        :formatter="typeFormat"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="名称"
        align="center"
        prop="clothesName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="价格"
        align="center"
        prop="clothesPrice"
        :show-overflow-tooltip="true"
      />

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import {
  addClothesPrice,
  delClothesPrice,
  getClothesPrice,
  list,
  updateClothesPrice
} from "../../../../api/business/clothes/price";
import Editor from '@/components/Editor/index.vue';
import {parseTime} from "../../../../utils/ruoyi";

export default {
  name: "price-choose",
  components: {
    Editor
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      chooseItem:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 公告表格数据
      clothesPriceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型数据字典
      statusOptions: [],
      // 状态数据字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clothesType: undefined,
        clothesName: undefined,
        status: 0
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clothesType: [
          { required: true, message: "衣服类型不能为空", trigger: "blur" }
        ],
        clothesName: [
          { required: true, message: "衣服名称不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_notice_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("bu_clothes_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    parseTime,
    /** 查询公告列表 */
    getList() {
      this.loading = true;
      list(this.queryParams).then(response => {
        this.clothesPriceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 公告状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 公告状态字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.clothesType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        clothesType: undefined,
        clothesName: undefined,
        clothesPrice: undefined,
        clothesGroup: undefined,
        orderNum: undefined,
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");

      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.chooseItem = selection
      console.log(this.chooseItem)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getClothesPrice(row.id ).then(response => {
        this.form = response.data;
        this.form.clothesType=this.form.clothesType+'';
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id) {
            updateClothesPrice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addClothesPrice(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delClothesPrice(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>
