# 将数据库切换为sqlite

细心的同学可能已经注意到web-flash提供一个名为oracle的分支来支持将后台数据库替换为oacel的需求。
那么为什么不同样建立一个sqlite分支来这么做呢？主要考虑到sqlite和mysql兼容性比较好，使用master分支做很小的调整就可以支持sqlite，那么就不费劲维护两个分支了。

下面介绍如何将master分支经过简单调整将底层数据库从mysql切换为sqlite。

## 数据库替换
这节描述如何将mysql替换为sqlite。

在本系统中使用的mysql建表语句中，只需要下面字段类型调整下就可以用在sqlite中。
- 将bigint类型替换为INTEGER
- 将verchar类型替换为TEXT
- 将date、datetime类型皆替换为TEXT或INTEGER，我在我司的产品中都是替换为text类型，这样也不用考虑日期格式化的问题。
将更改过的语句在mysql中执行即可。

这里在项目中也提供了[sqlite.sql](https://github.com/enilu/web-flash/blob/master/db/sqlite.sql)，你可以直接使用

## entity模块调整

接下来将guns-entity中对应数据库表的实体中属性类型进行替换。
- 将所有日期类型的字段替换为String，当然你也可以直接继续用Date（这样的话，sqlite的对应字段类型必须为INTGER）

没错，只需要调整日期类型的属性即可，甚至也不需要调整（如果数据库中对应字段使用INTEGER的话）

## 业务代码调整

 如果在上一小节，你将日期类型调整为String，那么在涉及到所有为entity设置日期属性的时候用cn.enilu.guns.utils.DateUtil.getTime()代替new Date()方法即可，
 DateUtil.getTime() 方法会返回当前日期的yyyy-MM-dd HH:mm:ss格式
 
 大功告成，是不是很简单！



## 使用sqlite常见问题

### No Dialect mapping for JDBC type: 0
最近基于web-flash和sqlite做一个产品，使用一个复杂的sql语句查询返回结果为空的时候报异常：
```
org.springframework.orm.jpa.JpaSystemException: No Dialect mapping for JDBC type: 0; nested exception is org.hibernate.MappingException: No Dialect mapping for JDBC type: 0
```
出现这个问题的原因是：
**返回值返回类型为Null,hibernate不支持SQLite的空值类型，所以需要我们自定义映射关系**

- 解决方法
    - 自定义SQLiteDialect
    ```java
    package cn.enilu.guns.api.config;
    import java.sql.Types;
    public class SQLiteDialect extends com.enigmabridge.hibernate.dialect.SQLiteDialect {
        public SQLiteDialect(){
            super();
            registerHibernateType(Types.NULL,"null");
        }
    }
    ```
    - 配置该方言
    ```properties
    spring.jpa.properties.hibernate.dialect=cn.enilu.guns.api.config.SQLiteDialect
    ```

**参考资料:** 
- https://kevin12.iteye.com/blog/1768258
- https://stackoverflow.com/questions/10719117/sqlite-no-dialect-mapping-for-jdbc-type-0-hibernate
