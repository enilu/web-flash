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
 * 系统通知，改表并没有真正用起来，可以根据自己实际需要做调整
 * @author enilu
 */

@Entity(name = "t_sys_notice")
@Table(appliesTo = "t_sys_notice", comment = "通知")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Notice extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(64) COMMENT '通知标题'")
    private String title;
    @Column(columnDefinition = "INT COMMENT '通知类型'")
    private Integer type;
    @Column(columnDefinition = "TEXT COMMENT '通知内容'")
    private String content;

}
