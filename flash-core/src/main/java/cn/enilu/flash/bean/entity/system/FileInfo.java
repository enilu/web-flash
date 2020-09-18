package cn.enilu.flash.bean.entity.system;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

@Data
@Entity(name = "t_sys_file_info")
@Table(appliesTo = "t_sys_file_info", comment = "文件")
@EntityListeners(AuditingEntityListener.class)
public class FileInfo extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(64) COMMENT '原始文件名称'")
    private String originalFileName;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '文件存储在磁盘中的真正名称'")
    private String realFileName;
    @Transient
    private String ablatePath;

}
