# 常见问题

#### 本地开发正常，打包运行的时候提交中文内容乱码，检查了数据库编码也没问题？

打包为jar包运行的时候可以指定运行时编码为UTF8：
```
java -Dfile.encoding=utf-8 -jar xxxxxxx.jar
```

#### 打包vue-admin或者vue-h5总是下载依赖包失败
使用淘宝源
```shell
npm install --registry=https://registry.npmmirror.com
```

#### 使用代码生成器的时候生成不了代码
下载项目好，首先在项目根目录下运行mvn install -DskipTestss将项目打包成功，然后再生成，代码生成器读取实体类的class文件进行生成，所以必须构建生成class文件


####  Invalid character found in the request target. The valid characters are defined in RFC 3986
将api部署在tomcat中的时候，如果前端请求参数有特殊字符，例如{},[]等，tomcat会报上述错误，
出现这个错误的原因是不支持参数中的特殊字符，可以通过该如下方式借据：
- 在conf/catalina.properties中最后添加2行：
```properties
tomcat.util.http.parser.HttpParser.requestTargetAllow=|{}
org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true
```
- requestTargetAllow 只能配置|、{、} 允许这三个字符，对于其他的(例如" < > [ \ ] ^ ` { | } .)，在请求时，仍然拦截，如果使用了|{}之外的其他字符那怎么办呢？那就还需要如下配置。
- 在conf/server.xml中的Connector节点中，添加2个属性：
 ```xml
relaxedPathChars="|{}[],"
relaxedQueryChars="|{}[],"
```
这2个属性，可以接收任意特殊字符的组合，根据需要可以自行增减。

**参考文档：** https://blog.csdn.net/ljheee/article/details/82051755