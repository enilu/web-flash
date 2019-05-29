# 基本包结构

本节详细说明本项目的基本目录结构

## web-flash模块

web-flash包含6个核心模块：
- guns-admin 一个成熟的后台管理系统，完全具备了后台管理系统的基本功能
- guns-admin-vuejs 基于vuejs的后台管理,如果你想要前后端分离，那么该目录和下面的guns-api可以帮到你，这两个模块共同实现了上面guns-admin实现了的功能
- guns-api 基于vuejs后台管理的api服务
- guns-utils 工具包
- guns-dao dao层
- guns-entity 实体层
- guns-service 服务层

其中guns-admin和guns-api一个java web模块，guns-admin-vuejs是前端项目（基于vuejs），
其他都为java se模块，
