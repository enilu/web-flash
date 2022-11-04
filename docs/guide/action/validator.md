# Validator数据校验

项目中使用了hibernate-validator对数据录入的时候做了验证。

由于项目本身使用了hibernate,所以不需要额外引入依赖包，只需要在对应的地方添加注解和少量代码即可。


## 实体类

在实体类中对应的字段添加验证注解,以：cn.enilu.flash.bean.entity.message.MessageTemplate为例

```java
@Data
@Entity(name="t_message_template")
@Table(appliesTo = "t_message_template",comment = "消息模板")
public class MessageTemplate extends BaseEntity {
    @Column(name="code",columnDefinition = "VARCHAR(32) COMMENT '编号'")
    @NotBlank(message = "编号不能为空")
    private String code;
    @NotBlank(message = "内容不能")
    @Column(name="content",columnDefinition = "TEXT COMMENT '内容'")
    private String content;
    @Column(name="id_message_sender",columnDefinition = "BIGINT COMMENT '发送者id'")
    @NotNull(message = "发送器不能为空")
    private Long idMessageSender; 
    ....
}
```

- 上述中针对字符串类型的数据，我们使用不能为空的验证@NotBlank,该注解标识该字段不能为null或者空字符串。
- 针对idMessageSender，则使用了@NotNull来验证；由于@NotBlank是针对字符串做验证，所以针对idMessageSender不能使用它，否则会报异常，下面列举更多常用注解：

```
@AssertFalse 校验false
@AssertTrue 校验true
@DecimalMax(value=,inclusive=) 小于等于value，
inclusive=true,是小于等于
@DecimalMin(value=,inclusive=) 与上类似
@Max(value=) 小于等于value
@Min(value=) 大于等于value
@NotNull  检查Null
@Past  检查日期
@Pattern(regex=,flag=)  正则
@Size(min=, max=)  字符串，集合，map限制大小
@Validate 对po实体类进行校验
```

## controller

在提交数据的方法中，需要使用@Valid来标识验证该类中的字段输入的合法性。

以cn.enilu.flash.api.controller.message.MessagetemplateController.save方法为例：

```java
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑消息模板", key = "name", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_TPL_EDIT})
    public Object save(@ModelAttribute @Valid MessageTemplate tMessageTemplate) {
        messagetemplateService.saveOrUpdate(tMessageTemplate);
        return Rets.success();
    }
```



## 前端展示错误信息

为了前端更友好的展示错误信息，在flash-vue-admin/src/utils/request.js中针对错误信息统一做了处理：
```javascript
  if(error.response && error.response.data.errors) {
      Message({
        message: error.response.data.errors[0].defaultMessage,
        type: 'error',
        duration: 5 * 1000
      })
    }
```
由于提交的时候可能会出发多个校验规则，因此会返回多个错误信息，这里仅显示第一个错误信息。