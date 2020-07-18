import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import ECharts from 'vue-echarts/components/ECharts.vue'
import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import i18n from './lang' // Internationalization
import '@/icons' // icon
import '@/permission' // permission control

/**
 * 全局引用自定义公共组件
 */
import DictSelect from './components/DictSelect'
Vue.component('dict-select',DictSelect)
/**
 * 全局引用第三方组件
 */
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
Vue.component('treeselect',Treeselect)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

// set ElementUI lang to EN
Vue.use(ElementUI, {   i18n: (key, value) => i18n.t(key, value) })

Vue.component('v-chart', ECharts)
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
