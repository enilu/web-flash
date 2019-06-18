import treeTable from '@/components/TreeTable'
import { getList, save, delMenu } from '@/api/system/menu'

export default {
  name: 'treeTableDemo',
  components: { treeTable },
  data() {
    return {
      showTree: false,
      defaultProps: {
        id: 'code',
        label: 'name',
        children: 'children'
      },

      listLoading: true,
      expandAll: false,
      formTitle: '',
      formVisible: false,
      isAdd: false,
      form: {
        id: '',
        pname: '',
        name: '',
        code: '',
        url: '',
        pcode: '',
        ismenu: 1,
        num: 1
      },
      rules: {
        name: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入编码', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入请求地址', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ]
      },
      data: [],
      selRow: {}
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.data = response.data
        this.listLoading = false
      })
    },
    handleNodeClick(data, node) {
      this.form.pcode = data.code
      this.form.pname = data.name
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
          const menuData = self.form
          menuData.parent = null
          menuData.children = null
          save(menuData).then(response => {
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
      if (row.isMenuName === '是') {
        this.form.ismenu = 1
      } else {
        this.form.ismenu = 0
      }
      if (row.statusName === '启用') {
        this.form.status = 1
      } else {
        this.form.status = 0
      }
      if (row.parent) {
        this.form.pcode = row.parent.code
        this.form.pname = row.parent.name
      }
      console.log(this.form.pcode)
      this.formTitle = '编辑菜单'
      this.formVisible = true
      this.isAdd = false
    },
    remove(row) {
      console.log(row)
      this.$confirm('确定删除该记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delMenu(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.fetchData()
        }).catch(err =>{
          this.$notify.error({
            title: '错误',
            message:err,
          })
        })
      })
    }
  }
}
