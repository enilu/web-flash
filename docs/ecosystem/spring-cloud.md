# web-flash(spring cloud版)
该项目在[web-flash](http://webflash-enilu.cn)项目的基础上，将其拆分为微服务版本
- 该项目在web-flash基础上将其后端拆分成基于Spring Cloud的微服务系统
- 该项目不包含前端项目代码，可以直接使用web-flash的前端项目(flash-vue-admin,flash-vue-h5)
- 使用Eureka作为注册中心
- 使用基于git仓库的Config配置中心
- 使用集成了Ribbon（负载均衡和Hystrix（熔断，降级）的OpenFeign负责服务之间的调用

## 项目地址
- gitee [https://gitee.com/enilu/web-flash-spring-cloud](https://gitee.com/enilu/web-flash-spring-cloud)
- github[https://github.com/enilu/web-flash-spring-cloud](https://github.com/enilu/web-flash-spring-cloud)

## 选用技术
- Spring Cloud
- Spring Cloud Config
- Eureka
- Ribbon
- Hystrix
- Open Feign
## 模块
- eureka-server 服务注册中心
- flash-common 基础公共模块
- flash-config-server 配置中心（基于git仓库）
- flash-manage 后台管理api
- flash-message 消息服务
- flash-schedule 定时任务
- flash-vue-admin (后台管理前端项目，该模块已删除，请直接使用[web-flash项目的flash-vue-admin模块]作为后台管理页面项目)
- flash-vue-h5  (用户端前端项目，该模块已删除，请直接使用[web-flash项目的flash-vue-h5模块]作为用户端h5页面项目)
## 使用
- 克隆本项目
- 导入idea或者eclipse
- 创建数据库：webflashsc(无需初始化表和数据)
     ```sql
        CREATE DATABASE IF NOT EXISTS webflashsc DEFAULT CHARSET utf8 COLLATE utf8_general_ci; 
        CREATE USER 'webflashsc'@'%' IDENTIFIED BY 'webflash190602@ABC';
        GRANT ALL privileges ON webflashsc.* TO 'webflashsc'@'%';
        flush privileges;
        ```    
- 确保开发工具下载了lombok插件
- 按照顺序启动一下各个微服务
    - eureka-server
    - flash-config-server:启动之前修改配置文件中配置文件使用的git仓库地址和账号密码
    - flash-message
    - flash-schedule
    - flash-manage 启动后会自动创建数据库表和初始化数据
- 进入flash-vue-admin目录
    - 运行 npm install --registry=https://registry.npmmirror.com
    - 运行npm run dev
    - 启动成功后访问 http://localhost:9528 ,登录，用户名密码:admin/admin      
 