<template>
  <div>
    <x-header title="关于MicroSite"></x-header>
    <div>
    <p class="logo">
      <img src="static/images/profile.jpg">
    </p>
    <p class="about">
      <strong>MicroSite</strong><br/>H5&小程序 官网解决方案专家
    </p>
    </div>
    <footMenu></footMenu>
  </div>
</template>
<script>
  import { XHeader } from 'vux'
  import footMenu from '../../components/footer/footMenu'

  export default {
    components: {
      XHeader,
      footMenu
    }
  }
</script>
<style scope>
  .logo{
    margin-top:0px;
    text-align: center;
    font-size:.8rem;
  }
  .logo>img{
    width:100%;
  }
  .about{
    margin:20px;
    text-align: center;
  }
</style>
