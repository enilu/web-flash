# 日志管理
日志管理包括两方面：
一个是后台用户登录日志的查看,
一个是业务日志查看，业务日志内容主要包含两方面：系统产生的异常和用户操作日志。



## 登录日志
用户登录系统和退出系统的时候会调用LogTaskFactory记录相关日志，并在“登录日志”页面进行展示。

![loginLog](./img/loginLog.jpg)

## 业务日志

业务日志包含异常日志和业务操作日志两大类日志收集、保存和展示。

### 异常日志

系统提供了异常处理类：GlobalExceptionHandler 来对系统各种异常进行统一收集保存。
该类提供了九个方法来分别对9中常见的异常类型进行收集保存；如果开发者自己有特殊需求需要对其他异常类型处理，可以通过新增处理方式来对新的异常进行收集。


### 业务操作日志

系统提供了通过注解的形式可以方便的添加业务操作日志，比如记录修改系统参数的操作日志，可以通过如下方式：

在CfgController的编辑参数增加@BussinessLog注解：

```java
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑参数", key = "cfgName",dict= CfgDict.class)   
    public Object save(@ModelAttribute @Valid Cfg cfg){
        if(cfg.getId()!=null){
            Cfg old = cfgService.get(cfg.getId());
            LogObjectHolder.me().set(old);
            old.setCfgName(cfg.getCfgName());
            old.setCfgValue(cfg.getCfgValue());
            old.setCfgDesc(cfg.getCfgDesc());
            cfgService.saveOrUpdate(old);
        }else {
            cfgService.saveOrUpdate(cfg);
        }
        return Rets.success();
    }
```
- 后台通过LogAop来保存用户操作日志。
- 在LogAop中也提供了获取变更前后的数据变化的功能。
- 通过LogObjectHolder类将变更前的数据临时存起来，在LogAop中拿出来和新数据进行对比。
- 如果不需要记录详细变更的内容，可以不设置，也就不会记录详细的变化内容了。

具体的实现逻辑感兴趣的同学可以自行通过注解类：BussinessLog进行跟踪查看。