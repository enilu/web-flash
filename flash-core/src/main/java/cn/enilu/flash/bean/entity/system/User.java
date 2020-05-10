package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name = "t_sys_user")
@Table(appliesTo = "t_sys_user",comment = "账号")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User  extends BaseEntity {
    @Column
    private String avatar;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '账户'")
    private String account;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '密码'")
    private String password;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '密码盐'")
    private String salt;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '姓名'")
    private String name;
    @Column
    private Date birthday;
    @Column
    private Integer sex;
    @Column(columnDefinition = "VARCHAR(64) COMMENT 'email'")
    private String email;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String phone;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '角色id列表，以逗号分隔'")
    private String roleid;
    @Column
    private Long deptid;
    @Column
    private Integer status;
    @Column
    private Integer version;
    @JoinColumn(name="deptid", insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.EAGER)
    private Dept dept;
}
