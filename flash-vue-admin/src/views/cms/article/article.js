import articleApi from '@/api/cms/article'
import {getApiUrl} from '@/utils/utils'
import permission from '@/directive/permission/index.js'

export default {
  name: 'article',
  directives: {permission},
  data() {
    return {
      formVisible: false,
      formTitle: '添加文章',
      deptList: [],
      isAdd: true,
      form: {
        id: '',
        title: '',
        author: '',
        img: ''
      },

      listQuery: {
        page: 1,
        limit: 20,
        title: undefined,
        author: undefined,
        startDate: undefined,
        endDate: undefined
      },
      rangeDate: undefined,
      total: 0,
      list: null,
      listLoading: true,
      selRow: {},
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }
        ]
      }

    }
  },
  computed: {
    rules() {
      return {
        title: [
          {required: true, message: '标题不能为空', trigger: 'blur'}
        ],
        author: [
          {required: true, message: '作者不能为空', trigger: 'blur'}
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
      let queryData = this.listQuery
      if (this.rangeDate) {
        queryData['startDate'] = this.rangeDate[0]
        queryData['endDate'] = this.rangeDate[1]

      }
      articleApi.getList(queryData).then(response => {
        this.list = response.data.records
        for (const index in this.list) {
          const item = this.list[index]
          item.img = getApiUrl() + '/file/getImgStream?idFile=' + item.img
        }
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.listQuery.page = 1
      this.fetchData()
    },
    reset() {
      this.listQuery.title = undefined
      this.listQuery.author = undefined
      this.listQuery.startDate = undefined
      this.listQuery.endDate = undefined
      this.rangeDate = ''
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
    add() {
      this.$router.push({path: '/cms/articleEdit'})
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
        this.$router.push({path: '/cms/articleEdit', query: {id: this.selRow.id}})
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
          articleApi.remove(id).then(response => {
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
