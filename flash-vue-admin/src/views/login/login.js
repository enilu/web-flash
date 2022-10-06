import {isvalidUsername} from '@/utils/validate'
import {getApiUrl} from '@/utils/utils'
import AesUtil  from "@/utils/aes.js"
import {getQrcodeStatus} from '@/api/user'
import LangSelect from '@/components/LangSelect'

export default {
  name: 'login',
  components: {LangSelect},
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error(this.$t('login.errorAccount')))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 2) {
        callback(new Error(this.$t('login.errorPassword')))
      } else {
        callback()
      }
    }
    return {

      qrcode: {
        activeName: 'first',
        showAppdownload: false,
        imgUrl: '',
        uuid: '',
        resultStatus: '',
        msg: '请使用flash-uniapp 扫码登录'
      },
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{required: true, trigger: 'blur', validator: validateUsername}],
        password: [{required: true, trigger: 'blur', validator: validatePassword}]
      },
      loading: false,
      pwdType: 'password',
      redirect: '/'
    }
  },
  mounted() {
    this.init()
    this.getQrcode()

  },
  methods: {
    init() {
      const redirect = this.$route.query.redirect
      if (redirect) {
        this.redirect = redirect
      }

    },
    changeLoginType(tab) {
      if (this.qrcode.activeName === 'second') {

        this.getQrcode()
        this.getQrcodeResult()
      }
    },
    uuid() {
      function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
      }

      return (S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4())
    },
    showAppdownload() {
      this.qrcode.showAppdownload = true
    },
    hideAppdownload() {
      this.qrcode.showAppdownload = false
    },
    getQrcode() {
      this.qrcode.uuid = this.uuid()
      const url = getApiUrl() + '/account/qrcode/generate?uuid=' + this.qrcode.uuid
      console.log('getQrcode', url)
      this.qrcode.imgUrl = url
    },
    refreshQrcode() {
      this.getQrcode()
      this.qrcode.resultStatus = ''
      this.getQrcodeResult()
    },
    getQrcodeResult() {
      if (this.qrcode.activeName === 'second') {
        const me = this
        this.sleep(1000).then(() => {
          getQrcodeStatus({uuid: me.qrcode.uuid}).then(res => {
            this.qrcode.resultStatus = res.data.status
            if (res.data.status === 'invalid') {
              this.qrcode.showAppdownload = false
              me.qrcode.msg = '二维码已过期'
            }
            if (res.data.status === 'success') {
              this.qrcode.showAppdownload = false
              this.qrcode.msg = '扫描成功，自动登录中'
              this.loading = true
              this.$store.dispatch('user/autoLogin', {token: res.data.token}).then(() => {
                this.loading = false
                this.$router.push({path: this.redirect})
              }).catch((err) => {
                this.qrcode.msg = '登录失败'
                this.loading = false
              })
            }
            if (me.qrcode.activeName === 'second' && res.data.status === 'undo') {
              me.getQrcodeResult()
            }
          }).catch((err) => {
            console.log('err', err)
          })
        })
      }
    },
    sleep(time) {
      return new Promise((resolve) => setTimeout(resolve, time))
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      const loginForm = this.loginForm
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          let loginBody = {username: loginForm.username, password: AesUtil.encrypt(loginForm.password)}
          this.$store.dispatch('user/login', loginBody).then(() => {
            this.loading = false
            this.$router.push({path: this.redirect})
          }).catch((err) => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    }
  }
}
