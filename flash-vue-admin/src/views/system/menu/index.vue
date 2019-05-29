<template>
  <div class="app-container">
    <div class="block">
      <el-button type="success" icon="el-icon-plus"  @click.native="add">{{ $t('button.add') }}</el-button>
    </div>

    <tree-table
    :data="data"
    :expandAll="expandAll"
    highlight-current-row
    border>

      <el-table-column label="名称" >
        <template slot-scope="scope">
          <el-button type="text" @click="edit(scope.row)">{{scope.row.name}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="编码" >
        <template slot-scope="scope">
          <span >{{scope.row.code}}</span>
        </template>
      </el-table-column>
      <el-table-column label="ID" >
        <template slot-scope="scope">
          <span >{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否是菜单" >
        <template slot-scope="scope">
          <span >{{scope.row.isMenuName}}</span>
        </template>
      </el-table-column>
        <el-table-column label="URL">
          <template slot-scope="scope">
            <span >{{scope.row.url}}</span>
          </template>
      </el-table-column>
      <el-table-column label="是否启用">
        <template slot-scope="scope">
          <span >{{scope.row.statusName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="顺序">
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
              <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" minlength=1></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="请求地址" prop="url">
                <el-input v-model="form.url"  minlength=1></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="是否是菜单">
                <el-radio-group v-model="form.ismenu">
                  <el-radio :label="1">是</el-radio>
                  <el-radio :label="0">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="是否启用">
                <el-radio-group v-model="form.status">
                  <el-radio :label="1">是</el-radio>
                  <el-radio :label="0">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单编号" prop="code">
                <el-input v-model="form.code"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="图标" >
                <el-input v-model="form.icon"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="排序" prop="num">
                <el-input type="number" v-model="form.num"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="父菜单" >
                <el-input
                  placeholder="请选择父菜单"
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

<script src="./menu.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
