<template>
  <el-select  size="mini" :placeholder="placeholder">
    <el-option
      v-for="item in dictList"
      :key="item.num"
      :label="item.name"
      :value="item.num">
    </el-option>
  </el-select>
</template>

<script>
import { getDicts } from '@/api/system/dict'
export default {
  props: {
    dictName: {
      type: String,
      default: ''
    },
    placeholder:{
      type:String,
      default:''
    }
  },
  data() {
    return {
      dictList: null
    }
  },
  created() {
    this.getDictList()
  },
  methods: {
    getDictList() {
      getDicts(dictName).then(response=>{
        this.dictList = response.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
