<template>
  <div id="article">
    <p class="title">{{article.title}}</p>
    <flexbox class="header">
      <flexbox-item :span="2" >
        <img src="static/images/icon/user.png" class="avatar">
      </flexbox-item>
      <flexbox-item :span="10">
        <p >
          <span class="author-name">
          {{article.author}}
          </span>
         <span class="create-at">
           {{article.createTime}}</span>
        </p>
      </flexbox-item>
    </flexbox>
    <p class="content" v-html="article.content"></p>
  </div>
</template>

<script>
  import { Flexbox, FlexboxItem } from 'vux'

  export default {

    name: 'me-article',
    props: { article: {} },
    components: {
      Flexbox, FlexboxItem
    }
  }
</script>

<style scoped>
  #article{
    padding:10px;
  }
  #article>.title{
    font-size:1.2rem;
  }
  #article>.vux-flex-row>.vux-flexbox-item>.avatar{
    width:40px;
    border-radius: 30px;
  }
  #article>.vux-flex-row>.vux-flexbox-item>p>.author-name{
    color:#999;font-size:1rem;
  }
  #article>.vux-flex-row>.vux-flexbox-item>p>.create-at{
    font-size:.6rem;
  }
</style>
