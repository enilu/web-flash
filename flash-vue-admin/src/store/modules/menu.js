import { getList } from '@/api/menu'
import router from '@/router'
import { traverseRoutes } from '@/utils/route'
import { constantRoutes } from '@/router'
const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {

  getSideMenus({ commit, state }) {
    return new Promise((resolve, reject) => {
      getList().then(response => {
        const menus = response.data
        // let menus = [{"path":"/product","component":"layout","children":[{"path":"index","name":"product","component":"views/product/index","meta":{"title":"Product","icon":"form"}}]},{"path":"*","redirect":"/404","hidden":true}]
        let remoteroutes = traverseRoutes(menus)
        console.log('remoteroutes',remoteroutes)
        commit('SET_ROUTES',remoteroutes);
        resolve(remoteroutes);
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
