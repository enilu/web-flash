import treeTable from '@/components/TreeTable'
import deptApi from '@/api/system/dept'
import permission from '@/directive/permission/index.js'

export default {
  directives: {permission},
  name: 'dept',
  components: {treeTable},
  data() {
    return {
      expandAll: true,
      data: [],
      formVisible: false,
      formTitle: '',
      isAdd: false,
      deptTree: {
        data: [],
      },
      form: {
        id: '',
        simplename: '',
        fullname: '',
        pid: undefined,
        num: ''
      },
      rules: {
        simplename: [
          {required: true, message: '请输入菜单名称', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        fullname: [
          {required: true, message: '请输入编码', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ],
        num: [
          {required: true, message: '请输入排序', trigger: 'blur'}
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
      deptApi.list().then(response => {
        this.data = response.data
        this.deptTree.data = response.data
        this.listLoading = false
      })
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
          const menuData = {
            id: self.form.id,
            simplename: self.form.simplename,
            fullname: self.form.fullname,
            num: self.form.num,
            pid: self.form.pid
          }//self.form
          menuData.parent = null
          deptApi.save(menuData).then(response => {
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
    editItem(record) {
      this.selRow = Object.assign({}, record);
      if (this.selRow.pid == '0') {
        this.selRow.pid = undefined
      }
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = Object.assign({}, this.selRow);
        this.form.statusBool = this.form.statusName === '启用'
        this.form.password = ''
        this.formTitle = '修改用户'
        this.formVisible = true
      }
    },
    removeItem(record) {
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const row = this.selRow
          deptApi.del(row.id).then(response => {
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
}
