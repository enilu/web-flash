<template>
  <div class="app-container">


    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">

      <el-table-column label="申请人">
        <template slot-scope="scope">
          {{scope.row.userName}}
        </template>
      </el-table-column>
      <el-table-column label="申请时间">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{scope.row.title}}
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
          <el-button type="text" size="mini" icon="el-icon-edit" @click.native="editItem(scope.row)" v-permission="['/workflow/request/task']">{{ $t('button.audit') }}</el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click.native="removeItem(scope.row)" v-permission="['/workflow/request/delete']">{{ $t('button.delete') }}</el-button>
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
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题">
             <div>{{form.title}}</div>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="申请时间">
              <div>{{form.createTime}}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申请人">
              <div>{{form.userName}}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申请备注">
            <div>{{form.descript}}</div>
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item>
          <el-button type="primary" @click="pass">{{ $t('button.pass') }}</el-button>
          <el-button @click.native="reject">{{ $t('button.reject') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./task.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

