import { listForRouter } from '@/api/system/menu'
import { traverseRoutes } from '@/utils/route'
import { constantRoutes } from '@/router'
import router from '@/router'
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
      listForRouter().then(response => {
        const menus = response.data
        let remoteroutes = traverseRoutes(menus)
        commit('SET_ROUTES',remoteroutes);
        resolve(remoteroutes);
      }).catch(error => {
        console.log('list',error)
        router.replace({
          path: '/login',
          query:{redirect:router.currentRoute.path}
        })
        // reject(error)
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
