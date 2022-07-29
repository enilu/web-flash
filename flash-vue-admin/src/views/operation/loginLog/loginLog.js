import loginLogApi from '@/api/system/loginLog'
import permission from '@/directive/permission/index.js'

export default {
  name: 'loginLog',
  directives: {permission},
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 20,
        beginTime: undefined,
        endTime: undefined
      },
      total: 0,
      list: null,
      listLoading: true,
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
      loginLogApi.getList(this.listQuery).then(response => {
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
      this.listQuery.beginTime = ''
      this.listQuery.endTime = ''
      this.listQuery.page = 1
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
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
    clear() {
      this.$confirm('确定清空数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        loginLogApi.clear().then(response => {
          this.$message({
            message: '清空成功',
            type: 'sucess'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    }

  }
}
