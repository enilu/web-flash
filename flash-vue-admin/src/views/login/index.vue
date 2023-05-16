<template>
  <div class="login-container" style="background-image: url(./img/bg.jpg)">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">{{ $t("login.title") }}</h3>
        <lang-select class="set-language" />
      </div>
      <el-tabs v-model="qrcode.activeName" type="border-card" @tab-click="changeLoginType">
        <el-tab-pane label="账号密码登录" name="first">
          <el-form-item prop="username">
            <span class="svg-container">
              <svg-icon icon-class="user" />
            </span>
            <el-input
              v-model="loginForm.username"
              :placeholder="$t('login.username')"
              name="username"
              type="text"
              auto-complete="on"
            />
          </el-form-item>

          <el-form-item prop="password">
            <span class="svg-container">
              <svg-icon icon-class="password" />
            </span>
            <el-input
              :type="pwdType"
              v-model="loginForm.password"
              :placeholder="$t('login.password')"
              name="password"
              auto-complete="on"
              @keyup.enter.native="handleLogin"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon icon-class="eye" />
            </span>
          </el-form-item>

          <el-button
            :loading="loading"
            type="primary"
            style="width: 100%; margin-bottom: 30px"
            @click.native.prevent="handleLogin"> {{ $t("login.logIn") }} </el-button>

          <div style="position: relative">
            <div class="tips">
              <span>{{ $t("login.username") }} : admin</span>
              <span>{{ $t("login.password") }} : admin</span>
            </div>
            <div class="tips">
              <span style="margin-right: 18px">{{ $t("login.username") }} : auditUser</span>
              <span>{{ $t("login.password") }} : 123456</span>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="扫码登录" name="second" >
          <div v-if="qrcode.activeName=='second'"  >

              <div class="qrcode" v-show="!qrcode.showAppdownload">
                  <img   :src="qrcode.imgUrl"/>
                  <div  class="qrCodeCoverBlack"  v-show="qrcode.resultStatus=='invalid'">
                      <span >二维码已失效
                      </span><br>
                      <el-button   type="primary" size="mini" style="margin-top:15px;" @click.native="refreshQrcode">&nbsp;&nbsp;刷新&nbsp;&nbsp;</el-button>
                      <img  style="width: 40px; display:none;margin-top:-15px;" src="${base}/res/images/success.png"/>
                  </div>
              </div>
               <div class="app-download" v-show="qrcode.showAppdownload">
                  <div class="app-download-container">
                   <div> <p style="color:#fff;font-size:14px;font-weight: bold;">手机扫码下载APP</p></div>
                   <div>
                      <img style="width: 140px;border: 1px solid #ccc9c9;" src="@/assets/img/app_download.png">
                   </div>
                  </div>
              </div>
          </div>
            <div style="position: relative;text-align:center;">
                <div v-if="qrcode.resultStatus=='' ||qrcode.resultStatus=='undo' ">
                    <p>打开<span
                    style="color:#5b9bd1;cursor:pointer;"
                     @mouseenter="showAppdownload"
                     @mouseleave="hideAppdownload">web-flash APP</span>
                     </p>
                    <p style="font-size:10px ;margin-top:-5px;">在【首页-左上角-扫码登录】扫描二维码登录</p>
                </div>
                <div v-if="qrcode.resultStatus=='success'">
                    <p>扫码成功</p>
                    <p style="margin-bottom:5px;">请在手机上确认登录</p>
                    <el-button class="btn  btn-link btn-xs qrCodeRefresh" href="#" style="font-size:12px ;font-weight: bolder;color:rgb(0,146,248)">重新扫描</el-button>
                </div>
            </div>
        </el-tab-pane>
      </el-tabs>
    </el-form>
  </div>
</template>

<script src="./login.js"></script>
<style rel="stylesheet/scss" lang="scss" src="./login.scss"></style>
