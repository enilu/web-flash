# 开发和部署

## 本地开发
- flash-api默认使用8082端口，所以启动后将提供http://localhost:8082接口服务,可以通过 访问http://localhost:8082/swagger-ui.html验证api服务是否成功启动
- flash-vue-admin项目中的vue.config.js配置了开发环境的api地址,如果更改api的端口，则需要配合调整
```javascript
 target:`http://localhost:8082`
```
- 用户通过浏览器debug看到的api地址是http://localhost:9528/dev-api，但是实际上会被proxy代理到http://localhost:8082



## 生产部署

- 运行：npm run build:prod可以构建出生产环境的前端部署文件
- 前端链接的api服务地址为前端地址+prod-api，
```
以本项目演示环境为例,前端地址为：http://flashadmin.enilu.cn
那么请求的api地址为:http://flashadmin.enilu.cn/prod-api
可以通过访问http://flashadmin.enilu.cn/prod-api/swagger-ui.html来检测生产环境的api服务是否正常
```

- 用户通过浏览器debug看到的api地址是http://flashadmin.enilu.cn/prod-api/，但是实际上会被代理软件（建议使用nginx)转发到实际的api地址


下面给出本人的nginx配置供参考：
```
server {
    listen       80;
    server_name  flashadmin.enilu.cn;

    access_log  /data/app/runtime/log/nginx/flashadmin.access.log  main;
    
    location /{
                alias /opt/web-flash/admin/;
                index index.html index.htm;
    }
  location /prod-api/{
                proxy_ignore_client_abort on;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_read_timeout 600s;

                proxy_pass      http://localhost:8080/api/;
    }

    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

}

```

### api服务打包
系统默认提供了打包为war以便部署到web容器（例如tomcat）的配置
flash-api/pom.xml
```xml
<!--以war形式运行 配置开始-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <configuration>
        <warName>api</warName>
        <failOnMissingWebXml>false</failOnMissingWebXml>
    </configuration>
</plugin>
<!-- 以war形式运行 配置结束-->
```

如果你想打包成jar，直接使用jar命令运行该api服务，可以将使用下面配置替换掉上面
```xml
<!--以jar包形式单独部署 配置开始-->
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <mainClass>cn.enilu.flash.api.ApiApplication</mainClass>
        <layout>ZIP</layout>
        <finalName>flash-api</finalName>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>
<!--以jar包形式单独部署 配置结束-->
```
