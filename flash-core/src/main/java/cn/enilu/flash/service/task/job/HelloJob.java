package cn.enilu.flash.service.task.job;

import cn.enilu.flash.bean.entity.system.Cfg;
import cn.enilu.flash.dao.system.CfgRepository;
import cn.enilu.flash.service.task.JobExecuter;
import cn.enilu.flash.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * HelloJob
 *
 * @author zt
 * @version 2018/12/30 0030
 */
@Component
public class HelloJob extends JobExecuter {
    @Autowired
    private CfgRepository cfgRepository;
    @Override
    public void execute(Map<String, Object> dataMap) throws Exception {
        Cfg cfg = cfgRepository.findById(1L).get();
        cfg.setCfgDesc(cfg.getCfgDesc()+"update by "+ DateUtil.getTime());
        cfgRepository.save(cfg);
    }
}
