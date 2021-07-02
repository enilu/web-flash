<template>
  <div class="app-container">
    <div class="block">
      <iframe src="http://flash-api.enilu.cn/druid/index.html" width="100%" height="768px" frameborder="0" scrolling="auto"></iframe>


    </div>

  </div>
</template>

<script>
  export default {
    name:'druid',
    created: function () {
      this.init()
    },
    methods: {
      init() {
        this.$notify({
          title: '提示',
          message: '查看application.yml配置文件获取账号密码',
          duration: 3000
        })
      },
    }
  };
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

