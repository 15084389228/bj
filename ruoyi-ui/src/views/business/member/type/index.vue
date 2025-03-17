<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="会员卡名称" prop="memberTypeName">
        <el-input
          v-model="queryParams.memberTypeName"
          placeholder="请输入会员卡名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员类型" prop="memberType">
        <el-select v-model="queryParams.memberType" placeholder="请选择会员类型" clearable
                   size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="会员折扣" prop="memberDiscount">
        <el-input
          v-model="queryParams.memberDiscount"
          placeholder="请输入会员折扣"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员卡状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择会员卡状态" clearable
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
          v-hasPermi="['system:type:add']"
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
          v-hasPermi="['system:type:remove']"
        >删除
        </el-button>
      </el-col>

    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="会员卡名称" align="center" prop="memberTypeName"/>
      <el-table-column label="会员类型" align="center" prop="memberType" :formatter="typeFormat"/>
      <el-table-column label="会员折扣" align="center" prop="memberDiscount"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="状态" align="center" prop="status"
      :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:type:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:type:remove']"
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
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="会员卡名称" prop="memberTypeName">
          <el-input v-model="form.memberTypeName" placeholder="请输入会员卡名称"/>
        </el-form-item>
        <el-form-item label="会员类型">
          <el-select v-model="form.memberType" placeholder="请选择会员类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="会员折扣" prop="memberDiscount">
          <el-input-number v-model="form.memberDiscount" placeholder="请输入会员折扣" min="5" max="10" step="0.05" />
        </el-form-item>
        <el-form-item label="会员卡状态">
          <el-radio-group v-model="form.status">
          <el-radio
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictValue"
          >{{dict.dictLabel}}</el-radio>
        </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    maxlength="1000"
                    show-word-limit
                    placeholder="请输入内容" />
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
  listType,
  getType,
  delType,
  addType,
  updateType,
  exportType
} from "@/api/business/member/type/type";

export default {
  name: "Type",
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
      typeOptions:[],
      // 总条数
      total: 0,
      // 会员表格数据
      typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        memberTypeName: undefined,
        memberType: undefined,
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
    this.getDicts("member_type").then(response => {
      this.typeOptions = response.data;
    });


  },
  methods: {
    /** 查询会员列表 */
    getList() {
      this.loading = true;
      listType(this.queryParams).then(response => {
        this.typeList = response.rows;
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
        memberTypeName: undefined,
        memberType: undefined,
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
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.memberType);
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id =
        row.id || this.ids
      getType(id).then(response => {
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
            updateType(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addType(this.form).then(response => {
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
        return delType(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () {
      });
    }
    /** 导出按钮操作 */
    // handleExport() {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否确认导出所有会员数据项?', "警告", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning"
    //   }).then(function () {
    //     return
    //   export Type(queryParams);
    //   }).then(response => {
    //     this.download(response.msg);
    //   }).catch(function () {
    //   });
    // }
  }
};
</script>
