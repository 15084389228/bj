<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="会员名称" prop="memberName">
        <el-input
          v-model="queryParams.memberName"
          placeholder="请输入会员名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员手机号" prop="memberPhone">
        <el-input
          v-model="queryParams.memberPhone"
          placeholder="请输入会员手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员余额" prop="memberPrice">
        <el-input
          v-model="queryParams.memberPrice"
          placeholder="请输入会员余额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员卡" prop="memberTypeId">
        <el-input
          v-model="queryParams.memberTypeId"
          placeholder="请选择会员卡"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员专属折扣" prop="memberDiscount">
        <el-input
          v-model="queryParams.memberDiscount"
          placeholder="请输入会员折扣"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择会员状态" clearable
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
          v-hasPermi="['system:info:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:info:remove']"
        >删除
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:info:export']"-->
<!--        >导出-->
<!--        </el-button>-->
<!--      </el-col>-->
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键id" align="center" prop="id"/>
      <el-table-column label="会员名称" align="center" prop="memberName"/>
      <el-table-column label="会员手机号" align="center" prop="memberPhone"/>
      <el-table-column label="会员余额" align="center" prop="memberPrice"/>
      <el-table-column label="会员卡id" align="center" prop="memberTypeId"/>
      <el-table-column label="会员折扣(专属折扣,覆盖会员卡折扣)" align="center" prop="memberDiscount"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="会员状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag
            :type="statusFormat(scope.row) === '启用' ? 'success' : 'danger'"
            disable-transitions>{{statusFormat(scope.row)}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
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

    <!-- 添加或修改会员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员名称" prop="memberName">
          <el-input v-model="form.memberName" placeholder="请输入会员名称"/>
        </el-form-item>
        <el-form-item label="会员手机号" prop="memberPhone">
          <el-input v-model="form.memberPhone" placeholder="请输入会员手机号"/>
        </el-form-item>
        <el-form-item label="会员余额" prop="memberPrice">
          <el-input v-model="form.memberPrice" placeholder="请输入会员余额"/>
        </el-form-item>
        <el-form-item label="会员卡id" prop="memberTypeId">
          <el-input v-model="form.memberTypeId" placeholder="请输入会员卡id"/>
        </el-form-item>
        <el-form-item label="会员折扣(专属折扣,覆盖会员卡折扣)" prop="memberDiscount">
          <el-input v-model="form.memberDiscount" placeholder="请输入会员折扣(专属折扣,覆盖会员卡折扣)"/>
        </el-form-item>
        <el-form-item label="会员状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listInfo,
  getInfo,
  delInfo,
  addInfo,
  updateInfo,
  exportInfo
} from "@/api/business/member/info/info";

export default {
  name: "Info",
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
      statusOptions:[],
      // 总条数
      total: 0,
      // 会员表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        memberName: undefined,
        memberPhone: undefined,
        memberPrice: undefined,
        memberTypeId: undefined,
        memberDiscount: undefined,
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
    this.getDicts("common_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询会员列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        memberName: undefined,
        memberPhone: undefined,
        memberPrice: undefined,
        memberTypeId: undefined,
        memberDiscount: undefined,
        remark: undefined,
        status: "0",
        delFlag: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
      }
      ;
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
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加会员";
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id =
        row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateInfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addInfo(this.form).then(response => {
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
      this.$confirm('是否确认删除会员编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有会员数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportInfo(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () {
      });
    }
  }
};
</script>
