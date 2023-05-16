package cn.enilu.flash.service.workflow;


import cn.enilu.flash.bean.entity.workflow.WorkFlowRequest;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.dao.workflow.WorkFlowRequestRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.Maps;
import cn.enilu.flash.utils.factory.Page;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class WorkFlowRequestService extends BaseService<WorkFlowRequest, Long, WorkFlowRequestRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WorkFlowRequestRepository workFlowRequestRepository;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    /**
     * 启动新流程
     * 1，保存业务表单
     * 2，启动新流程
     * 3，关联流程信息和业务信息
     *
     * @param flowRequest
     * @return
     */
    @Override
    public WorkFlowRequest insert(WorkFlowRequest flowRequest) {
        //1,
        flowRequest.setState(0);
        flowRequest = super.insert(flowRequest);
        //2,
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(flowRequest.getProcessDefId()).singleResult();
        String key = processDefinition.getKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, flowRequest.getId().toString());
        //3,
        flowRequest.setProcessDefName(processDefinition.getName());
        logger.info("id:{}", processInstance.getId());
        logger.info("processInstanceId:{}", processInstance.getProcessInstanceId());
        flowRequest.setInstanceId(processInstance.getProcessInstanceId());
        update(flowRequest);
        return flowRequest;
    }

    /**
     * 查询所有代办任务
     *
     * @return
     */
    public Page<WorkFlowRequest> queryTask(Page<WorkFlowRequest> page, List<String> roleNames) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery.taskAssigneeIds(roleNames).listPage(page.getOffset(), page.getLimit());
        Long count = taskQuery.count();
        page.setTotal(count.intValue());
        List<WorkFlowRequest> flowRequests = Lists.newArrayList();
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            WorkFlowRequest flowRequest = get(SearchFilter.build("instanceId", processInstanceId));
            if(flowRequest!=null) {
                flowRequests.add(flowRequest);
                flowRequest.setTaskId(task.getId());
            }
        }
        page.setRecords(flowRequests);
        logger.info(JsonUtil.toJson(page));
        return page;
    }


    public void completeTask(Long id, String taskId,Integer state) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("state",state);
        taskService.complete(taskId,params);
    }

    /**
     * 获取流程图
     *
     * @param processInstanceId
     * @param highLight
     * @return
     */
    /**
     * Get Process instance diagram
     */
    public InputStream getProcessDiagram(String processInstanceId, boolean highLight) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String processDefinitionId =   processInstance.getProcessDefinitionId();

        // null check
        if (processDefinitionId != null) {
            // get process model
            BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);

            if (model != null && model.getLocationMap().size() > 0) {
                ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
                if(highLight) {
                    // 生成流程图 已启动的task 高亮
                    List<String> list =  runtimeService.getActiveActivityIds(processInstanceId);
                    return generator.generateDiagram(model,list);
                }else {
                    // 生成流程图 都不高亮
                return generator.generateDiagram(model, "宋体","宋体","宋体");
                }
            }
        }
        return null;
    }
}

