
import { asyncRouterMap, constantRouterMap } from '@/router'


/**
 * 通过route.path判断用户是否有对改菜单的操作权限
 * @param roles
 * @param route
 */
function hasMenu(menus, route) {
  if (route.path) {
    return menus.some(menu => (menu[4].indexOf(route.path) >= 0) )
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回后台菜单列表包含的路由表
 * @param asyncRouterMap
 * @param menus
 * @returns {*}
 */
function filterAsyncRouterByMenu(asyncRouterMap, menus) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasMenu(menus, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouterByMenu(route.children, menus)
      }
      return true
    }
    console.log(route.path)
    return false
  })
  return accessedRouters
}
/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.indexOf(role) >= 0)
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param roles
 */
function filterAsyncRouter(asyncRouterMap, roles) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasPermission(roles, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
  return accessedRouters
}


const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const roles = data.roles
        const routers = data.routers
        const menus = data.menus
        let accessedRouters = null
        if (roles.indexOf('admin') >= 0) {
          accessedRouters = asyncRouterMap
        } else {
          accessedRouters = filterAsyncRouterByMenu(asyncRouterMap, menus)
        }
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
