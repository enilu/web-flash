<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input v-model="listQuery.cfgName" size="mini" :placeholder="$t('config.name')"></el-input>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.cfgValue" size="mini" :placeholder="$t('config.value')"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add" v-permission="['/cfg/add']">{{ $t('button.add') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="edit" v-permission="['/cfg/update']">{{ $t('button.edit') }}</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="remove" v-permission="['/cfg/delete']">{{ $t('button.delete') }}</el-button>
          <el-button type="info" size="mini" icon="el-icon-document" @click.native="exportXls">{{ $t('button.export') }}</el-button>
        </el-col>
      </el-row>
    </div>


    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">

      <el-table-column label="ID">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('config.name')">
        <template slot-scope="scope">
          {{scope.row.cfgName}}
        </template>
      </el-table-column>
      <el-table-column :label="$t('config.value')">
        <template slot-scope="scope">
          <el-button type="text" v-if="scope.row.cfgValue.length>50" title="点击查看全部参数值" @click="showCfgValDialog(scope.row)">{{scope.row.cfgValue.substr(0,25)}}</el-button>
          <div v-else> {{scope.row.cfgValue}}</div>
        </template>
      </el-table-column>
      <el-table-column :label="$t('config.descript')">
        <template slot-scope="scope">
          {{scope.row.cfgDesc}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit" @click.native="editItem(scope.row)" v-permission="['/cfg/update']">{{ $t('button.edit') }}</el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click.native="removeItem(scope.row)" v-permission="['/cfg/delete']">{{ $t('button.delete') }}</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="total"
      :current-page.sync="listQuery.page"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>
    <el-dialog
      :title="dialog.title"
      :visible.sync="dialog.show"
      width="50%" >
      <span>{{dialog.content}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click.native="dialog.show = false">{{ $t('button.close') }}</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item :label="$t('config.name')" prop="cfgName">
              <el-input v-model="form.cfgName" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('config.descript')" prop="cfgDesc">
              <el-input v-model="form.cfgDesc"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item :label="$t('config.value')" prop="cfgValue">
              <el-input v-model="form.cfgValue" minlength=1 type="textarea" :rows="5"></el-input>
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

<script src="./cfg.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

