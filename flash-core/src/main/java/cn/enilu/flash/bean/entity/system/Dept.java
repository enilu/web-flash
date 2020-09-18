package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name = "t_sys_dept")
@Table(appliesTo = "t_sys_dept", comment = "组织/部门")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Dept extends BaseEntity {
    @Column(columnDefinition = "INT COMMENT '排序'")
    private Integer num;
    @Column(columnDefinition = "BIGINT COMMENT '父组织id'")
    private Long pid;
    /**
     * 从顶级组织id开始一直到父组织id，例如：[0],[1], 期中0为标识根组织(虚拟组织，实际并没有这个组织），1为父组织
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '所有上级组织id列表'")
    private String pids;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '部门/组织简称'")
    @NotBlank(message = "部门简称不能为空")
    private String simplename;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '部门/组织全称'")
    @NotBlank(message = "部门全称不能为空")
    private String fullname;
}
