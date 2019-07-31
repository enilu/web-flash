package cn.enilu.flash.bean.entity.cms;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name="t_cms_article")
@Table(appliesTo = "t_cms_article",comment = "文章")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {
    @Column(columnDefinition = "BIGINT COMMENT '栏目id'")
    @NotNull(message = "栏目不能为空")
    private Long idChannel;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '标题'")
    @NotBlank(message = "标题不能为空")
    private String title;
    @Column(columnDefinition = "TEXT COMMENT '内容'")
    @NotBlank(message = "内容不能为空")
    private String content;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '作者'")
    private String author;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '文章题图ID'")
    private String img;
}
