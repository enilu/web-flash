package cn.enilu.flash.bean.entity.test;

import cn.enilu.flash.bean.entity.BaseEntity;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
/**
 * 该实体用于测试生成代码
 */
@Entity(name="t_test_girl")
@Table(appliesTo = "t_test_girl",comment = "女孩")
public class Girl extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(32) COMMENT '姓名'")
    private String name;
    @Column(columnDefinition = "INT COMMENT '年龄'")
    private Integer age;
    @Column(columnDefinition = "DATE COMMENT '生日'")
    private Date birthday;
    @Column(name = "has_boy_friend",columnDefinition = "TINYINT COMMENT '是否有男朋友'")
    private Boolean hasBoyFriend;
}
