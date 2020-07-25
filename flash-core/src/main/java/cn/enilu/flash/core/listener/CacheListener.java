package cn.enilu.flash.core.listener;

import cn.enilu.flash.cache.ConfigCache;
import cn.enilu.flash.cache.DictCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 系统监听器<br>
 * 系统启动时加载全局参数(t_sys_cfg标中的数据)到缓存中
 *
 * @author enilu
 * @version 2018-12-23
 */
@Component
public class CacheListener implements CommandLineRunner {

    @Autowired
    private ConfigCache configCache;
    @Autowired
    private DictCache dictCache;
    private Logger logger = LoggerFactory.getLogger(CacheListener.class);

    public void loadCache() {
        configCache.cache();
        dictCache.cache();
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info(".....................load cache........................");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadCache();
            }
        });
        thread.start();
    }
}
