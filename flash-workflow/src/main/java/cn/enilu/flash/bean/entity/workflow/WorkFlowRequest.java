package cn.enilu.flash.bean.entity.workflow;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * 工作流请求信息
 *
 * @Author enilu
 * @Date 2021/7/24 7:43
 * @Version 1.0
 */
@Data
@Entity(name="t_workflow_request")
@Table(appliesTo = "t_workflow_request",comment = "工作流请求实例")
@EntityListeners(AuditingEntityListener.class)
public class WorkFlowRequest extends BaseEntity {
    public static  final int PASS=1;
    public static  final int REJECT=2;

    public static  final int ING=0;

    @Column(columnDefinition = "VARCHAR(64) COMMENT '标题'")
    private String title;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '流程定义deploymentId'")
    private String processDefId;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '流程定义名称'")
    private String processDefName;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '流程实例id'")
    private String instanceId;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '备注'")
    private String descript;
    @Column(columnDefinition = "INT COMMENT '状态:0:进行中,1:成功,2:失败'")
    private Integer state;
    /**
     * 当前待办任务id
     */
    @Transient
    private String taskId;

}
