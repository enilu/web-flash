import workFlowRequestApi from '@/api/workflow/workFlowRequest'
import dictApi from '@/api/system/dict'
import processDefinitionApi from '@/api/workflow/processDefinition'
import permission from '@/directive/permission/index.js'

export default {
  //如果需要标签页缓存生效，则需要保证name值和菜单管理中的编码值一致
  name: 'workFlowTask',
  directives: {permission},
  data() {
    return {
      activeName: 'first',
      formVisible: false,
      formTitle: '发起新流程',
      isAdd: true,
      form: {
        title: '',
        processDefId: '',
        instanceId: '',
        descript: '',
        state: '',
        id: ''
      },
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined
      },
      processDefinitionList: [],
      dictStateList: [],
      total: 0,
      list: null,
      listLoading: true,
      selRow: {},
      taskResult: {
        list: []
      }
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
  computed: {

    //表单验证
    rules() {
      return {
        // cfgName: [
        //   { required: true, message: this.$t('config.name') + this.$t('common.isRequired'), trigger: 'blur' },
        //   { min: 3, max: 2000, message: this.$t('config.name') + this.$t('config.lengthValidation'), trigger: 'blur' }
        // ]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
      dictApi.getDicts('工作流实例状态').then(response => {
        this.dictStateList = response.data
      })

    },
    fetchData() {
      this.listLoading = true
      workFlowRequestApi.getTasks(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.id = ''
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
    formatState(state) {
      let ret = ''
      this.dictStateList.forEach(function (element) {
        if (state + '' == element.num) {
          ret = element.name
        }
      })
      return ret
    },
    resetForm() {
      this.form = {
        title: '',
        processDefId: '',
        instanceId: '',
        descript: '',
        state: '',
        id: ''
      }
    },
    add() {
      this.formTitle = '发起新流程'
      this.formVisible = true
      this.isAdd = true

      if (this.$refs['form'] !== undefined) {
        this.$refs['form'].resetFields()
      }
      //如果表单初始化有特殊处理需求,可以在resetForm中处理
    },
    pass() {
      this.form.state = 1
      console.log('form',this.form)
      workFlowRequestApi.completeTask(this.form).then(response => {
        this.fetchData()
        this.formVisible = false
      })
    },
    reject() {
      this.form.state = 2
      console.log('form',this.form)
      workFlowRequestApi.completeTask(this.form).then(response => {
        this.fetchData()
        this.formVisible = false
      })
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          const formData = {
            id: this.form.id,
            title: this.form.title,
            processDefId: this.form.processDefId,
            instanceId: this.form.instanceId,
            descript: this.form.descript,
            state: this.form.state,
          }
          if (formData.id) {
            workFlowRequestApi.update(formData).then(response => {
              this.$message({
                message: this.$t('common.optionSuccess'),
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          } else {
            workFlowRequestApi.add(formData).then(response => {
              this.$message({
                message: this.$t('common.optionSuccess'),
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
        message: this.$t('common.mustSelectOne'),
        type: 'warning'
      })
      return false
    },
    editItem(record) {
      this.selRow = record
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        let form = Object.assign({}, this.selRow)
        this.form = form
        this.formTitle = '编辑流程实例'
        this.formVisible = true

        if (this.$refs['form'] !== undefined) {
          this.$refs['form'].resetFields()
        }
        //如果表单初始化有特殊处理需求,可以在resetForm中处理
      }
    },
    removeItem(record) {
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id
        this.$confirm(this.$t('common.deleteConfirm'), this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
          workFlowRequestApi.remove(id).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
          }).catch(err => {
            this.$notify.error({
              title: '错误',
              message: err
            })
          })
        }).catch(() => {
        })
      }
    }

  }
}
