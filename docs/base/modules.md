# 基本包结构

本节详细说明本项目的基本目录结构

## web-flash模块

web-flash包含4个核心模块：
- flash-api 后台api服务,为vue-admin和vue-h5提供后台数据接口
- flash-vue-admin 基于Vue.js的后台管理系统
    - 该模块克隆自：[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)，感谢PanJiaChen，欢迎大家给他点赞。
    - PanJiaChen同学提供了全面的文档，开发过程可以参考这里：[vue-elment-admin官方文档](https://panjiachen.github.io/vue-element-admin-site/zh/)

- flash-vue-h5 移动端内容系统，基于后台管理CMS内容管理模块的内容进行展示。基于[vux](https://vux.li)开发
- flash-core 基础模块，包括工具类，dao，service，bean等内容
