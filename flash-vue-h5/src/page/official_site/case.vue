<template>
  <div>
    <x-header title="经典案例" :left-options="{showBack: false}"></x-header>
    <swiper :list="banner.list" v-model="banner.index" @on-index-change="bannerChange" ></swiper>
    <productList title="精选案例" :list="caseList" type="case"  column="1" ></productList>
    <footMenu  ></footMenu>

  </div>
</template>
<script src="./case.js"></script>
