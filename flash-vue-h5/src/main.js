// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Icon from 'vue-svg-icon/Icon.vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import router from './router/router'
import store from './store/store'
import { ConfirmPlugin,BusPlugin } from 'vux'

Vue.use(VueRouter)
Vue.use(ConfirmPlugin)
Vue.use(BusPlugin)
Vue.component('icon', Icon)
FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app-box')

