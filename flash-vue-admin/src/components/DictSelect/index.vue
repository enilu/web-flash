<template>
  <el-select
    v-model="dictValue"
    style="width: 100%"
    :size="size"
    :placeholder="placeholder"
    @change="change"
  >
    <el-option
      v-for="item in dictList"
      :key="item.num"
      :label="item.name"
      :value="item.num"
    />
  </el-select>
</template>

<script>
import dictApi from '@/api/system/dict'

export default {
  name: 'DictSelect',
  props: {
    value: {
      type: [String, Number],
      default: ''
    },
    size: {
      type: String,
      default: 'mini'
    },
    dictName: {
      type: String,
      default: '',
      require: true
    },
    placeholder: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      dictList: [],
      dictValue: ''
    }
  },
  watch: {
    value() {
      this.dictValue = this.value + ''
    }
  },
  created() {
    this.dictValue = this.value + ''
    this.getDictList()
  },
  methods: {
    getDictList() {
      // 从后台获取字典列表
      dictApi.getDicts(this.dictName).then(response => {
        this.dictList = response.data
      })
    },
    change(value) {
      this.$emit('input', value)
    }
  }
}
</script>
