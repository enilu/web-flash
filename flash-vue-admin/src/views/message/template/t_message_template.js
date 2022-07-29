import templateApi from '@/api/message/template'
import senderApi from '@/api/message/sender'
import permission from '@/directive/permission/index.js'

export default {
  name: 'msgTpl',
  directives: {permission},
  data() {
    return {
      formVisible: false,
      formTitle: '添加消息模板',
      isAdd: true,
      form: {
        code: '',
        title: '',
        content: '',
        type: '',
        idMessageSender: '',
        remoteTplCode: '',
        id: ''
      },
      sendList: [],
      listQuery: {
        page: 1,
        limit: 20,
        id: '',
        idMessageSender: '',
        title: ''
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
      this.fetchData()
      senderApi.queryAll().then(response => {
        this.sendList = response.data
      })
    },
    fetchData() {
      this.listLoading = true
      templateApi.getList(this.listQuery).then(response => {
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
      this.listQuery.id = ''
      this.listQuery.idMessageSender = ''
      this.listQuery.title = ''
      this.listQuery.page = 1
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
        code: '',
        title: '',
        content: '',
        type: 0,
        idMessageSender: '',
        remoteTplCode: '',
        id: ''
      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加消息模板',
        this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          templateApi.save({
            code: this.form.code,
            title: this.form.title,
            content: this.form.content,
            type: this.form.type,
            remoteTplCode: this.form.remoteTplCode,
            idMessageSender: this.form.idMessageSender,
            id: this.form.id
          }).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
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
      this.selRow = Object.assign({}, record);
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '编辑消息模板'
        this.formVisible = true
      }
    },
    removeItem(record) {
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        const id = this.selRow.id
        this.$confirm(this.$t('common.deleteConfirm'), this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
          templateApi.remove(id).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    }

  }
}
