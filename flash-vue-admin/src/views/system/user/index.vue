<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="4">
          <el-input v-model="listQuery.account" size="mini" placeholder="请输入帐号"></el-input>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.name" size="mini" placeholder="请输入姓名"></el-input>
        </el-col>
        <el-col :span="4">
          <dict-select v-model="listQuery.sex" dict-name="性别" placeholder="请选择性别" />
        </el-col>

        <el-col :span="4">
          <el-input v-model="listQuery.phone" size="mini" placeholder="请输入手机号"></el-input>
        </el-col>
        <el-col :span="4">
          <el-select v-model="listQuery.status" size="mini" placeholder="账号状态">
            <el-option
              v-for="item in statusList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>

        <el-col :span="4">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add" v-permission="['/mgr/add']">
            {{$t('button.add') }}
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="edit" v-permission="['/mgr/edit']">
            {{$t('button.edit') }}
          </el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="remove" v-permission="['/mgr/delete']">
            {{$t('button.delete') }}
          </el-button>
          <el-button type="danger" size="mini" icon="el-icon-refresh-right" @click.native="resetPwd" v-permission="['/mgr/edit']">
            {{$t('button.resetPwd') }}
          </el-button>

          <el-button type="info" size="mini" icon="el-icon-s-operation" @click.native="openRole" v-permission="['/mgr/setRole']">角色分配</el-button>
        </el-col>
      </el-row>
    </div>

    <el-row>
      <el-col :span="4">
        <el-tree
                 empty-text="暂无数据"
                 :expand-on-click-node="false"
                 :default-expand-all="true"
                 :data="deptTree.data"
                 :props="deptTree.defaultProps"
                 @node-click="chooseDept"
                 class="input-tree">
        </el-tree>
      </el-col>
      <el-col :span="20">
        <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
    @current-change="handleCurrentChange">

      <el-table-column label="账号">
        <template slot-scope="scope">
          {{scope.row.account}}
        </template>
      </el-table-column>
      <el-table-column label="姓名">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>


      <el-table-column label="部门">
        <template slot-scope="scope">
          {{scope.row.dept.simplename}}
        </template>
      </el-table-column>

      <el-table-column label="电话">
        <template slot-scope="scope">
          {{scope.row.phone}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" min-width="120px">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status==1" @change="changeUserStatus(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="150px">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit" @click.native="editItem(scope.row)" v-permission="['/mgr/edit']">
            {{$t('button.edit') }}
          </el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click.native="removeItem(scope.row)" v-permission="['/mgr/delete']">
            {{$t('button.delete') }}
          </el-button>
          <el-button type="text" size="mini" icon="el-icon-s-operation" @click.native="openRoleItem(scope.row)" v-permission="['/mgr/setRole']">角色分配</el-button>
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

      </el-col>
    </el-row>
    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" label-position="right">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账户" prop="account">
              <el-input v-model="form.account" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name"  minlength=1></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="2">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-show="isAdd">
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password"  type="password"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-show="isAdd">
            <el-form-item label="确认密码" prop="rePassword">
              <el-input v-model="form.rePassword"  type="password"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话" prop="phone">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属部门" prop="deptid" >
              <treeselect v-model="form.deptid"  :options="deptTree.data"  placeholder="请选择所属部门"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否启用">
              <el-switch v-model="form.statusBool"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" style="width: 100%;">

                </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="saveUser">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="角色分配"
      :visible.sync="roleDialog.visible"
      width="25%">
      <el-form>
        <el-row>
          <el-col :span="12">
            <el-tree
              :data="roleDialog.roles"
              ref="roleTree"
              show-checkbox
              node-key="id"
              :default-checked-keys="roleDialog.checkedRoleKeys"
              :props="roleDialog.defaultProps">
            </el-tree>

          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="setRole">{{ $t('button.submit') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./user.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

