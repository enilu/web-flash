/**
 * Created by superman on 17/2/16.
 */
import Vuex from 'vuex'
import Vue from 'vue'
import * as types from './types'
import common from './modules/common'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {},
    token: null,
    title: '',
    appKey: types.APP_KEY
  },
  mutations: {
    [types.LOGIN]: (state, data) => {
      localStorage.token = data.token
      localStorage.account = data.account
      state.token = data.token
      state.user.account = data.account
    },
    [types.LOGOUT]: (state) => {
      localStorage.removeItem('account')
      localStorage.removeItem('token')
      state.token = null
      state.user = {}
    },
    [types.TITLE]: (state, data) => {
      state.title = data
    }
  },
  modules: {
    common
  }
})
