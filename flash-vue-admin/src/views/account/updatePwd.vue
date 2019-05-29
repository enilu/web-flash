<template>
  <div class="app-container">
      <el-form ref="form" :model="form"  :rules="rules" label-width="150px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="原密码">
              <el-input type="password" v-model="form.oldPassword"  ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="新密码" prop="password">
              <el-input type="password" v-model="form.password"  minlength=5  ></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="24">
            <el-form-item label="重复密码">
              <el-input type="password" v-model="form.rePassword"  ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="updatePwd">{{ $t('button.submit') }}</el-button>
        </el-form-item>

      </el-form>
  </div>
</template>

<script src="./updatePwd.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

