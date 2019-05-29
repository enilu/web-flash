import { updatePwd } from '@/api/login'

export default {
  data() {
    return {
      form: {
        oldPassword: '',
        password: '',
        rePassword: ''
      }
    }
  },

  computed: {
    rules() {
      return {
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 5, max: 100, message: '密码长度不能小于5', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    updatePwd() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updatePwd({
            oldPassword: this.form.oldPassword,
            password: this.form.password,
            rePassword: this.form.rePassword
          }).then(response => {
            console.log(response)
            this.$message({
              message: '密码修改成功',
              type: 'success'
            })
            this.$store.dispatch('LogOut').then(() => {
              location.reload() // 为了重新实例化vue-router对象 避免bug
            })
          }).catch((err) => {
            this.$message({
              message: err,
              type: 'error'
            })
          })
        } else {
          return false
        }
      })
    }

  }
}
