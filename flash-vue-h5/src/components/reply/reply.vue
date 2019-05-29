<template>
  <div>
  <div class="replies" v-for="(item,index) in replies" v-bind:key="item.id">

    <flexbox>
      <flexbox-item :span="2">
        <img :src="item.author.avatar" class="avatar">
      </flexbox-item>
      <flexbox-item :span="5">
        <p class="author-name">{{item.author.name}}</p>
      </flexbox-item>
      <flexbox-item :span="5">
        <p class="author-name">{{item.createAt}}</p>
      </flexbox-item>
    </flexbox>
    <p class="content" v-html="item.content"></p>
  </div>
  </div>
</template>


<script>
  import { Flexbox, FlexboxItem } from 'vux'

  export default {
    name: 'me-reply',
    props: { replies: [] },
    components: {
      Flexbox, FlexboxItem
    }
  }
</script>
<style scoped>

  .replies{
    padding:10px;
  }
  .replies>.vux-flex-row{
    padding-top:3px;
  }
  .replies>p{
    font-size:.8rem;
  }
  .replies>.vux-flex-row>.vux-flexbox-item>.avatar{
    width:35px;
    border-radius: 25px;
  }
  .replies>.vux-flex-row>.vux-flexbox-item>.author-name{
    color:#999;font-size:.75rem;
  }
</style>
