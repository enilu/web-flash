package cn.enilu.flash.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name="t_sys_login_log")
@Table(appliesTo = "t_sys_login_log",comment = "登录日志")
@Data
public class LoginLog {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String logname;
    @Column
    private Integer userid;
    @Column
    private String succeed;
    @Column
    private String message;
    @Column
    private String ip;
    @CreationTimestamp
    @Column(name = "create_time",columnDefinition="DATETIME COMMENT '创建时间'")
    private Date createTime;
}
