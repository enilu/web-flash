/**
 * Created by superman on 17/2/16.
 * vuex types
 */
// 公共
export const COM_NAV_STATUS = 'COM_NAV_STATUS'
export const COM_HEADER_STATUS = 'COM_HEADER_STATUS'
export const COM_LOADING_STATUS = 'COM_LOADING_STATUS'

export const COM_SHOW_TOAST = 'COM_SHOW_TOAST'              //显示toast
export const COM_SHOW_SUCCESS = 'COM_SHOW_SUCCESS'          //显示成功toast
export const COM_SHOW_FAIL = 'COM_SHOW_FAIL'                //显示失败toast
export const COM_TOAST_MSG = 'COM_TOAST_MSG'                //显示toast文字
export const COM_SHOW_ALERT = 'COM_SHOW_ALERT'
export const COM_ALERT_MSG = 'COM_ALERT_MSG'
export const COM_SHOW_TIME_PICKER = 'COM_SHOW_TIME_PICKER'  //显示timepicker

export const LOGIN = 'login'

export const LOGOUT = 'logout'

export const TITLE = 'title'

export const APP_KEY = 'ABDFEADF'
export const PRODUCT_BBS = 'BBS'
export const PRODUCT_OFFICIAL_SITE = 'OFFICIAL_SITE'
export const PRODUCT = PRODUCT_OFFICIAL_SITE
export const MENU_GROUP_DATA = [
  { link: '/news', name: '动态资讯', icon: 'micro-news', class: 'one' },
  { link: '/product', name: '产品服务', icon: 'micro-product', class: 'two' },
  { link: '/solution', name: '解决方案', icon: 'micro-solution', class: 'there' },
  { link: '/case', name: '精选案例', icon: 'micro-case', class: 'four' },
  { link: '/contact', name: '立即咨询', icon: 'advisory', class: 'five' }
]
export const MENU_DATA_BBS = [
  { link: '/index', name: '首页', icon: '../../../static/images/menu/home.png' },
  { link: '/message', name: '消息', icon: '../../../static/images/menu/notify.png' },
  { link: '/more', name: '关于', icon: '../../../static/images/menu/setting.png' }
]

export const MENU_DATA_OFFICIAL_SITE = [
  { link: '/index', name: '首页', icon: 'static/images/menu/home.png' },
  { link: '/news', name: '资讯', icon: 'static/images/menu/notify.png' },
  { link: '/contact', name: '联系', icon: 'static/images/menu/contact.png' },
  { link: '/more', name: '更多', icon: 'static/images/menu/about.png' }
]
