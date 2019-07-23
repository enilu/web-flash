# 前言
本文档以向导的方式引导用户使用web-flash系统做二次开发，
web-flash项目本身有readme文件，如果你有丰富的spring boot的和vuejs的开发经验，那么基本上你是用不上本文档了，
有什么问题直接看代码即可。大多数功能都可以参考代码，即使找不到的google和百度也能帮到你。

但是考虑到有的开发者可能初次使用上述组件，有的甚至刚接触java不久，那么本文当将引导你一步步使用本系统搭建一个后台管理系统，并在此基础上做二次开发。

 
## 版本说明
- web-flash提供了一个后台管理系统和前端h5站点系统
- web-flash是一个基于[Spring Boot](https://spring.io/projects/spring-boot/)和[Vue.js](https://cn.vuejs.org)的web系统，包含了基于[element](https://element.eleme.cn/#/zh-CN)搭建的后台管理系统和基于[vux](https://vux.li)搭建的手机端h5站点
- web-flash具备后台管理类系统的通用的基础功能，而且提供了基于idea intellij的的代码生成插件，可以一键生成前后端页面。
 
## 目录说明
- flash-api 后台api服务
- flash-vue-admin 基于vuejs的后台管理系统
- flash-vue-h5 移动端内容系统，基于后台管理CMS内容管理模块的内容进行展示。
- flash-core 基础模块，包括工具类，dao，service，bean等内容
- flash-generator 代码生成模块

## 演示
- 后台管理(vue版本) [http://47.104.84.62:8080/vue](http://47.104.84.62:8080/vue)
- 手机端 [http://47.104.84.62:8080/mobile/#/index](http://47.104.84.62:8080/mobile/#/index)

## 技术选型
- 核心框架：Spring Boot
- 数据库层：Spring data jpa
- 安全框架：Shiro
- 数据库连接池：Druid
- 缓存：Ehcache
- 前端：基于Vue.js的element和vux 


## 包含的功能
web-flash包含了成熟的后台管理功能和手机端h5内容站点系统
- 部门管理
- 用户管理
- 角色管理
- 菜单管理：配置菜单功能
- 权限分配：为指定的角色配置特定的功能菜单
- 参数管理：维护系统参数，并缓存系统参数提供高效的读取
- 数据字典管理：配置维护数据字典
- 定时任务管理：编写、配置、执行定时任务
- 业务日志：通过注解的方式记录用户操作日志，并提供日志查询功能
- 登录日志：查看用户登录登出日志
- cms内容管理，配合flash-vue-h5提供了手机端内容展示系统
- 消息管理：配置消息模板，发送短信，邮件消息
- 基于idea插件的代码生成

## 使用

- 克隆本项目
- 导入idea或者eclipse
- 创建数据库：web-flash
- 在开发环境中配置了系统启动后自动创建数据库和初始化数据，所以不需要开发人员手动初始化数据库
- 修改flash-api中数据库连接配置
- 启动flash-api，访问http://localhost:8082/swagger-ui.html，保证api服务启动成功
- 进入flash-vue-admin目录
    - 运行 npm install --registry=https://registry.npm.taobao.org
    - 运行npm run dev
    - 启动成功后访问 http://localhost:9528,登录，用户名密码:admin/admin 
- ![vue](../vuejs.gif)

## 在线文档
- [http://enilu.github.io/web-flash](http://enilu.github.io/web-flash)
- 国内加速版：[http://enilu.gitee.io/web-flash](http://enilu.gitee.io/web-flash)

## 交流
- Bugs: [Issues](https://github.com/enilu/web-flash/issues/new)
- QQ: 欢迎加入qq交流群 752844606
- Gitter: [Gitter channel](https://gitter.im/web-flash/community)

也欢迎你在github给该项目点个赞：[https://github.com/enilu/web-flash](https://github.com/enilu/web-flash)
