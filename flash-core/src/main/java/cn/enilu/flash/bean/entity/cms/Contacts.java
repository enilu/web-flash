package cn.enilu.flash.bean.entity.cms;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="t_cms_contacts")
@Data
@Table(appliesTo = "t_cms_contacts",comment = "邀约信息")
public class Contacts extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(64) COMMENT '邀约人名称'")
    private String userName;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '联系电话'")
    private String mobile;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '电子邮箱'")
    private String email;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '备注'")
    private String remark;
}
