<template>
  <div>
    <x-header title="产品&服务" :left-options="{showBack: false}"></x-header>
    <swiper :list="banner.list" v-model="banner.index" @on-index-change="bannerChange" ></swiper>
    <productList title="产品展示" :list="productList" type="product"  ></productList>
    <footMenu  ></footMenu>

  </div>
</template>
<script src="./product.js"></script>
