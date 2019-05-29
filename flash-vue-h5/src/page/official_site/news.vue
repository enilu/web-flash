<template>
  <div>
    <x-header title="最新资讯" :left-options="{showBack: false}"></x-header>
    <swiper :list="banner.list" v-model="banner.index" @on-index-change="bannerChange"
            ></swiper>
    <panel :list="list" ></panel>
    <footMenu ></footMenu>
  </div>
</template>
<script src="./news.js"></script>
<style scope>
  .vux-label {
    font-size: .56rem;
  }
</style>
