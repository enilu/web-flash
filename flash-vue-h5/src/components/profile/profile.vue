<template>
  <div class="profile">
    <div class="avatar" style="width: 60px"><img
      src="static/images/icon/user.png">
    </div>
    <div class="user-info"><p class="login">
      <p>
      <router-link to="/login">登录</router-link>/
      <router-link to="/register">注册</router-link>
    </p>
    </div>
  </div>
</template>

<style scoped lang="less">
  .profile {
    display: -webkit-flex;
    display: flex;
    background-image:@header-background-image;
    padding: 6.666667vw 4vw;
    color: #fff;
    -webkit-align-items: center;
    align-items: center;
  }

  .avatar {
    border-radius: 50%;
  }

  .avatar > img, .avatar > span {
    border-radius: 50%;
    width: 100%;
  }

  img {
    max-width: 100%;
  }

  .user-info {
    overflow: hidden;
    margin-left: 4.8vw;
    flex-grow: 1;
  }

 a{
   color:white;
 }

  .login {
    font-size: .7rem;
  }

</style>
