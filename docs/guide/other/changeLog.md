# change log
## 3.0
[gitee](https://gitee.com/enilu/web-flash/releases/3.0)
[github](https://github.com/enilu/web-flash/releases/tag/3.0)

- Issues
- Issue 适配flash-vue3-admin；从3.0开始，api和前端项目将进行分离。
- Issue 打包时默认跳过测试
- Issue 升级mysql驱动包到版本:8.0.28
- Change 升级Logback
- Change 升级shiro
- Change 默认的jdk编译版本升级到11
- Issue 移除冗余的maven依赖
- Issue 工作流的待办任务中增加任务进度图

## 2.2
[gitee](https://gitee.com/enilu/web-flash/releases/2.2)
[github](https://github.com/enilu/web-flash/releases/tag/2.2)
### Issues
- Issue 增加扫码登陆功能
- Issue 使用aes加密算法对登录密码加密后传送
- Change 将角色编码字段名从之前得tips调整为code
- Change 统一调整新增和修改接口的传参方式为:Request Payload
- Change 限制对初始化数据的删除
## 2.1
[gitee](https://gitee.com/enilu/web-flash/releases/2.1)
[github](https://github.com/enilu/web-flash/releases/tag/2.1)
### Issues
- Issue 启用keep-Alive保存标签页状态
- Change 路由模式由默认的hash调整为history
- Change 去掉maven冗余的依赖
- Change 完善单元测试
- Issue 集成activiti实现基本的工作流功能
- Change 完善代码生成器中controller生成模板中关于权限配置部分
### Fixes
- Fix #I3Z1QT 后端启动后，API接口访问不到后端，中途权限被拦截
- Fix #I271AB token验证失败后，弹出过多token过期提示
- Fix 初始化sql语句中菜单资源地址重复的问题

## 2.0

[gitee](https://gitee.com/enilu/web-flash/releases/2.0)
[github](https://github.com/enilu/web-flash/releases/tag/2.0)
### Issues
- Change 完善消息发送服务功能
- Change 完善参数管理功能细节
- Change 完善账号管理逻辑
### Fixes
- Fix 参数管理中参数值长度校验问题
- Fix 自动生成hibernate_sequence的问题
- Fix 包装类命名单词错误


## 1.9

[gitee](https://gitee.com/enilu/web-flash/releases/1.9)
[github](https://github.com/enilu/web-flash/releases/tag/1.9)
### Issues
- Issue 用户管理中增加重置用户密码功能
- Issue 优化个人中心功能
- Issue 提供手动生成代码的方式:cn.enilu.flash.CodeGenerator
- Issue 升级Spring Boot从2.1.1.RELEASE到2.3.11.RELEASE
- Issue 升级Spring Framework从5.1.3.RELEASE到5.2.12.RELEASE
- Issue 升级Swagger从2.2.2到3.0.0
- Issue 配置文件从properties形式转换为yml形式
- Issue 简化Controller请求类型配置
- Issue 添加事务管理器

## 1.8

[gitee](https://gitee.com/enilu/web-flash/releases/1.8)
[github](https://github.com/enilu/web-flash/releases/tag/1.8)
### Issues
- Change 重构BaseRepository部分方法
- Change 优化获取用户菜单数据的方法
- Issue 优化JsonUtil性能（ObjectMapper采用单例模式)
- Issue 完善系统基础表的列定义和注释
- Issue 添加阿里云短信服务模块
- Change 重构短信发送模块，将远程模板编号从messageSender迁移至messageTemplate
- Issue DictSelect组件调整为双向绑定
- Issue cms模块文件列表增加图片预览功能
- Issue 禁止冻结初始账户
- Issue 提交定时任务时对相关参数有效性进行校验
### Fixes
- Fix 文件下载过程文件损坏
- Fix 用户锁定和删除状态可以登录的问题
- Fix 新增菜单时候候选页面不显示菜单图标

## 1.7

[gitee](https://gitee.com/enilu/web-flash/releases/1.7)
[github](https://github.com/enilu/web-flash/releases/tag/1.7)

### Issues
- Issue前端request.js请求默认配置：withCredentials: false,(仅作开发使用，不建议生产使用该配置)
- Issue 优化菜单维护功能
- Issue 优化页面标题显示（从之前的英文调整为中文）
- Issue 顶部工具栏添加文档地址，快速入口搜索框
- Issue 页面标题显示中文代替之前的英文
- Issue 定义DictSelect 字典下拉组件
- Issue 映入vue-treeselect组件代替之前的input+el-tree的用法
- Issue 菜单编辑窗口使用图标选择组件输入菜单图标
- Issue 完善权限管理功能
### Fixes
- Fix 修复登陆页面标题显示错误问题
- Fix 编辑菜单默认的父菜单没有选中
- Fix 编辑功能使用深拷贝表格记录，代替之前的直接引用
- Fix 完善用户编辑过程对用户字段的校验
- Fix 去掉menu中无用的status字段
- Fix 菜单编辑过程针对菜单类型的切换（按钮/菜单）过程，隐藏掉不需要填写的字段编辑框

## 1.6

[gitee](https://gitee.com/enilu/web-flash/releases/1.6)
[github](https://github.com/enilu/web-flash/releases/tag/1.6)
### Issues
- Issue 各个功能的列表页面完善查询条件
- Issue 各个功能的列表页面增加操作列：包含修改，删除等操作按钮
- Issue 完善功能按钮的权限控制
- Issue 用户管理页面增加部门树快速定位用户
- Issue 重构记录业务日志功能
- Issue 代码生成功能添加生成列表页的操作列，查询条件
- Issue 菜单列表显示菜单图标
- Issue 封装JsonUtil使用jackson代替nutz的json工具类
- Issue #I1JYAR 移除fastjson，使用jackson代替
- Change 调整登录相关验证
- Issue 完善查询条件为(not)null的代码封装

### Fixes
- Fix #65 语言切换bug
- Fix 处理延迟加载导致对象转换为json异常的问题
- Fix 修正初始化数据
- Fix #58 新建用户不配置角色时登录出错
- Fix MD5在高并发环境生成md5值错误
- Fix 生成代码时 js文件没有和index.vue文件同级目录问题


## 1.5
[gitee](https://gitee.com/enilu/web-flash/releases/1.5)
[github](https://github.com/enilu/web-flash/releases/tag/1.5)
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
- Issue echarts随着窗口缩放自适应

### Fixes
- Fix 业务日志中如果获取不到字段值对应的中文名称则返回字段名本身，代替之前的null
- Fix 超级管理员判断错误
- Fix SearchFilter构建查询条件中的问题 bug
- Fix 更新缓存的时候连带更新常量工具类中使用的本地(TimeCacheMap)缓存

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
