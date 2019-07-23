import treeTable from '@/components/TreeTable'
import { list, save, del } from '@/api/system/dept'

export default {
  name: 'customTreeTableDemo',
  components: { treeTable },
  data() {
    return {
      expandAll: true,
      data: [],
      formVisible: false,
      formTitle: '',
      isAdd: false,

      showTree: false,
      defaultProps: {
        id: 'id',
        label: 'simplename',
        children: 'children'
      },
      form: {
        id: '',
        simplename: '',
        fullname: '',
        pid: '',
        num: '',
        tips: ''
      },
      rules: {
        simplename: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        fullname: [
          { required: true, message: '请输入编码', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ]
      }

    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {
        this.data = response.data
        this.listLoading = false
      })
    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.pid = data.id
      this.form.pname = data.simplename
      this.showTree = false
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    add() {
      this.form = {}
      this.formTitle = '添加菜单'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log('form', self.form)
          const menuData = {id:self.form.id,simplename:self.form.simplename,fullname:self.form.fullname,num:self.form.num,pid:self.form.pid,tips:self.form.tips}//self.form
          menuData.parent = null
          save(menuData).then(response => {
            console.log(response)
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            self.fetchData()
            self.formVisible = false
          })
        } else {
          return false
        }
      })
    },
    edit(row) {
      this.form = row

      if (row.parent) {
        this.form.pid = row.parent.id
        this.form.pname = row.parent.simplename
      }
      this.formTitle = '编辑部门'
      this.formVisible = true
      this.isAdd = false
    },
    remove(row) {
      this.$confirm('确定删除该记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        del(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.fetchData()
        })
      })
    }
  }
}
