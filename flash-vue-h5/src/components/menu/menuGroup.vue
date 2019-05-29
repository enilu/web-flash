<template>
  <div>

    <flexbox style="text-align: center;padding:5px 0px 0px 0px;">
      <flexbox-item v-for="(item,index) in menuData" v-bind:key="item.link" >
        <router-link :to="item.link" :class="item.class" >
        <icon :name="item.icon"></icon>
        <p  >{{item.name}}</p>
        </router-link>
      </flexbox-item>

    </flexbox>
  </div>
</template>
<style scoped>
  .vux-flexbox-item > img {
    width: 2rem;
  }

  .vux-flexbox-item > a >p {
    color:black;font-size:.8rem;
  }
  .one{
    color:#4285F4;
  }
  .two{
    color:#EA4335;
  }
  .there{
    color:#FBBC05;
  }
  .four{
    color:#34A853;
  }
  .five{
    color:#4285F4;
  }
</style>
<script>
  import { Flexbox, FlexboxItem } from 'vux'

  import * as types from '../../store/types'

  export default {
    components: {
      Flexbox, FlexboxItem
    },
    data() {
      return {
        menuData: this.getMenuData()
      }
    },
    methods: {
      getMenuData() {
        return types.MENU_GROUP_DATA
      }
    }
  }
</script>
