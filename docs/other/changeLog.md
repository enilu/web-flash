# change log

## latest
### Issues 
- Issue 压缩svg图标文件
- Issue 完善权限管理
- Issue 修改管理员密码后自动退出登录
- Issue 添加关联查询示范用法
- Issue 升级lombok从1.16.20到1.18.6
- Issue 支持or查询
- Issue 添加单元测试支持
- Issue BaseRepository封装根据sql查询数据返回Map或List方法
- Issue BaseRepository封装根据sql查询返回指定对象（列表）方法
- Change BaseRepository 去掉根据sql查询返回Object数组的方法 

### Fixes
- Fix 业务日志中如果获取不到字段值对应的中文名称则返回字段名本身，代替之前的null
- Fix 超级管理员判断错误
- Fix SearchFilter构建查询条件中的问题 bug

## 1.4
[gitee](https://gitee.com/enilu/web-flash/releases/1.4)
[github](https://github.com/enilu/web-flash/releases/tag/1.4)

- Issue Token过期刷新
- Fix npm run dev之后，会启动两次，浏览器打开两个相同的后台项目
- Fix 菜单修改后创建人和创建时间为空的问题
- Fix 权限拦截器中配置front目录下请求地址不拦截
- Fix 返回数据success状态逻辑错误
- Fix 权限FilterChainDefinitionManager试用LinkedHashMap代替HashMap避免anon配置可能无效的情况
- Chane 完善文档
- Change 表单样式
- Change 业务类使用CacheDao代替使用EhcacheDao
- Add MIT license文件

## 1.3
[gitee](https://gitee.com/enilu/web-flash/releases/1.3)
[github](https://github.com/enilu/web-flash/releases/tag/1.3)
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
[gitee](https://gitee.com/enilu/web-flash/releases/1.2)
[github](https://github.com/enilu/web-flash/releases/tag/1.2)

- Change 删除角色时判断是有用户使用该角色，如果有则不允许删除
- Change 简化手机站点api地址配置
- Change baseService增加缓存功能
- Update 升级element-ui至2.11.0
- Update 升级Vue全家桶：vue.js(2.6.10),vuex(3.1.1),vue-router(3.0.3),axios(0.18.1),"vue-loader(15.7.0),vue-template-compiler(2.6.10)
- Change 完善在线文档
- Fix bug

## 1.1
[github](https://github.com/enilu/web-flash/releases/tag/1.1)
### Issues
- 封装公共的[service](https://github.com/enilu/web-flash/issues/11)和[dao](https://github.com/enilu/web-flash/issues/9)
- [使用shiro和jwt针对后台接口完成权限认证](https://github.com/enilu/web-flash/issues/15)
- 使用Validator实现后台对数据的校验
- 同步更新代码生成功能
### Fixes
- [权限管理中，父子权限级联选择问题](https://github.com/enilu/web-flash/issues/14)
- [import的utils报错：Module is not installed ](https://github.com/enilu/web-flash/issues/6)

## 1.0
[github](https://github.com/enilu/web-flash/releases/tag/v1.0)
### Issues
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
