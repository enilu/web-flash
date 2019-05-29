<template>
  <div>
    <swiper :list="banner.list" v-model="banner.index" @on-index-change="bannerChange" ></swiper>
     <menuGroup></menuGroup>
    <productList title="产品展示" :list="productList" ></productList>
    <productList title="解决方案" :list="solutionList" ></productList>
    <group-title>{{title}}</group-title>
    <template >
    <group-title>最新资讯</group-title>
    <panel :list="newsList"></panel>
    </template>
    <footMenu ></footMenu>

  </div>
</template>
<script src="./index.js"></script>
