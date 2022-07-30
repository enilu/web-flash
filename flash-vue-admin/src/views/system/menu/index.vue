<template>
  <div class="app-container">
    <div class="block">
      <el-button type="success" size="mini" icon="el-icon-plus"  @click.native="add" v-permission="['/menu/add']">{{ $t('button.add') }}</el-button>
    </div>
    <el-table
      :data="data"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      border
      :default-expand-all="false"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
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
      <el-table-column label="图标">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column label="组件" >
        <template slot-scope="scope">
          <span >{{scope.row.component}}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" >
        <template slot-scope="scope">
          <span >{{scope.row.ismenu==1?'菜单':'按钮'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="URL">
        <template slot-scope="scope">
          <span >{{scope.row.url}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否隐藏">
        <template slot-scope="scope">
          <span >{{scope.row.hidden}}</span>
        </template>
      </el-table-column>
      <el-table-column label="顺序">
        <template slot-scope="scope">
          <span >{{scope.row.num}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit" @click="edit(scope.row)" v-permission="['/menu/edit']">{{ $t('button.edit') }}</el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click="remove(scope.row)" v-permission="['/menu/remove']">{{ $t('button.delete') }}</el-button>
        </template>
      </el-table-column>
    </el-table>

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
              <el-form-item label="资源地址" prop="url">
                <el-input v-model="form.url"  minlength=1></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="菜单类型">
                <el-radio-group v-model="form.ismenu">
                  <el-radio :label="1">菜单</el-radio>
                  <el-radio :label="0">按钮</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12"  v-show="form.ismenu">
              <el-form-item label="是否隐藏">
                <el-radio-group v-model="form.hidden">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单编号" prop="code">
                <el-input v-model="form.code"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="父菜单" >
                <treeselect v-model="form.pcode"  :options="treeData"  placeholder="请选择父菜单/顶级菜单目录无需选择"/>
              </el-form-item>
            </el-col>

            <el-col :span="12" v-show="form.ismenu===1">
              <el-form-item label="组件">
                <el-input v-model="form.component" @focus="componentTips" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-show="form.ismenu===1">
              <el-form-item label="图标">
                <el-popover
                  placement="bottom-start"
                  width="414"
                  trigger="click"
                  @show="$refs['iconSelect'].reset()"
                >
                  <IconSelect ref="iconSelect" @selected="selected" />
                  <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                    <svg-icon
                      v-if="form.icon"
                      slot="prefix"
                      :icon-class="form.icon"
                      class="el-input__icon"
                      style="height: 32px;width: 16px;"
                    />
                    <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                 </el-input>
                </el-popover>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="排序" prop="num">
                <el-input type="number" v-model="form.num"></el-input>
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
