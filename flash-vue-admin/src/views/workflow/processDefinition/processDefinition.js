import processDefinitionApi from '@/api/workflow/processDefinition'
import permission from '@/directive/permission/index.js'
import {getToken} from '@/utils/auth'

export default {
  //如果需要标签页缓存生效，则需要保证name值和菜单管理中的编码值一致
  name: 'processDefinition',
  directives: {permission},
  data() {
    return {
      formVisible: false,
      formTitle: '添加流程定义',
      isAdd: true,
      form: {
        name: '',
        key: '',
        version: '',
        deploymentId: '',
        resourceName: '',
        id: ''
      },
      processDefinitionForm: {
        title: '',
        modelerUrl: "",
        visible: false,
      },

      listQuery: {
        page: 1,
        limit: 20,
        id: undefined
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
      localStorage.setItem("Authorization", getToken())
    },
    fetchData() {
      this.listLoading = true
      processDefinitionApi.getList(this.listQuery).then(response => {
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
    resetForm() {
      this.form = {
        name: '',
        key: '',
        version: '',
        deploymentId: '',
        resourceName: '',
        id: ''
      }
    },
    add() {
      this.formTitle = '添加流程定义'
      this.formVisible = true
      this.isAdd = true

      if (this.$refs['form'] !== undefined) {
        this.$refs['form'].resetFields()
      }
      //如果表单初始化有特殊处理需求,可以在resetForm中处理
    },
    onlineDrawingProcess() {
      this.processDefinitionForm.title = '编辑流程图'
      this.processDefinitionForm.visible = true
      localStorage.setItem("VUE_APP_BASE_API", process.env.VUE_APP_BASE_API)
      this.processDefinitionForm.modelerUrl = "/bpmnjs/index.html?type=addBpmn"
    },
    onlineModificationProcess(data) {
      this.processDefinitionForm.title = '查看流程图'
      this.processDefinitionForm.visible = true
      localStorage.setItem("VUE_APP_BASE_API", process.env.VUE_APP_BASE_API)
      this.processDefinitionForm.modelerUrl = '/bpmnjs/index.html?type=lookBpmn&deploymentFileUUID=' + data.deploymentId + '&deploymentName=' + encodeURI(data.resourceName);
    },
    closeDrawingWindow() {
      console.log('关闭窗口')
      this.fetchData()
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          const formData = {
            id: this.form.id,
            name: this.form.name,
            key: this.form.key,
            version: this.form.version,
            deploymentId: this.form.deploymentId,
            resourceName: this.form.resourceName,
          }
          if (formData.id) {
            processDefinitionApi.update(formData).then(response => {
              this.$message({
                message: this.$t('common.optionSuccess'),
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          } else {
            processDefinitionApi.add(formData).then(response => {
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
        this.formTitle = '编辑流程定义'
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
          processDefinitionApi.remove(id).then(response => {
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
