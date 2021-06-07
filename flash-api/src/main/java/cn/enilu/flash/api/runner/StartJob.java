package cn.enilu.flash.api.runner;

import cn.enilu.flash.bean.entity.system.Task;
import cn.enilu.flash.bean.vo.QuartzJob;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.task.JobService;
import cn.enilu.flash.service.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动定时任务
 *
 * @author enilu
 * @Date 2019-08-13
 */
@Component
public class StartJob implements ApplicationRunner {

    @Autowired
    private JobService jobService;
    @Autowired
    private TaskService taskService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("start Job >>>>>>>>>>>>>>>>>>>>>>>");
        List<Task> tasks = taskService.queryAll(SearchFilter.build("disabled", SearchFilter.Operator.EQ, false));
        List<QuartzJob> list = jobService.getTaskList(tasks);
        for (QuartzJob quartzJob : list) {
            jobService.addJob(quartzJob);
        }
    }
}
