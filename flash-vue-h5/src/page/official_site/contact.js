import {XHeader, XInput, Group, XTextarea, XButton, AlertModule} from 'vux'
import footMenu from '../../components/footer/footMenu'
import api from '../../fetch/api'

export default {
  components: {
    XHeader, XInput, Group, XTextarea, XButton, footMenu, AlertModule
  },
  data () {
    return {
      form: {
        userName: '',
        mobile: '',
        email: '',
        remark: ''
      }
    }
  },
  methods: {
    submit () {
      if (this.valid()) {
        api.saveContact(this.form).then(res => {
          AlertModule.show({content: '提交成功'})
          console.log(res)
        })
      } else {
        AlertModule.show({content: '请填写完整信息'})
      }
    },
    valid () {
      if (this.form.username === '') {
        return false
      }

      if (this.form.mobile === '') {
        return false
      }

      if (this.form.email === '') {
        return false
      }

      if (this.form.description === '') {
        return false
      }
      return true
    }
  }
}
