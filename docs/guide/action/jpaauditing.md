# 系统审计


通常来说，我们都有这样的需求：我需要知道库中的数据是由谁创建，什么时候创建，最后一次修改时间是什么时候，最后一次修改人是谁。

在Spring jpa中可以通过在实体bean的属性或者方法上添加以下注解来实现上述需求@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy。

- @CreatedDate 表示该字段为创建时间时间字段，在这个实体被insert的时候，会设置值
- @CreatedBy 表示该字段为创建人，在这个实体被insert的时候，会设置值
- @LastModifiedDate 最后修改时间 实体被update的时候会设置
- @LastModifiedBy  最后修改人 实体被update的时候会设置

## 使用方式

### 实体类添加注解

- 首先在实体中对应的字段加上上述4个注解
- 在web-flash中我们抽象了了一个基础实体类BaseEntity，并将对应的字段添加上述4个注解,所有需要记录维护信息的表对应的实体都继承该类
```java
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    @Column(name = "create_time",columnDefinition="DATETIME COMMENT '创建时间/注册时间'")
    private Date createTime;
    @Column(name = "create_by",columnDefinition="bigint COMMENT '创建人'")
    @CreatedBy
    private Long createBy;
    @LastModifiedDate
    @Column(name = "modify_time",columnDefinition="DATETIME COMMENT '最后更新时间'")
    private Date modifyTime;
    @LastModifiedBy
    @Column(name = "modify_by",columnDefinition="bigint COMMENT '最后更新人'")
    private Long modifyBy;
}
```

### 实现AuditorAware接口返回操作人员的id
配置完上述4个注解后，在jpa.save方法被调用的时候，时间字段会自动设置并插入数据库，但是CreatedBy和LastModifiedBy并没有赋值
这两个信息需要实现AuditorAware接口来返回操作人员的id
- 首先需要在项目启动类添加@EnableJpaAuditing 注解来启用审计功能
```java
@SpringBootApplication
@EnableJpaAuditing
public class ApiApplication extends SpringBootServletInitializer {
 //省略
}
```
- 然后实现AuditorAware接口返回操作人员的id
```java
@Configuration
public class UserIDAuditorConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            String token = HttpUtil.getRequest().getHeader("Authorization");
            if (StringUtil.isNotEmpty(token)) {
                return Optional.of(JwtUtil.getUserId(token));
            }
        }catch (Exception e){
            //返回系统用户id
            return Optional.of(Constants.SYSTEM_USER_ID);
        }
        //返回系统用户id
        return Optional.of(Constants.SYSTEM_USER_ID);
    }
}
```
- 正常情况下用户通过浏览器进行系统管理操作，后台可以可以获取请求的request对象进而获取当前操作用户id，
但是有时候后台系统线程（比如定时任务）更新数据的时候并没有对应的request，
所以指定当前操作用户id为-1，即认定为系统自身对数据进行操作。


- 在系统自动设置审计信息的实体上添加注解:@EntityListeners(AuditingEntityListener.class),以配置参数实体Cfg为例：
```java
@Entity(name="t_sys_cfg")
@Table(appliesTo = "t_sys_cfg",comment = "系统参数")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Cfg  extends BaseEntity {
    @NotBlank(message = "参数名不能")
    @Column(name = "cfg_name",columnDefinition = "VARCHAR(256) COMMENT '参数名'")
    private String cfgName;
    @NotBlank(message = "参数值不能为空")
    @Column(name = "cfg_value",columnDefinition = "VARCHAR(512) COMMENT '参数值'")
    private String cfgValue;
    @Column(name = "cfg_desc",columnDefinition = "TEXT COMMENT '备注'")
    private String cfgDesc;

}
```
