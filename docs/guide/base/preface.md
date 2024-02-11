---
pageClass: getting-started
---
# 介绍
[![Spring Boot](https://img.shields.io/badge/spring--boot-2.3.11.RELEASE-brightgreen)](https://github.com/spring-projects/spring-boot)
[![vue](https://img.shields.io/badge/vue-2.6.10-brightgreen.svg)](https://github.com/vuejs/vue)
[![Node.js](https://img.shields.io/badge/node--js-14.15.0-brightgreen.svg)](https://nodejs.org/zh-cn)
[![element-ui](https://img.shields.io/badge/element--ui-2.12.0-brightgreen.svg)](https://github.com/ElemeFE/element)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/enilu/web-flash/blob/master/LICENSE)
[![GitHub release](https://img.shields.io/github/release/enilu/web-flash.svg)](https://github.com/enilu/web-flash/releases)
[![donate](https://img.shields.io/badge/%24-donate-ff69b4.svg)](./docs/donate.md)
[![Gitee stars](https://gitee.com/enilu/web-flash/badge/star.svg?theme=social)](https://gitee.com/enilu/web-flash)
[![GitHub stars](https://img.shields.io/github/stars/enilu/web-flash.svg?style=social&label=Stars)](https://github.com/enilu/web-flash)
[![Join the chat at https://gitter.im/web-flash/community](https://badges.gitter.im/web-flash/community.svg)](https://gitter.im/web-flash/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


本文档以向导的方式引导用户使用web-flash系统做二次开发。
web-flash项目本身有readme文件，如果你有丰富的Spring Boot的和Vue.Js的开发经验，那么基本上你是用不上本文档了，
有什么问题直接看代码即可。大多数功能都可以参考代码，即使找不到的google和百度也能帮到你。

但是考虑到有的开发者可能初次使用上述框架技术，有的甚至刚接触java不久，那么本文当将引导你一步步使用本系统搭建一个后台管理系统，并在此基础上做二次开发。

**另** web-flash提供了基于vue3的前端系统，[点击查看](https://gitee.com/enilu/flash-vue3-admin)

## 项目说明
- web-flash提供了一个后台管理系统和前端h5站点系统
- web-flash是一个基于[Spring Boot](https://spring.io/projects/spring-boot/)和[Vue.js](https://cn.vuejs.org)的web系统，包含了基于[element](https://element.eleme.cn/#/zh-CN)搭建的后台管理系统和基于[vux](https://vux.li)搭建的手机端h5站点
- web-flash具备后台管理类系统的通用的基础功能，而且提供了基于idea intellij的的代码生成插件，可以一键生成后台代码（service,dao,controller)和管理系统（flash-vue-admin)的前端界面。

## 项目目录
- flash-api 后台api服务
- flash-core 基础模块，包括工具类，dao，service，bean等内容
- flash-generator 代码生成模块，IDEA插件需要配合该模块进行代码生成，如果在代码生成方面你有什么特殊需求，可以直接调整该模块
- flash-workflow 基于activiti实现了工作流功能
- flash-vue-admin 基于Vue.js的前端界面
- flash-vue-h5 移动端内容系统，基于CMS内容管理模块的内容进行展示。

## 演示
- 后台管理(vue版本) [http://flashadmin.enilu.cn](http://flashadmin.enilu.cn)
- 手机端 [http://flash-mobile.enilu.cn/#/index](http://flash-mobile.enilu.cn/#/index) 打开浏览器后使用debug模式的手机视图模式浏览
- **建议** 开发之前先将上面两个演示系统的功能查看一遍，了解系统能做什么，不能做什么。以便后续开发有的放矢。**演示系统为方便演示给出的权限比较大。使用过程中请请温柔操作，不要随意删改关键数据。**


## 技术选型
- 核心框架：Spring Boot 2
- 数据库层：Spring data jpa
- 权限认证：Shiro+Jwt
- 数据库连接池：Druid
- 缓存：Ehcache
- 前端：基于Vue.js的element和vux


## 项目功能
web-flash包含了成熟的后台管理功能和手机端h5内容站点系统

- 用户管理
- 角色管理
- 部门管理
- 菜单管理
- 字典管理
- 参数管理
- 定时任务管理
- 业务日志
- 登录日志
- cms内容管理
  - 栏目管理
  - 文章管理
  - banner管理
  - 邀约管理
  - 文件管理
- 消息管理
  - 消息模板
  - 消息发送器
  - 历史消息
- 工作流
  - 流程定义
  - 发起申请
  - 待办任务

## 使用

- 克隆本项目
- 导入idea或者eclipse
- 创建数据库：webflash
- 在开发环境中配置了系统启动后自动创建数据库和初始化数据，所以不需要开发人员手动初始化数据库
- 确保开发工具下载了[lombok](https://www.zhihu.com/question/42348457)插件
- 修改flash-api中数据库连接配置
- 启动flash-api，访问http://localhost:8082/swagger-ui.html，保证api服务启动成功
- 进入flash-vue-admin目录
    - 运行 npm install --registry=https://registry.npmmirror.com 安装依赖
    - 运行npm run dev 启动项目
    - 启动成功后访问 http://localhost:9528,登录，用户名密码:admin/admin


 ![admin](../../vuejs.gif)

- 进入flash-vue-h5目录
    - 运行 npm install --registry=https://registry.npmmirror.com
    - 运行npm run dev
    - 启动成功后访问 http://localhost:8088/#/index
- 手机端运行效果图：

    ![mobile](../../flash-mobile.gif)

## 测试
```
mvn package -DisSkipTest=false
```
## 打包
```
mvn package 
```
## install
**如果使用代码生成功能，需要首先运行下mvn install 将 flash-generator模块安装到本地maven仓库**
```
mvn install
```

## 交流
- Bugs: [Issues](https://github.com/enilu/web-flash/issues/new)
- Gitter: [Gitter channel](https://gitter.im/web-flash/community)
- QQ:752844606


也欢迎你给该项目点个赞：
- Github:[https://github.com/enilu/web-flash](https://github.com/enilu/web-flash)
- Gitee: [https://gitee.com/enilu/web-flash](https://gitee.com/enilu/web-flash)
