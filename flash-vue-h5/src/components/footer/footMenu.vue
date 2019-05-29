<template>
  <div id="foot_menu">
    <tabbar v-model="index">
      <tabbar-item :link="item.link" ref="indexTab" v-for="(item,index) in menuData" v-bind:key="item.link">
        <img slot="icon" :src="item.icon">
        <span slot="label">{{item.name}}</span>
      </tabbar-item>

    </tabbar>
  </div>
</template>

<script>
  import { Tabbar, TabbarItem } from 'vux'

  import * as types from '../../store/types'

  export default {
    components: {
      Tabbar,
      TabbarItem
    },
    data() {
      return {
        menuData: this.getMenuData(),
        index: this.selectIndex()
      }
    },
    methods: {
      getMenuData() {
        if (types.PRODUCT === types.PRODUCT_OFFICIAL_SITE) {
          return types.MENU_DATA_OFFICIAL_SITE
        }
        if (types.PRODUCT === types.PRODUCT_BBS) {
          return types.MENU_DATA_BBS
        }
      },
      selectIndex() {
        const menuData = this.getMenuData()
        for (let i = 0; i < menuData.length; i++) {
          if (this.$route.path.indexOf(menuData[i].link) > -1) {
            return i
          }
        }
        return 0;
      }
    }
  }
</script>
