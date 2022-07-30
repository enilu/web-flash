import logApi  from '@/api/system/log'
import { updatePwd } from '@/api/user'

export default {
  data() {
    return {
      activeName: 'profile',
      user:{},
      reverse:false,
      activities: [],
      form: {
        oldPassword: '',
        password: '',
        rePassword: ''
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init(){
      this.user = this.$store.state.user.profile
      this.queryByUser()
    },
    handleClick(tab, event){
      // this.$router.push({ path: '/account/'+tab.name})
    },
    queryByUser() {
      logApi.queryByUser().then(response => {
        console.log(response)
        this.activities = response.data

      }).catch((err) => {
        this.$message({
          message: err,
          type: 'error'
        })
      })
    },
    updatePwd() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updatePwd({
            oldPassword: this.form.oldPassword,
            password: this.form.password,
            rePassword: this.form.rePassword
          }).then(response => {
            this.$message({
              message: '密码修改成功',
              type: 'success'
            })
            //退出登录，该操作是个异步操作，所以后面跳转到登录页面延迟1s再执行（如果有更好的方法再调整）
            this.$store.dispatch('user/logout')
            const self = this
            setTimeout(function(){
              self.$router.push(`/login`)
            },1000)

          }).catch((err) => {
          })
        } else {
          return false
        }
      })
    }


  }
}
