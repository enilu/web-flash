<template>
  <div class="app-container">
    <div class="block">
      <iframe src="http://localhost:8082/druid/sql.html" width="100%" height="768px" frameborder="0" scrolling="auto"></iframe>


    </div>

  </div>
</template>

<script>
  export default {
    created: function () {
      this.init()
    },
    methods: {
      init() {
        this.$notify({
          title: '提示',
          message: '用户名密码参考：application.properties',
          duration: 3000
        })
      },
    }
  };
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

