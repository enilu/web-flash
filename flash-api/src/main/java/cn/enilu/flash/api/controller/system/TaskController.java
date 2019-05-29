package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.dictmap.TaskDict;
import cn.enilu.flash.bean.entity.system.Task;
import cn.enilu.flash.bean.entity.system.TaskLog;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.dao.system.TaskRepository;
import cn.enilu.flash.service.task.TaskService;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created  on 2018/4/9 0009.
 * 系统参数
 * @author enilu
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;


    /**
     * 获取定时任务管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String name) {
        if(StringUtils.isNullOrEmpty(name)) {
            return Rets.success(taskRepository.findAll());
        }else{
            return Rets.success(taskRepository.findByNameLike("%"+name+"%"));
        }
    }

    /**
     * 新增定时任务管理
     */
    @RequestMapping(method = RequestMethod.POST)

    @BussinessLog(value = "编辑定时任务", key = "name",dict = TaskDict.class)
    public Object add(@ModelAttribute Task task,
                      HttpServletRequest request) {
        if(task.getId()==null) {
            taskService.save(task);
        }else{
            Task old = taskService.get(task.getId());
            old.setName(task.getName());
            old.setCron(task.getCron());
            old.setNote(task.getNote());
            old.setData(task.getData());
            taskService.update(old);
        }
        return Rets.success();
    }

    /**
     * 删除定时任务管理
     */
    @RequestMapping(method = RequestMethod.DELETE)

    @BussinessLog(value = "删除定时任务", key = "taskId",dict = TaskDict.class)
    public Object delete(@RequestParam Long id) {
        taskService.delete(id);
        return Rets.success();
    }

    @RequestMapping(value = "/disable",method = RequestMethod.POST)

    @BussinessLog(value = "禁用定时任务", key = "taskId",dict = TaskDict.class)
    public Object disable(@RequestParam Long taskId  ) {
        taskService.disable(taskId);
        return Rets.success();
    }
    @RequestMapping(value = "/enable",method = RequestMethod.POST)
    @BussinessLog(value = "启用定时任务", key = "taskId",dict = TaskDict.class)
    public Object enable(@RequestParam Long taskId  ) {
        taskService.enable(taskId);
        return Rets.success();
    }


    @RequestMapping(value="/logList")
    public Object logList(@Param("taskId") Long taskId) {
        Page<TaskLog> page = new PageFactory<TaskLog>().defaultPage();
        page = taskService.getTaskLogs(page,taskId);
        return Rets.success(page);
    }

}
