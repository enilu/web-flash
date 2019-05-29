# 初始化数据

本系统使用mysql数据库

- 在mysql中创建数据库 web-flash
    ```sql
    CREATE DATABASE IF NOT EXISTS web-flash DEFAULT charset utf8 COLLATE utf8_general_ci;
    ```
- 启动项目的时候会自动创建数据库并初始化系统数据
