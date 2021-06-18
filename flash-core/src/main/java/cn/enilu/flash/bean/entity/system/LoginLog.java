package cn.enilu.flash.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name = "t_sys_login_log")
@Table(appliesTo = "t_sys_login_log", comment = "登录日志")
@Data
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '日志表述'")
    private String logname;
    @Column(columnDefinition = "INT COMMENT '操作用户id'")
    private Integer userid;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '操作结果标识'")
    private String succeed;
    @Column(columnDefinition = "TEXT COMMENT '日志详情'")
    private String message;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '操作ip'")
    private String ip;
    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
