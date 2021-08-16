package cn.enilu.flash.api.controller.workflow;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.core.ShiroUser;
import cn.enilu.flash.bean.entity.workflow.WorkFlowRequest;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.cache.TokenCache;
import cn.enilu.flash.service.workflow.WorkFlowRequestService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.factory.Page;
import cn.enilu.flash.wrapper.TaskWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/workflow/request")
public class WorkFlowRequestController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WorkFlowRequestService workFlowRequestService;
    @Autowired
    private TokenCache tokenCache;

    @GetMapping(value = "/list")
    @RequiresPermissions(value = "/workflow/request1")
    public Object list(@RequestParam(required = false) Long id) {
        Page<WorkFlowRequest> page = new PageFactory<WorkFlowRequest>().defaultPage();
        page = workFlowRequestService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 代办任务列表
     *
     * @return
     */
    @GetMapping(value = "/tasks")
    @RequiresPermissions(value = "/workflow/request/task")
    public Object tasks(@RequestParam(required = false) Long id) {
        Page<WorkFlowRequest> page = new PageFactory<WorkFlowRequest>().defaultPage();
        ShiroUser shiroUser = tokenCache.getUser(getToken());
        List<String> roleCodes = shiroUser.getRoleCodes();
        page = workFlowRequestService.queryTask(page, roleCodes);


        page.setRecords((List<WorkFlowRequest>) new TaskWrapper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return Rets.success(page);
    }

    @PostMapping(value = "/completeTask")
    @RequiresPermissions(value = "/workflow/request/task")
    public Object completeTask(@RequestParam Long id,
                               @RequestParam String taskId,
                               @RequestParam Integer state) {
        workFlowRequestService.completeTask(id, taskId,state);

        return Rets.success();
    }

    @PostMapping
    @BussinessLog(value = "新增工作流请求实例", key = "name")
    public Object add(@ModelAttribute WorkFlowRequest workFlowRequest) {
        workFlowRequestService.insert(workFlowRequest);
        return Rets.success();
    }

    @PutMapping
    @BussinessLog(value = "更新工作流请求实例", key = "name")
    public Object update(@ModelAttribute WorkFlowRequest workFlowRequest) {
        workFlowRequestService.update(workFlowRequest);
        return Rets.success();
    }

    @DeleteMapping
    @BussinessLog(value = "删除工作流请求实例", key = "id")
    public Object remove(Long id) {
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        workFlowRequestService.delete(id);
        return Rets.success();
    }
}