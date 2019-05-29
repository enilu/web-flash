package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name="t_sys_dept")
@Table(appliesTo = "t_sys_dept",comment = "部门")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Dept extends BaseEntity {
    @Column
    private Integer num;
    @Column
    private Long pid;
    @Column
    private String pids;
    @Column
    private String simplename;
    @Column
    private String fullname;
    @Column
    private String tips;
    @Column
    private Integer version;
}
