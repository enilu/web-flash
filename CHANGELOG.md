## latest
- Fix bug 权限拦截器中配置front目录下请求地址不拦截
- Fix bug 返回数据success状态逻辑错误
## 1.3
- Add 页面导航增加多标签页的方式
- Add 左侧菜单完全通过后台配置动态生成
- Add 基于vue-cli3构建脚手架，更精简合理的配置方式
- Change 优化页面样式
- Change 完善密码修改功能
- Change 完善退出登录功能
- Change 查询in条件中无法使用数组作为参数的问题
- Fix 保存菜单出现异常：Converting circular structure to JSON
- Fix 权限编码配置错误的问题


## 1.2 

- Change 删除角色时判断是有用户使用该角色，如果有则不允许删除
- Change 简化手机站点api地址配置
- Change baseService增加缓存功能
- Update 升级element-ui至2.11.0
- Update 升级Vue全家桶：vue.js(2.6.10),vuex(3.1.1),vue-router(3.0.3),axios(0.18.1),"vue-loader(15.7.0),vue-template-compiler(2.6.10)
- Change 完善在线文档
- Fix bug

## 1.1
### Features
- 封装公共的[service](https://github.com/enilu/web-flash/issues/11)和[dao](https://github.com/enilu/web-flash/issues/9)
- [使用shiro和jwt针对后台接口完成权限认证](https://github.com/enilu/web-flash/issues/15)
- 使用Validator实现后台对数据的校验
- 同步更新代码生成功能
### Bug Fixes
- [权限管理中，父子权限级联选择问题](https://github.com/enilu/web-flash/issues/14)
- [import的utils报错：Module is not installed ](https://github.com/enilu/web-flash/issues/6)

## 1.0
### Features
- add 部门管理
- add 用户管理
- add 角色管理
- add 菜单管理
- add 权限分配
- add 参数管理
- add 数据字典管理
- add 定时任务管理
- add 业务日志
- add 登录日志
- add cms内容管理
- add 消息管理
- add 基于idea插件的代码生成功能
- 手机端h5站点系统
