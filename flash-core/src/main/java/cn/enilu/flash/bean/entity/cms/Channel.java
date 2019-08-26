package cn.enilu.flash.bean.entity.cms;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

@Entity(name="t_cms_channel")
@Table(appliesTo = "t_cms_channel",comment = "文章栏目")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Channel extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(64) COMMENT '名称'")
    @NotBlank(message = "名称不能为空")
    private String name;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '编码'")
    @NotBlank(message = "编码不能为空")
    private String code;

}
