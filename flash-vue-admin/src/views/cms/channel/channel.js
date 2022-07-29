import channelApi from '@/api/cms/channel'
import permission from '@/directive/permission/index.js'

export default {
  name: 'channel',
  directives: {permission},
  data() {
    return {
      formVisible: false,
      formTitle: '添加栏目',
      deptList: [],
      isAdd: true,
      form: {
        id: '',
        name: '',
        code: ''
      },
      total: 0,
      list: null,
      listLoading: true,
      selRow: {}
    }
  },

  computed: {
    rules() {
      return {
        cfgName: [
          {required: true, message: '名称不能为空', trigger: 'blur'}
        ],
        cfgValue: [
          {required: true, message: '编码不能为空', trigger: 'blur'}
        ]
      }
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
      channelApi.getList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.page = 1
      this.fetchData()
    },
    handleFilter() {
      this.getList()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        id: '',
        name: '',
        code: ''
      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加栏目'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          channelApi.save({
            id: this.form.id,
            name: this.form.name,
            code: this.form.code
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
      this.selRow = record
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '编辑栏目'
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
          channelApi.remove(id).then(response => {
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
