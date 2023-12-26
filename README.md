# 前言
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

- web-flash是一个基于[Spring Boot](https://spring.io/projects/spring-boot/)和[Vue.js](https://cn.vuejs.org)的web系统，包含了基于[element](https://element.eleme.cn/#/zh-CN)搭建的后台管理系统和基于[vux](https://vux.li)搭建的手机端h5站点
- web-flash具备后台管理类系统的通用的基础功能，而且提供了基于idea intellij的的代码生成插件，可以一键生成前后端页面。
- web-flash提供了一个后台管理系统和前端h5站点系统
- 另提供Material Design风格的单体版：[material-admin](https://github.com/enilu/material-admin)
- 另提供[web-flash spring cloud版本](https://gitee.com/enilu/web-flash-spring-cloud)
- **另** web-flash提供了基于vue3的前端系统，[点击查看](https://gitee.com/enilu/flash-vue3-admin)
## 目录说明
- flash-api 后台api服务
- flash-vue-admin 基于vuejs的后台管理系统
- flash-vue-h5 移动端内容系统，基于后台管理CMS内容管理模块的内容进行展示。
- flash-core 基础模块，包括工具类，dao，service，bean等内容
- flash-generator 代码生成模块,配合IDEA 代码生成插件[webflash-generator](https://plugins.jetbrains.com/plugin/12648-webflash-generator)使用效果更好
- flash-workflow 基于activiti实现工作流功能
## 演示
- 后台管理(vue版本) [http://flashadmin.enilu.cn](http://flashadmin.enilu.cn)
- 手机端 [http://flash-mobile.enilu.cn/#/index](http://flash-mobile.enilu.cn/#/index) 打开浏览器后使用debug模式的手机视图模式浏览

## 技术选型
- 核心框架：Spring Boot
- 数据库层：Spring data jpa
- 数据库连接池：Druid
- 缓存：Ehcache
- 前端：基于Vue.js的Element（后端）和vux（手机端） 
- 工作流：activiti


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
- 工作流：流程定义，发起申请，待办任务
- 基于idea插件的代码生成


## 使用
- 克隆本项目
- 导入idea或者eclipse
- 创建数据库：web-flash
     ```sql
        CREATE DATABASE IF NOT EXISTS webflash DEFAULT CHARSET utf8 COLLATE utf8_general_ci; 
        CREATE USER 'webflash'@'%' IDENTIFIED BY 'webflash190602@ABC';
        GRANT ALL privileges ON webflash.* TO 'webflash'@'%';
        flush privileges;
     ```
- 在开发环境中配置了系统启动后自动创建数据库和初始化数据，所以不需要开发人员手动初始化数据库
- 确保开发工具下载了lombok插件
- 修改flash-api中数据库连接配置
- 启动flash-api，访问http://localhost:8082/swagger-ui.html ， 保证api服务启动成功
- 进入flash-vue-admin目录
    - 运行 npm install --registry=https://registry.npmmirror.com
    - 运行npm run dev
    - 启动成功后访问 http://localhost:9528 ,登录，用户名密码:admin/admin     
- 后台管理运行效果图：
    ![admin](https://gitee.com/enilu/web-flash/raw/master/docs/vuejs.gif)
- 进入flash-vue-h5目录
    - 运行 npm install --registry=https://registry.npmmirror.com
    - 运行npm run dev
    - 启动成功后访问 http://localhost:8088/#/index
- 手机端运行效果图：
    ![mobile](https://gitee.com/enilu/web-flash/raw/master/docs/flash-mobile.gif)

## 打包
```
mvn package 
```
## install
**如果使用代码生成功能，需要首先运行下mvn install 将 flash-generator模块安装到本地maven仓库**
```
mvn install
```
## 在线文档
- [http://webflash.enilu.cn](http://webflash.enilu.cn)

## 交流
- Gitter: [Gitter channel](https://gitter.im/web-flash/community)
- QQ:752844606

[Change Log](http://enilu.gitee.io/web-flash/other/changeLog.html)
