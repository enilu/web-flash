<template>
  <div>
    <tab>
      <tab-item  v-for="item in list" @on-item-click="onItemClick" :selected="item.selected">
        {{item.name}}
      </tab-item>

    </tab>
  </div>
</template>


<script>
  import { Tab, TabItem } from 'vux'

  export default {
    name: 'meTab',
    props: {
      list: Array
    },
    components: {
      Tab, TabItem
    },
    methods: {
      onItemClick(index) {
        // const selectionArr = []
        // val.forEach(function (el) {
        //   selectionArr.push(el)
        // });
        this.$emit('tab-item-click', index)
      }
    }
  }
</script>
