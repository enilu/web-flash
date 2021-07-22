package cn.enilu.flash.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */

@Entity(name = "t_sys_operation_log")
@Table(appliesTo = "t_sys_operation_log", comment = "操作日志")
@Data
public class OperationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 日志类型，
     * @see cn.enilu.flash.bean.constant.state.LogType
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '日志类型'")
    private String logtype;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '日志名称'")
    private String logname;
    @Column(columnDefinition = "INT COMMENT '操作用户id'")
    private Integer userid;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '对应类名'")
    private String classname;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '对应方法名'")
    private String method;
    @Column(columnDefinition = "DATE COMMENT '操作日期'")
    private Date createTime;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '成功标识'")
    private String succeed;
    @Column(columnDefinition = "TEXT COMMENT '详细信息'")
    private String message;

}
