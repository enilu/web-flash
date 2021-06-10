
import { XHeader, Group, XButton, Box, Cell, AlertModule, Confirm } from 'vux'
import footMenu from '../../components/footer/footMenu'
import profile from '../../components/profile/profile'
import api from '../../fetch/api'
import * as types from '../../store/types'

export default {
  components: {
    XHeader,
    Group,
    XButton,
    Box,
    Cell,
    footMenu,
    profile,
    Confirm,
    AlertModule
  },
  data() {
    return {
      menus: {
        menu1: 'Take Photo',
        menu2: 'Choose from photos'
      },
      showMenus: false,
      phoneNo: '15311076360'
    }
  },
  methods: {
    logout() {
      var _this = this
      this.$vux.confirm.show({
        content: '您确定要退出登录吗？',
        onConfirm() {
          api.Logout().then(res => {
            _this.$store.commit(types.LOGOUT)
            _this.$router.push({ path: '/login' })
          }).catch(err => {
            AlertModule.show({
              title: '',
              content: err.data.msg
            })
          })

        }
      })
    },
    phoneCall() {
      window.location.href = 'tel://' + this.phoneNo
    },
    openDoc() {
      window.location.href = 'http://webflash.enilu.cn'
    }
  }
}
