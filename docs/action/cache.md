# 缓存的应用
对缓存的应用几乎成了系统的标配，web-flash中也有对缓存的应用。
本章节介绍系统中缓存的设计和用法

## 底层缓存支持
- web-flash为了给上层应用提供缓存支持，提供了CacheDao接口，CacheDao接口负责和底层的缓存组件打交道，比如Ehcache、Redis、ssdb，甚至自己实现的缓存系统皆可。
- 在web-flash中针对CacheDao的默认实现类是EhcacheDao，没错web-flash默认使用的缓存组件就是Ehcache。
- 针对ehcache的具体用法实现，可以查看EhcacheDao的实现和ehcache.xml配置，这里不再赘述。

## 缓存应用

目前web-flash中使用到缓存的地方有两个，一个是系统参数的管理，一个是字典管理。
具体用法也很简单，分为以下几个步骤
- 系统启动的时候通过CacheListener将数据加载到缓存
- 具体的功能中使用的时候注入对应的缓存类使用即可。
- 数据更新的时候重新刷新缓存

这里摘录一些关键代码：
- CacheListner 缓存监听器，启动的时候将数据从数据库加载到缓存中
```java
@Component
public class CacheListener implements CommandLineRunner {
    @Autowired
    private ConfigCache configCache;
    @Autowired
    private DictCache dictCache;
    public void loadCache() {
        configCache.cache();
        dictCache.cache();
    }

    @Override
    public void run(String... strings) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadCache();
            }
        });
        thread.start();
    }
}
```
- Cache顶级缓存接口，定义了缓存基本的三个方法
```java

public interface Cache {
	void cache();
	Object get(String key);
	void set(String key, Object val);
}
```


## 备注
**为什么选用Ehcahce**
- 目前最流行的缓存中间件非Redis莫属。而且我司大多数产品和项目也是使用redis，但是考虑到Ehcahe的开箱即用（直接整合到项目中，不需要部署专门的缓存服务），所以在web-flash
默认支持Ehcache，
- 想用Redis也很简单，参考EhcacheDao实现一个RedisCacheDao即可。

**当数据库中数据变化的时候缓存中的数据如何做到更新**
- web-flash中的做法简单明了，目前针对全局参数Cfg和字典Dict表的进行更新操作的时候分别调用ConfigCache和DictCache的cache()重新将数据库中的数据加载到缓存中.
- 具体代码参考CfgService和DictService
- 具体生产中，也许会有很多产品都是微服务架构，这时候推荐使用zookeeper来做配置变更的管理，感兴趣的同学可以在qq群众讨论，这里不再赘述。
 