<template>
  <div>
   <img src="static/images/profile.jpg">
    <group>
      <cell title="关于我们" :link="{path:'/about'}"></cell>
      <cell title="立即预约" :link="{path:'/contact'}"></cell>
      <cell title="在线文档" @click.native="openDoc"></cell>
      <cell title="联系我们"  @click.native="phoneCall">{{phoneNo}}</cell>
    </group>

    <footMenu></footMenu>
  </div>
</template>

<script src="./more.js">
</script>

<style>
</style>
