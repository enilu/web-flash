package cn.enilu.flash.service.workflow;

import cn.enilu.flash.bean.vo.workflow.ProcessDefinitionVo;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.factory.Page;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descript
 *
 * @Author enilu
 * @Date 2021/7/21 17:31
 * @Version 1.0
 */
@Service
public class ProcessDefinitionService {
    @Autowired
    private RepositoryService repositoryService;
    public Page<ProcessDefinitionVo> queryPage(Page<ProcessDefinitionVo> page){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionId()
                .orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage(page.getOffset(),page.getLimit());
        Long count = processDefinitionQuery.count();
        if(count>0) {
            page.setTotal(count.intValue());

        }

        List<ProcessDefinitionVo> processDefinitionVos = Lists.newArrayList();
        for(ProcessDefinition processDefinition:processDefinitions){
            ProcessDefinitionVo vo = new ProcessDefinitionVo(processDefinition);
            processDefinitionVos.add(vo);
        }
        page.setRecords(processDefinitionVos);

        return page;
    }

}
