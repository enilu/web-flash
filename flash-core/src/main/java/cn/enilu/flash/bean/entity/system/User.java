package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name = "t_sys_user")
@Table(appliesTo = "t_sys_user", comment = "账号")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '头像'")
    private String avatar;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '账户'")
    private String account;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '密码'")
    private String password;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '密码盐'")
    private String salt;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '姓名'")
    private String name;
    @Column(columnDefinition = "DATE COMMENT '生日'")
    private Date birthday;
    @Column(columnDefinition = "INT COMMENT '性别:1:男,2:女'")
    private Integer sex;
    @Column(columnDefinition = "VARCHAR(64) COMMENT 'email'")
    private String email;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String phone;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '角色id列表，以逗号分隔'")
    private String roleid;
    @Column(columnDefinition = "BIGINT COMMENT '部门id'")
    private Long deptid;
    @Column(columnDefinition = "INT COMMENT '状态1:启用,2:禁用'")
    private Integer status;
    @Column(columnDefinition = "INT COMMENT '版本'")
    private Integer version;
    @JoinColumn(name = "deptid", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.EAGER)
    private Dept dept;
}
