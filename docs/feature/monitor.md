# 监控管理

这里的监控功能指的的是alibaba druid自带的监控功能
启动flash-api后访问http://localhost:8082/druid
![monitor](./img/monitor.jpg)

更细粒度的监控配置可以在application.properties中配置
```properties

###监控配置 begin###
# WebStatFilter配置，说明请参考Druid Wiki，配置_配置WebStatFilter
spring.datasource.druid.web-stat-filter.enabled=true
spring.datasource.druid.web-stat-filter.url-pattern=/*
spring.datasource.druid.web-stat-filter.exclusions=*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
# StatViewServlet配置，说明请参考Druid Wiki，配置_StatViewServlet配置
spring.datasource.druid.stat-view-servlet.enabled= true
spring.datasource.druid.stat-view-servlet.url-pattern= /druid/*
spring.datasource.druid.stat-view-servlet.reset-enable= false
spring.datasource.druid.stat-view-servlet.login-username= druiduser
spring.datasource.druid.stat-view-servlet.login-password= druidpassword
spring.datasource.druid.stat-view-servlet.allow= 127.0.0.1
###监控配置 end###
```

druid配置的官方文档[https://github.com/alibaba/druid/wiki](https://github.com/alibaba/druid/wiki)
