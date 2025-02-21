<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="120px">
      <el-form-item label="客户名称" prop="clientId">
        <el-input
          v-model="queryParams.clientName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户手机号" prop="clientPhone">
        <el-input
          v-model="queryParams.clientPhone"
          placeholder="请输入客户手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所洗衣服" prop="clothesId">
        <el-input
          v-model="queryParams.clothesId"
          placeholder="请输入所洗衣服id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纸质订单号" prop="paperNum">
        <el-input
          v-model="queryParams.paperNum"
          placeholder="请输入纸质订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单号" prop="orderNum">
        <el-input
          v-model="queryParams.orderNum"
          placeholder="请输入订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态"
                   clearable
                   size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:order:add']"
        >新增订单
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:order:remove']"
        >删除
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['system:order:export']"-->
      <!--        >导出-->
      <!--        </el-button>-->
      <!--      </el-col>-->
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="客户姓名" align="center" prop="clientName"/>
      <el-table-column label="客户手机号" align="center" prop="clientPhone"/>
      <el-table-column label="订单总价格" align="center" prop="orderPrice"/>
      <el-table-column label="所享折扣" align="center" prop="orderDiscount"/>
      <el-table-column label="实付价格" align="center" prop="actualPrice"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="纸质订单号" align="center" prop="paperNum"/>
      <el-table-column label="订单号" align="center" prop="orderNum"/>
      <el-table-column label="订单状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


    <!-- 添加或修改洗衣订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="chooseOpen=true"
            v-hasPermi="['system:order:add']"
          >添加衣物
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:order:remove']"
          >删除
          </el-button>
        </el-col>
        <!--      <el-col :span="1.5">-->
        <!--        <el-button-->
        <!--          type="warning"-->
        <!--          icon="el-icon-download"-->
        <!--          size="mini"-->
        <!--          @click="handleExport"-->
        <!--          v-hasPermi="['system:order:export']"-->
        <!--        >导出-->
        <!--        </el-button>-->
        <!--      </el-col>-->
      </el-row>
      <el-table
        :data="tableData"
        border
        v-show="choseClothes"
        show-summary
        style="width: 100%">
        <el-table-column
          prop="clothesName"
          label="衣物名称">
        </el-table-column>
        <el-table-column
          prop="clothesPrice"
          sortable
          label="衣物价格">
        </el-table-column>
        <el-table-column
          align="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDeleteTableDate(scope.$index, scope.row)">Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form ref="form" :model="form" v-show="!choseClothes" :rules="rules" label-width="120px">
        <!--        <el-form-item label="客户信息主键" prop="clientId">-->
        <!--          <el-input v-model="form.clientId" placeholder="请输入客户信息主键"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="客户姓名" prop="clothesId">
          <el-input v-model="form.clothesName" placeholder="请输入客户姓名"/>
        </el-form-item>
        <el-form-item label="客户手机号" prop="clientPhone">
          <el-input v-model="form.clientPhone" placeholder="请输入客户手机号"/>
        </el-form-item>
        <el-form-item label="订单总价格" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入订单总价格"/>
        </el-form-item>
        <el-form-item label="所享折扣" prop="orderDiscount">
          <el-input v-model="form.orderDiscount" placeholder="请输入所享折扣"/>
        </el-form-item>
        <el-form-item label="实付价格" prop="actualPrice">
          <el-input v-model="form.actualPrice" placeholder="请输入实付价格"/>
        </el-form-item>
        <el-form-item label="纸质订单号" prop="paperNum">
          <el-input v-model="form.paperNum" placeholder="请输入纸质订单号"/>
        </el-form-item>
        <el-form-item label="订单号" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入订单号"/>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-select v-model="form.status" placeholder="状态" clearable size="small">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="chooseOpen" width="800px" append-to-body>
    <price-choose ref="priceChoose"></price-choose>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitChoose">确 定</el-button>
        <el-button @click="chooseOpen=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrder,
  getOrder,
  delOrder,
  addOrder,
  updateOrder,
  exportOrder
} from "@/api/business/clothes/order/order";
import PriceChoose from "@/views/business/clothes/price/priceChoose.vue";

export default {
  name: "Order",
  components: {PriceChoose},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      statusOptions: [],
      tableData: [],
      // 总条数
      total: 0,
      // 洗衣订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      chooseOpen: false,
      choseClothes: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clientId: undefined,
        clientPhone: undefined,
        clientName: undefined,
        orderPrice: undefined,
        orderDiscount: undefined,
        actualPrice: undefined,
        paperNum: undefined,
        orderNum: undefined,
        status: undefined,
      },
      // 表单参数
      form: {}
      ,
      // 表单校验
      rules: {}
    }
      ;
  },
  created() {
    this.getList();
    this.getDicts("order_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询洗衣订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleDeleteTableDate(index) {
      this.tableData.splice(index,1);
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        clientId: undefined,
        clientPhone: undefined,
        clothesId: undefined,
        orderPrice: undefined,
        orderDiscount: undefined,
        actualPrice: undefined,
        remark: undefined,
        paperNum: undefined,
        orderNum: undefined,
        status: "1",
        delFlag: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
      }
      ;
      this.resetForm("form");
    },
    // 公告状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加洗衣订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id =
        row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改洗衣订单";
      });
    },
    /** 提交按钮 */
    submitChoose:function (){
     this.tableData= this.$refs.priceChoose.chooseItem
      this.chooseOpen=false
    },
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrder(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除洗衣订单编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有洗衣订单数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return
        exportOrder(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () {
      });
    }
  }
};
</script>
