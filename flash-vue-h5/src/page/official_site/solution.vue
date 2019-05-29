<template>
  <div>
    <x-header title="解决方案" :left-options="{showBack: false}"></x-header>
    <swiper :list="banner.list" v-model="banner.index" @on-index-change="bannerChange"  ></swiper>
    <productList title="解决方案" :list="solutionList" type="solution"  ></productList>
    <footMenu   ></footMenu>

  </div>
</template>
<script src="./solution.js"></script>
