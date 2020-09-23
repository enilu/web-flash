# 系统参数管理

参数管理包括参数的维护和使用两部分

## 系统参数维护
后台界面提供对系统参数的增删该查，开发者可以通过这个功能维护系统参数

![sysConfig](./img/sysConfig.jpg)

## 系统参数使用
系统启动后会将参数放到本地缓存中，并提供了一个ConfigCache类来访问系统参数，用法如下：

```java
@Autowired
private ConfigCache ConfigCache;
//获取系统文件默认上传目录
configCache.get(ConfigKeyEnum.SYSTEM_FILE_UPLOAD_PATH);
```
