# 初始化数据

本系统使用mysql数据库，如果要使用oracle，可以使用oracle分支，这里仅说明mysql数据库初始化步骤。

- 在mysql中创建数据库 web-flash

```sql
CREATE DATABASE IF NOT EXISTS web-flash DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

```

- 登录mysql书库，导入mysql.sql文件

```sql
mysql>source d:/mysql.sql
```
