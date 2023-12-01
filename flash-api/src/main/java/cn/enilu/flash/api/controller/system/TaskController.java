package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.system.Task;
import cn.enilu.flash.bean.entity.system.TaskLog;
import cn.enilu.flash.bean.enumeration.ApplicationExceptionEnum;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.task.TaskLogService;
import cn.enilu.flash.service.task.TaskService;
import cn.enilu.flash.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created  on 2018/4/9 0009.
 * 系统参数
 *
 * @author enilu
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskLogService taskLogService;


    /**
     * 获取定时任务管理列表
     */

    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.TASK})
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Boolean disabled) {
        Page<Task> page = new PageFactory<Task>().defaultPage();
        page.addFilter("name", name);
        page.addFilter("disabled", disabled);
        page = taskService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 新增定时任务管理
     */
    @PostMapping
    @BussinessLog(value = "编辑定时任务", key = "name")
    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public Object add(@RequestBody @Valid Task task) {

        Ret validRet = taskService.validate(task);
        if (!validRet.isSuccess()) {
            return validRet;
        }
        if (task.getId() == null) {
            taskService.save(task);
        } else {
            Task old = taskService.get(task.getId());
            old.setName(task.getName());
            old.setCron(task.getCron());
            old.setJobClass(task.getJobClass());
            old.setNote(task.getNote());
            old.setData(task.getData());
            taskService.update(old);
        }
        return Rets.success();
    }

    /**
     * 删除定时任务管理
     */
    @DeleteMapping
    @BussinessLog(value = "删除定时任务", key = "taskId")
    @RequiresPermissions(value = {Permission.TASK_DEL})
    public Object delete(@RequestParam Long id) {
        taskService.delete(id);
        return Rets.success();
    }

    @DeleteMapping("batchRemove")
    @BussinessLog(value = "批量删除任务", key = "id")
    @RequiresPermissions(value = {Permission.ROLE_DEL})
    public Ret batchRemove(@RequestParam(value = "id[]") Long[] id) {
        for (Long taskId : id) {
            if (taskId == null) {
                throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
            }
            taskService.delete(taskId);
        }
        return Rets.success();
    }

    @PostMapping(value = "/disable")
    @BussinessLog(value = "禁用定时任务", key = "taskId")
    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public Object disable(@RequestParam Long taskId) {
        taskService.disable(taskId);
        return Rets.success();
    }

    @PostMapping(value = "/enable")
    @BussinessLog(value = "启用定时任务", key = "taskId")
    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public Object enable(@RequestParam Long taskId) {
        taskService.enable(taskId);
        return Rets.success();
    }


    @GetMapping(value = "/logList")
    @RequiresPermissions(value = {Permission.TASK})
    public Object logList(@RequestParam Long taskId) {
        Page<TaskLog> page = new PageFactory<TaskLog>().defaultPage();
        page.addFilter(SearchFilter.build("idTask", SearchFilter.Operator.EQ, taskId));
        page = taskLogService.queryPage(page);
        return Rets.success(page);
    }

}
