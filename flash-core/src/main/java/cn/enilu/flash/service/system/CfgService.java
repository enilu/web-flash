package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.Cfg;
import cn.enilu.flash.cache.ConfigCache;
import cn.enilu.flash.dao.system.CfgRepository;
import cn.enilu.flash.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CfgService
 *
 * @author enilu
 * @version 2018/11/17 0017
 */

@Service
@Transactional
public class CfgService  extends BaseService<Cfg,Long,CfgRepository> {
    @Autowired
    private ConfigCache configCache;

    @Override
    public Cfg saveOrUpdate(Cfg cfg) {
        super.saveOrUpdate(cfg);
        configCache.cache();
        return cfg;
    }
    @Override
    public void delete(Long id) {
        super.delete(id);
        configCache.cache();
    }

}
