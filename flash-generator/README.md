# web-flash 代码生成工具

## 使用方法
- 在Intellij IDEA中新建一个Run Config，如下图所示
![run config](doc/run_config.jpg)
    - 上图三个配置分别为
        - 1，运行的代码生成主类
        - 2，代码生成参数,详见下面说明
        - 3，在哪个模块中执行代码生成：guns-entity，因为代码生成是通过读取guns-entity中的实体类的注解实现，所以这里选择guns-entity

- 新建配置文件：flash-core/src/main/resources/code/code.json,配置文件内容如下，下面内容指定了生成的相关代码分别放在哪个模块下，考虑到有的同学可能会更改项目名和模块名，增加该配置，开发人员可以自行配置模块名
```json
{
  "codeConfig": {
    "type": CCodeConfig   entityModel: "flash-core",
      daoModel: "flash-core",
      serviceModel: "flash-core",
      controllerModel: "flash-api",
      viewModel: "flash-vue-admin"
    }
  }
}
```   

## 代码生成参数
执行生成的时候，在Program arguments配置为：-h 运行后将会输出下面参数帮助提示

```shell
usage: Generator [options] [all|entity|service|controller|view]
 -f,--force               force generate file even if file exists
 -h,--help                show help message
 -i,--include <arg>       include table pattern
 -module,--module <arg>   current module name
 -p,--package <arg>       base package name,default:cn.enilu.guns
 -u,--base-uri <arg>      base uri prefix, default is /
 -x,--exclude <arg>       exclude table pattern
```   

比如在Program arguments配置 -mudule test 将会根据guns-entity/src/main/java/cn/enilu/guns/bean/entity/test/目录下所有的java类生成其CRUD相关代码

如果程序运行参数更改为 -mudule test -i t_test_boy 将会根据上述目录下的Boy实体生成其相关CRUD代码，注意这里-i 后面的参数值是实体中配置的表名称，而不是实体名本身。

测试实体Boy内容如下
```java
package cn.enilu.guns.bean.entity.test;
import cn.enilu.guns.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="t_test_boy")
@Table(appliesTo = "t_test_boy",comment = "男孩")
@Data
public class Boy extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(32) COMMENT '姓名'")
    private String name;
    @Column(columnDefinition = "INT COMMENT '年龄'")
    private Integer age;
    @Column(columnDefinition = "VARCHAR(12) COMMENT '生日'")
    private String birthday;
    @Column(name = "has_girl_friend",columnDefinition = "TINYINT COMMENT '是否有女朋友'")
    private Boolean hasGirFriend;
}
```
注意如果要根据实体生成代码,实体中的@Table注解要配置为@org.hibernate.annotations.Table而不是能配置为：@javax.persistence.Table,因为前者可以配置注释comment

以Boy实体为例,执行代码生成后将会生成如下文件：
![run code_add](doc/code_add.jpg)

代码生成后，在guns-admin-vuejs的路由配置文件中guns-admin-vuejs/src/store/modules/permission.js，增加如下红框内容：

![run router](doc/router.jpg)

启动guns-api和guns-admin-vuejs,配置和菜单和角色权限后就可以访问刚刚生成的代码功能了
![run code_result](doc/code_result.jpg)


## One More Thing 
本项目提供了基于IDEA的插件来生成代码,你可以再idea插件仓库中索索webflash-generator，或者直接从本地安装插件：flash-generator/idea-plugin.jar即可
用法如下：
- 首先写好实体类，打开实体类，右键选择Generator(或者Alt+Insert)     
 ![](../docs/img/plugin/generator.jpg)
 
 - 选择web-flash mvc，在弹框中勾选生成选项 
 ![](../docs/img/plugin/generator-config.jpg) 
 - 点击生成即可，生成完毕后在vue的router/index.js添加路由，启动api和vue项目，配置上菜单和角色后即可使用
        
