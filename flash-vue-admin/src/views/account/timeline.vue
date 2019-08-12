<template>
  <div class="app-container">
    <div>
      <h3>{{user.name}}</h3>
    </div>
    <el-row style="box-shadow: 1px 0px 1px #838080;">
      <el-col :span="6"  style="box-shadow: 1px 0px 1px #838080;height:500px;width:220px;">
        <img class="user-avatar" style="width:200px;" src="@/assets/img/avatar.gif"><br>
        <p><span class="title"><i class="el-icon-phone"></i>&nbsp;&nbsp;{{ user.phone }}</span></p>
        <p><span class="title"><i class="el-icon-message"></i>&nbsp;&nbsp;{{ user.email }}</span></p>
        <p><span class="title"><i class="el-icon-view"></i>&nbsp;&nbsp;{{ user.status == '1'?'启用' : '禁用' }}</span></p>
        <p><span class="title"><i class="el-icon-location-outline"></i>&nbsp;&nbsp;上海市浦东大道290弄</span></p>
      </el-col>
      <el-col :span="18" style="padding-left:10px;">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="个人资料" name="profile"></el-tab-pane>
          <el-tab-pane label="最近活动" name="timeline"></el-tab-pane>
          <el-tab-pane label="修改密码" name="updatePwd"></el-tab-pane>
        </el-tabs>
        <el-form ref="form" :model="form"   label-width="150px">
        </el-form>
      </el-col>
    </el-row>


  </div>
</template>


<script src="./timeline.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

