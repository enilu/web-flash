package cn.enilu.flash.bean.entity.message;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * 消息发送者
 */
@Data
@Entity(name="t_message_sender")
@Table(appliesTo = "t_message_sender",comment = "消息发送者")
public class MessageSender extends BaseEntity {
    @Column(name="name",columnDefinition = "VARCHAR(64) COMMENT '名称'")
    @NotBlank(message = "名称并能为空")
    private String name;
    @Column(name="class_name",columnDefinition = "VARCHAR(64) COMMENT '发送类'")
    @NotBlank(message = "发送类不能为空")
    private String className;
    @Column(name="tpl_code",columnDefinition = "VARCHAR(64) COMMENT '短信运营商模板编号'")
    private String tplCode;

}
