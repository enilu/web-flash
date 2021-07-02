import { remove, getList, save, savePermissons } from '@/api/system/role'
import { list as getDeptList } from '@/api/system/dept'
import { menuTreeListByRoleId } from '@/api/system/menu'
import permission from '@/directive/permission/index.js'

export default {
  name:'role',
  directives: { permission },
  data() {
    return {
      formVisible: false,
      formTitle: '添加角色',

      roleList: [],
      isAdd: true,
      checkedPermissionKeys: [],
      permissons: [],
      defaultProps: {
        id: 'id',
        label: 'name',
        children: 'children'
      },
      permissonVisible: false,
      deptTree: {
          data: [],
      },
      roleTree: {
        data:[],
      },
      form: {
        tips: '',
        name: '',
        deptid: '',
        pid: undefined,
        id: '',
        version: '',
        deptName: '',
        pName: '',
        num: 1
      },
      rules: {
        tips: [
          { required: true, message: '请输入角色编码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined,
        tips:undefined,
      },
      total: 0,
      list: null,
      listLoading: true,
      selRow: {}
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      getDeptList().then(response => {
        this.deptTree.data = response.data
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.roleTree.data = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.listQuery.page = 1
      this.listQuery.tips = ''
      this.fetchData()
    },
    handleFilter() {
      this.getList()
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        tips: '',
        name: '',
        deptid: undefined,
        pid: undefined,
        id: '',
        version: '',
        pName: '',
        num: 1

      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加角色'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: this.form.id,
            num: this.form.num,
            deptid: this.form.deptid,
            pid: this.form.pid,
            name: this.form.name,
            tips: this.form.tips
          }).then(response => {
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
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
    editItem(record){
      this.selRow= Object.assign({},record);
      console.log('sel',this.selRow);
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.form.status = this.selRow.statusName === '启用'
        this.form.password = ''
        this.formTitle = '修改角色'
        this.formVisible = true
      }
    },
    removeItem(record){
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        const id = this.selRow.id
        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            this.fetchData()
          }).catch( err=> {
            this.$notify.error({
              title: '错误',
              message:err,
            })
          })
        }).catch(() => {
        })
      }
    },
    openPermissionsItem(record){
      this.selRow = record
      this.openPermissions()
    },
    openPermissions() {
      if (this.checkSel()) {
        menuTreeListByRoleId(this.selRow.id).then(response => {
          this.permissons = response.data.treeData
          this.checkedPermissionKeys = response.data.checkedIds
          this.permissonVisible = true
        })
      }
    },
    savePermissions() {
      let checkedNodes =this.$refs.permissonTree.getCheckedNodes(false,true)
      let menuIds = ''
      for (var index in checkedNodes) {
        menuIds += checkedNodes[index].id + ','
      }
      const data = {
        roleId: this.selRow.id,
        permissions: menuIds
      }
      savePermissons(data).then(response => {
        this.permissonVisible = false
        this.$message({
          message: '提交成功',
          type: 'success'
        })
      })
    }
  }
}
