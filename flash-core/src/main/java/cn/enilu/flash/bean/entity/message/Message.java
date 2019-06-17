package cn.enilu.flash.bean.entity.message;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 历史消息
 */
@Data
@Entity(name="t_message")
@Table(appliesTo = "t_message",comment = "历史消息")
public class Message extends BaseEntity {
    @Column(name="tpl_code",columnDefinition = "VARCHAR(32) COMMENT '模板编码'")
    private String tplCode;
    @Column(name="content",columnDefinition = "TEXT COMMENT '消息内容'")
    private String content;
    @Column(name="receiver",columnDefinition = "VARCHAR(64) COMMENT '接收者'")
    private String receiver;
    @Column(name="type",columnDefinition = "VARCHAR(32) COMMENT '消息类型,0:短信,1:邮件'")
    private Integer type;
    @Column(name="state",columnDefinition = "VARCHAR(32) COMMENT '消息类型,0:初始,1:成功,2:失败'")
    private Integer state;
}
