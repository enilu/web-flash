package cn.enilu.flash.bean.vo.workflow;

import lombok.Data;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 流程定义
 *
 * @Author enilu
 * @Date 2021/7/21 17:30
 * @Version 1.0
 */
@Data
public class ProcessDefinitionVo {

    private String id;

    private String name;

    private String key;

    private int version;

    private String deploymentId;
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
