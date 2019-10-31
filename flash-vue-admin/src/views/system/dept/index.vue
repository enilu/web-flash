<template>
  <div class="app-container">
    <div class="block">
      <el-button type="success" size="mini" icon="el-icon-plus"  @click.native="add">{{ $t('button.add') }}</el-button>
    </div>

    <tree-table
    :data="data"
    :expandAll="expandAll"
    highlight-current-row
    border>
      <el-table-column label="简称" >
        <template slot-scope="scope">
          <el-button type="text" @click="edit(scope.row)">{{scope.row.simplename}}</el-button>

        </template>
      </el-table-column>
      <el-table-column label="全称" >
        <template slot-scope="scope">
          <span >{{scope.row.fullname}}</span>
        </template>
      </el-table-column>
      <el-table-column label="顺序" >
        <template slot-scope="scope">
          <span >{{scope.row.num}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">
          <el-button type="text" @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </tree-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="simplename">
              <el-input v-model="form.simplename" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="全称" prop="fullname">
              <el-input v-model="form.fullname"  minlength=1></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="排序" prop="num">
              <el-input type="number" v-model="form.num"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="父部门" >
              <el-input
                placeholder="请选择父部门"
                v-model="form.pname"
                readonly="readonly"
                @click.native="showTree = !showTree">
              </el-input>
              <el-tree v-if="showTree"
                       empty-text="暂无数据"
                       :expand-on-click-node="false"
                       :data="data"
                       :props="defaultProps"
                       @node-click="handleNodeClick"
                       class="input-tree">
              </el-tree>

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

<script src="./dept.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
