package cn.enilu.flash.bean.vo.workflow;

import lombok.Data;
import org.activiti.engine.repository.ProcessDefinition;
import org.hibernate.annotations.Table;

import javax.persistence.Column;

/**
 * 流程定义
 *
 * @Author enilu
 * @Date 2021/7/21 17:30
 * @Version 1.0
 */
@Data
@Table(appliesTo = "t_workflow_process_definition", comment = "流程定义")
public class ProcessDefinitionVo {

    private String id;
    @Column(name = "name", columnDefinition = "VARCHAR(32) COMMENT '名称'")
    private String name;
    @Column(name = "key", columnDefinition = "VARCHAR(32) COMMENT '标识'")
    private String key;
    @Column(name = "version", columnDefinition = "VARCHAR(32) COMMENT '版本号'")
    private int version;
    @Column(name = "deploymentId", columnDefinition = "VARCHAR(32) COMMENT '部署id'")
    private String deploymentId;
    @Column(name = "resourceName", columnDefinition = "VARCHAR(32) COMMENT '流程资源'")
    private String resourceName;

    public ProcessDefinitionVo(){

    }
    public ProcessDefinitionVo(ProcessDefinition processDefinition){
        this.id = processDefinition.getId();
        this.name = processDefinition.getName();
        this.key = processDefinition.getKey();
        this.version = processDefinition.getVersion();
        this.deploymentId = processDefinition.getDeploymentId();
        this.resourceName = processDefinition.getResourceName();
    }
}
