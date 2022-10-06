import { deleteUser, getList, saveUser, remove, setRole, changeStatus,resetPassword } from '@/api/system/user'
import deptApi from '@/api/system/dept'
import { parseTime } from '@/utils/index'
import roleApi from '@/api/system/role'
// 权限判断指令
import permission from '@/directive/permission/index.js'

export default {
  name:'mgr',
  directives: { permission },
  data() {
    return {
      roleDialog: {
        visible: false,
        roles: [],
        roleTree: [],
        checkedRoleKeys: [],
        defaultProps: {
          id: 'id',
          label: 'name',
          children: 'children'
        }
      },
      statusList:[
        {label:'启用',value:'1'},
        {label:'冻结',value:'2'}
      ],
      formVisible: false,
      formTitle: '添加用户',
      deptTree: {
        data: [],
      },
      isAdd: true,
      form: {
        id: '',
        account: '',
        name: '',
        birthday: '',
        sex: 1,
        email: '',
        password: '',
        rePassword: '',
        dept: '',
        statusBool: true,
        deptid: undefined
      },
      rules: {
        account: [
          { required: true, message: '请输入登录账号', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        deptid:[
          { required: true, message: '请选择所属部门', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入email', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        limit: 20,
        account: undefined,
        name: undefined,
        deptid:undefined,
        phone:undefined,
        status:undefined,
        sex:undefined
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
      deptApi.list().then(response => {
        this.deptTree.data = response.data
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.account = ''
      this.listQuery.name = ''
      this.listQuery.page = 1
      this.listQuery.deptid=''
      this.listQuery.status =''
      this.listQuery.phone=''
      this.listQuery.sex=''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

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
        id: '',
        account: '',
        name: '',
        birthday: '',
        sex: 1,
        email: '',
        password: '',
        rePassword: '',
        dept: '',
        statusBool: true,
        deptid: undefined
      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加用户'
      this.formVisible = true
      this.isAdd = true
    },
    changeUserStatus(record){
      changeStatus(record.id).then(response => {
        this.$message({
          message: '提交成功',
          type: 'success'
        })
        this.fetchData()
      })
    },
    validPasswd() {
      if (!this.isAdd) {
        return true
      }

      if (this.form.password !== this.form.rePassword) {
        this.$message({
          message: '前后密码不一致',
          type: 'error'
        })
        return false
      }
      if (this.form.password === '' || this.form.rePassword === '') {
        this.$message({
          message: '密码不能为空',
          type: 'error'
        })
        return false
      }
      return true
    },
    saveUser() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.validPasswd()) {
            var form = self.form
            console.log('form.status',form.status);
            if (form.statusBool === true) {
              //启用
              form.status = 1
            } else {
              //冻结
              form.status = 2
            }
            form.birthday = parseTime(form.birthday, '{y}-{m}-{d}')
            form.createtime = parseTime(form.createtime)
            form.dept = null
            saveUser(form).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          }
        } else {
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
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        let form = Object.assign({}, this.selRow);
        form.statusBool = form.statusName === '启用'
        form.password = ''
        this.form = form
        this.formTitle = '修改用户'
        this.formVisible = true
      }
    },
    removeItem(record){
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id

        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    },

    resetPwd() {
      if (this.checkSel()) {
        var id = this.selRow.id
        this.$confirm('密码将重置为111111?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          resetPassword(id).then(response => {
            this.$message({
              message: '重置密码成功',
              type: 'success'
            })
          }).catch(err => {
            this.$notify.error({
              title: '错误',
              message: err,
            })
          })
        }).catch(() => {
        })
      }
    },
    chooseDept(data,node){
      this.listQuery.deptid = data.id
      this.search()
    },
    openRoleItem(record){
      this.selRow = record
      this.openRole()
    },
    openRole() {
      if (this.checkSel()) {
        roleApi.roleTreeListByIdUser(this.selRow.id).then(response => {
          this.roleDialog.roles = response.data.treeData
          this.roleDialog.checkedRoleKeys = response.data.checkedIds
          this.roleDialog.visible = true
        })
      }
    },
    setRole() {
      var checkedRoleKeys = this.$refs.roleTree.getCheckedKeys()
      var roleIds = ''
      for (var index in checkedRoleKeys) {
        roleIds += checkedRoleKeys[index] + ','
      }
      var data = {
        userId: this.selRow.id,
        roleIds: roleIds
      }
      setRole(data).then(response => {
        this.roleDialog.visible = false
        this.fetchData()
        this.$message({
          message: '提交成功',
          type: 'success'
        })
      })
    }

  }
}
