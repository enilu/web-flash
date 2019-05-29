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
@Entity(name = "t_sys_menu")
@Table(appliesTo = "t_sys_menu",comment = "菜单")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Menu  extends BaseEntity {
    @Column
    private String code;
    @Column
    private String pcode;
    @Column
    private String pcodes;
    @Column
    private String name;
    @Column
    private String icon;
    @Column
    private String url;
    @Column
    private Integer num;
    @Column
    private Integer levels;
    @Column
    private Integer ismenu;
    @Column
    private String tips;
    @Column
    private Integer status;
    @Column
    private Integer isopen;

}
