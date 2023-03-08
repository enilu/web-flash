package cn.enilu.flash.workflow.servicetask;

import cn.enilu.flash.bean.entity.workflow.WorkFlowRequest;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.service.workflow.WorkFlowRequestService;
import cn.enilu.flash.utils.JsonUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 利用serviceTask自动处理工作流任务
 *
 * @Author enilu
 * @Date 2021/7/25 23:03
 * @Version 1.0
 */
@Service
public class AutoCompleteServiceTask implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(AutoCompleteServiceTask.class);
    @Autowired
    private WorkFlowRequestService workFlowRequestService;
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        WorkFlowRequest request = SpringContextHolder.getBean(WorkFlowRequestService.class).get(Long.valueOf(businessKey));
        logger.info("request:{}", JsonUtil.toJson(request));
        Long ret =System.currentTimeMillis()%2;
        Integer state = ret.intValue()==1?1:2;
        delegateExecution.setVariable("state",state);
        logger.info("state:{}",state);

    }
}
