package cn.enilu.flash.cache;

import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.service.system.impl.ConstantFactory;

/**
 * @author ：enilu
 * @date ：Created in 2020/4/26 19:07
 */
public abstract class BaseCache implements Cache {
    @Override
    public void cache() {
        SpringContextHolder.getBean(ConstantFactory.class).cleanLocalCache();
    }
}
