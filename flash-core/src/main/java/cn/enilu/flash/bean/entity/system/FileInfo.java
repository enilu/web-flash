package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Data
@Entity(name="t_sys_file_info")
@Table(appliesTo = "t_sys_file_info",comment = "文件")
public class FileInfo extends BaseEntity {
    @Column
    private String originalFileName;
    @Column
    private String realFileName;
    @Transient
    private String ablatePath;

}
