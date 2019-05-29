# MicroBBS

## 切换主题

- 修改webpack.base.conf.js中的less-theme属性
## 切换产品

- 修改typs.js中MENU_DATA值
- 调整router/router.js的内容为对应产品的js中内容
- 每个产品的预定页面是固定的，用户可以通过前端配置调整types.js中的MENU_DATA值来修改页面菜单已达到修改页面个数效果

## 使用iconft中svg资源的方法
- 运行 npm install vue-svg-icon --save-dev
- 在入口文件main.js引入
```
import Icon from 'vue-svg-icon/Icon.vue'
Vue.component('icon', Icon)
```
- svg资源放到src/svg目录下
- 在页面引用
```
<icon name="wechat" scale="20"></icon>
```
> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install
or 
npm install --registry=https://registry.npm.taobao.org

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
