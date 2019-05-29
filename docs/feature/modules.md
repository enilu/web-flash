# 基本包结构

本节详细说明本项目的基本目录结构

## web-flash模块

web-flash包含6个核心模块：
- guns-admin
- guns-admin-vuejs
- guns-utils
- guns-dao
- guns-entity
- guns-service

其中guns-admin一个java web模块，其他都为java se项目；
具体每个包的作用通过名字即可看出包含的功能分别为：后台管理、工具包、数据库操作层、实体层、
服务层

![modules](./img/modules.jpg)


具体每个包里的细节不详细介绍，开发人员可以在使用过程中主键料件，本身代码量并不大，了解起来不需要花费太多时间。

这里仅详细说明下guns-admin的内部结构，毕竟日常开发主要是基于该模块来做的。

## guns-admin
guns-admin是一个标准的web项目

![guns-admin](./img/guns-admin.jpg)

目录结构包含：

- src/main/java  java源码
- src/main/resources  配置文件
- src/webapp  web页面和静态文件资源
这里介绍下guns-admin的基本目录和开发流程

### src/main/java/ 源代码

目录结构如下所示：

![guns-admin](./img/guns-src.jpg)

- **common** 该package 封装了一些工具的类库，如一些注解，常量、枚举，异常等公共类
- **config** 该package 包含项目支持各种特性的相关配置。例如：
    - 支持swagger在线文档的配置
    - EhCache缓存配置
    - Session配置
    - Shiro配置
    - Beetl模版配置
- **core** 该package是项目的核心，包含注入数据源管理、缓存管理、模版管理、aop，监听器以及分页工具、各种工具类（这里自所以有封装自己的工具类而不是放在guns-utils中，是因为这些工具类是与guns-admin紧密相关的工具类，并不适用于其他模块）。
当然开发人员可以根据项目实际情况做二次调整和封装。

- **modular** 该pakcage存放和业务相关的代码。
比如目前提供了一个**system模块**，主要包含诸如：用户、角色、权限、日志等管理功能的系统管理功能。

system包中除了controller包是必须的，其他包都是根据具体情况选择是否需要。
业务相关的service、dao、entity写到对应的guns-service/dao/entity 模块中，而不是写在guns-admin模块中。

**这样设计的目的是因为**，大多数实际项目不仅包含后台管理，还可能包括面向客户端的服务，如app或者微信公众号或者pc站，这些服务也需要一套平台比如针对app和微信公众号可能需要一套api系统，那么这时候api系统就可以和guns-admin共用service、dao和实体层。



### src/webapp  web页面和静态文件资源
 
目录结构如下所示：

![guns-admin](./img/guns-web.jpg)

- static 目录为静态资源
    - css、fonts，img，js分别为公共的样式、字体，图片，js资源
    - modular 目录为业务用js资源，比如system即为admin内置功能的js资源，其中每个功能使用一个目录和WEB-INF/view/中的目录一一对应
    
    ![guns-web-js](./img/guns-web-js.jpg)
    
- WEB-INF/view 为页面目录
    - common 为公共的页面框架和封装的标签目录
    - 其他目录为业务页面目录，比如system即为内置的功能页面包括用户、角色、权限等管理功能的页面
    
    ![guns-web-page](./img/guns-web-page.jpg)

