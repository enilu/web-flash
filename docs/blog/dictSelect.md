# DictSelect组件
DictSelect是一个基于系统字典数据提供的字典下拉组件
## 实现
- 后台管理-系统管理提供了字典数据的维护功能
- 一个字典包含了一组key-value的键值对；比如名称为“性别”的字典数据包含了两组键值对：[{key:男,value:1},{key:女,value:2}]
- 系统封装了DictSelect组件，[/flash-vue/src/components/DictSelect/index.vue](https://gitee.com/enilu/web-flash/blob/master/flash-vue-admin/src/components/DictSelect/index.vue) 方便对字典数据的使用

## 用法
- DictSelect组件 主要用于各种（编辑，查询等）表单中，根据传入的字典名称自动生成改字典的select下拉框
- 以用户列表页面性别查询下拉框为例用法如下：
```vue
<dict-select dictName="性别"
           v-model="listQuery.sex"
           @change="changeSexVal"
           placeholder="请选择性别">
</dict-select>
```

```javascript

import DictSelect from '@/components/DictSelect'
export default {
  components:{
    DictSelect
  },
  ...
  data() {
      return {
          listQuery:{
            sex:''
          }
      }
  }
  ...
  method:{
      changeSexVal(val){
        console.log('get user select value',val)
        this.listQuery.sex = val
      }
  }
}
```
