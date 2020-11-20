# 初始化数据

本系统使用mysql数据库

- 在mysql中创建数据库 webflash
    ```sql
    CREATE DATABASE IF NOT EXISTS `webflash` DEFAULT charset utf8 COLLATE utf8_general_ci;
    ```
- 启动项目的时候会自动创建数据库并初始化系统数据，所以无需自己初始化数据
- 本系统通过Spring Boot的配置：spring.jpa.hibernate.ddl-auto=create 来初始化数据库，建议在生产环境中去掉该配置以避免将关键业务数据删除掉。
- 初始化语句为:src/main/resources/import.sql，当配置spring.jpa.hibernate.ddl-auto=create的时候，系统启动会自动执行该语句初始化测试数据。
- 如果你是个传统的人；就是想要数据库文件，用传统的方式初始化数据库，点击这里[database.sql](https://gitee.com/enilu/web-flash/blob/master/docs/web-flash.sql)，强烈不建议使用这种方式，因为本人可能不是那么及时的更新这个文件导致一些问题。
