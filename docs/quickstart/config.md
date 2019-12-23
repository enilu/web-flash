# 配置项目

你已经下载项目，并且初始化好了数据库，那么接下来只需要更改相应的配置就可以运行该项目了

- 更改src/resources/application-dev.properties配置：

```properties
## 开发环境配置，根据实际情况调整数据库连接信息
spring.datasource.url=jdbc:mysql://localhost:3306/web-flash?useUnicode=true&characterEncoding=UTF8
spring.datasource.username=root
spring.datasource.password=root
## 系统启动自动创建数据库和导入测试语句
spring.jpa.hibernate.ddl-auto=create

```
