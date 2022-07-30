import treeTable from '@/components/TreeTable'
import menuApi from '@/api/system/menu'
import permission from '@/directive/permission/index.js'
import IconSelect from '@/components/IconSelect'

export default {
  directives: { permission },
  name: 'menu',
  components: { treeTable, IconSelect },
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
        name: '',
        code: '',
        url: '',
        pcode: '',
        ismenu: 1,
        hidden:false,
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
          { required: true, message: '请输入资源地址', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ]
      },
      data: [],
      treeData:[],
      selRow: {}
    }
  },
  created() {
    this.init()
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name
      this.$forceUpdate()
    },
    init() {
      this.fetchData()
      menuApi.getMenuTree().then(response => {
        this.treeData = response.data
      })
    },
    fetchData() {
      this.listLoading = true
      menuApi.getList().then(response => {
        this.data = response.data
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
      this.form = {ismenu: 1,hidden:false}
      this.formTitle = '添加菜单'
      this.formVisible = true
      this.isAdd = true
      if(this.$refs['form'] !== undefined) {
        this.$refs['form'].resetFields()
      }
    },
    save() {
      let self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          const menuData = self.form
          delete menuData.parent
          delete menuData.children
          menuApi.save(menuData).then(response => {
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
      this.form= Object.assign({},row);
      if (row.isMenuName === '是') {
        this.form.ismenu = 1
      } else {
        this.form.ismenu = 0
      }
      if (this.form.pcode=='0') {
        this.form.pcode =undefined
      }

      this.formTitle = '编辑菜单'
      this.formVisible = true
      this.isAdd = false
    },
    remove(row) {
      this.$confirm('确定删除该记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        menuApi.delMenu(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.fetchData()
        })
      })
    },
    componentTips(){
      this.$notify({
        title: '提示',
        dangerouslyUseHTMLString:true,
        message: '顶级目录请输入layout,<br/>左侧菜单请根据实际组件路径输入:views/...<br/>功能按钮无需输入该值'
      })
    }
  }
}
