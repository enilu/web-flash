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
@Entity(name = "t_sys_menu")
@Table(appliesTo = "t_sys_menu",comment = "菜单")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Menu  extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(32) COMMENT '编号'",unique = true,nullable = false)
    @NotBlank(message="编号不能为空")
    private String code;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '父菜单编号'",nullable = false)
    private String pcode;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '递归父级菜单编号'")
    private String pcodes;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '名称'",nullable = false)
    @NotBlank(message = "名称并能为空")
    private String name;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '图标'")
    private String icon;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '链接'")
    private String url;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '顺序'",nullable = false)
    private Integer num;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '级别'",nullable = false)
    private Integer levels;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '是否是菜单1:菜单,0:按钮'",nullable = false)
    private Integer ismenu;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '鼠标悬停提示信息'")
    private String tips;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '状态1:启用,0:禁用'",nullable = false)
    private Integer status;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '是否默认打开1:是,0:否'")
    private Integer isopen;

}
