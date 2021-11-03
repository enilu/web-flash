# 基本包结构

本节详细说明本项目的基本目录结构

## web-flash模块

web-flash包含5个核心模块：
- **flash-api** 后台api服务,为vue-admin和vue-h5提供后台数据接口。**个人小建议**：如果前后端项目不复杂，可以前后端项目共用这么一个api服务，如果业务比较复杂，建议前后端的api服务分拆开来：后台管理一个api服务，手机端系统一个api服务。
- **flash-vue-admin** 基于Vue.js的后台管理的界面系统
    - 该模块克隆自：[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)，感谢PanJiaChen，欢迎大家给他点赞。
    - PanJiaChen老师提供了全面详细的文档，开发过程可以参考这里：[vue-elment-admin官方文档](https://panjiachen.github.io/vue-element-admin-site/zh/)


- **flash-vue-h5** 移动端内容系统，基于后台管理CMS内容管理模块的内容进行展示。基于[vux](https://vux.li)开发
- **flash-core** 基础模块，包括工具类，dao，service，bean等内容
- **flash-generator** 代码生成模块，配合IDEA Intellij的代码生成插件：webflash-generator可以根据实体类一键生成前后端代码。方便快速开发
- **flash-workflow** 工作流模块，基于activit集成基础的工作流功能
