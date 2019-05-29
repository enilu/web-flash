<template>
  <div>
    <group-title>{{title}}</group-title>
    <template v-if="column==='2'">
    <div v-for="(item,index) in list" v-bind:key="item.id" v-if="index%2 === 0">
      <grid :show-lr-borders="false" :show-vertical-dividers="false">
        <grid-item :link="'/article?id='+list[index].id+'&type='+type" :label="list[index].name">
          <img :src="list[index].img">
        </grid-item>
        <grid-item :link="'/article?id='+list[index+1].id+'&type='+type" :label="list[index+1].name">
          <img :src="list[index+1].img">
        </grid-item>
      </grid>
    </div>
    </template>
    <template v-if="column === '1'">
      <div v-for="(item,index) in list" v-bind:key="item.id">
        <grid :show-lr-borders="false" :show-vertical-dividers="false">
          <grid-item :link="'/article?id='+list[index].id+'&type='+type" :label="list[index].name">
            <img :src="list[index].img">
          </grid-item>
        </grid>
      </div>
    </template>
  </div>
</template>


<script>
  import {Grid, GridItem, GroupTitle} from 'vux'

  export default {
    props: {
      type: '',
      column: {
        type: String,
        default: '2'
      },
      title: {
        type: String,
        default: '产品列表'
      }, list: []
    },
    components: {
      Grid, GridItem, GroupTitle
    }
  }
</script>
<style scoped>
</style>
