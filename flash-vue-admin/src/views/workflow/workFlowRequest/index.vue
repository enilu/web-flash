<template>
  <div class="app-container">
    <div class="block">

      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-caret-right" @click.native="add" v-permission="['/workflow/request/add']">{{ $t('workflow.startProcessInstance') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="edit" v-permission="['/workflow/request/update']">{{ $t('button.edit') }}</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="remove" v-permission="['/workflow/request/delete']">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{scope.row.title}}
        </template>
      </el-table-column>
      <el-table-column label="工作流类型">
        <template slot-scope="scope">
          {{scope.row.processDefName}}
        </template>
      </el-table-column>
      <el-table-column label="流程实例id">
        <template slot-scope="scope">
          {{scope.row.instanceId}}
        </template>
      </el-table-column>
      <el-table-column label="备注">
        <template slot-scope="scope">
          {{scope.row.descript}}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          {{ formatState(scope.row.state) }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
         <el-button type="text" size="mini" icon="el-icon-view" @click.native="viewImg(scope.row)" v-permission="['/workflow/request']" v-if="scope.row.state!=1">任务进度</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="total"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>


    <el-dialog
      title="任务进度"
      :visible.sync="processInstanceImg.show"
      width="70%">
      <div>
        <object style="width:80%"  type="image/svg+xml" :data="processInstanceImg.url"></object>
      </div>
    </el-dialog>
    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题">
              <el-input v-model="form.title" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作流类型">
              <el-select v-model="form.processDefId" placeholder="请选择">
                <el-option
                  v-for="item in processDefinitionList"
                  :key="item.deploymentId"
                  :label="item.name"
                  :value="item.deploymentId"

                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-show="form.instanceId">
            <el-form-item label="流程实例id">
              <el-input v-model="form.instanceId" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注">
              <el-input v-model="form.descript" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-show="form.state">
            <el-form-item label="状态">
              <el-input v-model="form.state" minlength=1></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./workFlowRequest.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

