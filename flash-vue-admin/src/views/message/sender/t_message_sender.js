import senderApi from '@/api/message/sender'
import permission from '@/directive/permission/index.js'

export default {
  name: 'msgSender',
  directives: { permission },
  data() {
    return {
      formVisible: false,
      formTitle: '添加消息发送者',
      isAdd: true,
      form: {
        name:'',
        className:'',
        id: ''
      },
      listQuery: {
        page: 1,
        limit: 20,
        name:undefined,
        className:undefined
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
    },
    fetchData() {
      this.listLoading = true
      senderApi.getList(this.listQuery).then(response => {
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
      this.listQuery.page = 1
      this.listQuery.name=''
      this.listQuery.className=''
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
        name:'',
        className:'',
        id: ''
      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加消息发送者',
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          senderApi.save({
            name:this.form.name,
            className:this.form.className,
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
    editItem(record){
      this.selRow= Object.assign({},record);
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '编辑消息发送者'
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
        this.$confirm(this.$t('common.deleteConfirm'), this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
          senderApi.remove(id).then(response => {
              console.log(response)
              this.$message({
                message: this.$t('common.optionSuccess'),
                type: 'success'
              })
              this.fetchData()

          }).catch(err => {
            this.$notify.error({
              title: '错误',
              message:err,
            })
          })
        })
      }
    }

  }
}
