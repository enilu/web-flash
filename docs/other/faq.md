# 常见问题

## 本地开发正常，打包运行的时候提交中文内容乱码，检查了数据库编码也没问题？

打包为jar包运行的时候可以指定运行时编码为UTF8：
```
java -Dfile.encoding=utf-8 -jar xxxxxxx.jar
```

## 打包vue-admin或者vue-h5总是下载依赖包失败
使用淘宝源
```shell
npm install --registry=https://registry.npm.taobao.org
```

## 使用代码生成器的时候生成不了代码
下载项目好，首先运行mvn package -DskipTestss将项目打包成功，然后再生成，代码生成器读取实体类的class文件进行生成，所以必须构建生成class文件