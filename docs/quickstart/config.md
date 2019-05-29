# 配置项目

你已经下载项目，并且初始化好了数据库，那么接下来只需要更改相应的配置就可以运行该项目了

- 更改src/resources/application-dev.properties配置：

```properties
## 开发环境配置，修改为真实的用户名密码
spring.datasource.url=jdbc:mysql://localhost:3306/web-flash
spring.datasource.username=root
spring.datasource.password=root

```