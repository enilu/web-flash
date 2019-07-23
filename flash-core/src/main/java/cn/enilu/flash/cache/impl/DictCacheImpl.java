package cn.enilu.flash.cache.impl;

import cn.enilu.flash.bean.constant.cache.CacheKey;
import cn.enilu.flash.bean.entity.system.Dict;
import cn.enilu.flash.cache.CacheDao;
import cn.enilu.flash.cache.DictCache;
import cn.enilu.flash.dao.system.DictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DictCacheImpl
 *
 * @author zt
 * @version 2018/12/23 0023
 */
@Component
public class DictCacheImpl implements DictCache {
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private CacheDao cacheDao;

    @Override
    public List<Dict> getDictsByPname(String dictName) {
        return (List<Dict>) cacheDao.hget(EhcacheDao.CONSTANT,CacheKey.DICT+dictName,List.class);
    }

    @Override
    public String getDict(Long dictId) {
        return (String) get(CacheKey.DICT_NAME+String.valueOf(dictId));
    }

    @Override
    public void cache() {
    List<Dict> list = dictRepository.findByPid(0L);
    for(Dict dict:list){
        List<Dict> children  = dictRepository.findByPid(dict.getId());
        if(children.isEmpty()) {
           continue;
        }
        set(String.valueOf(dict.getId()), children);
        set(dict.getName(), children);
        for(Dict child:children){
            set(CacheKey.DICT_NAME+String.valueOf(child.getId()),child.getName());
        }

    }

    }

    @Override
    public Object get(String key) {
        return cacheDao.hget(EhcacheDao.CONSTANT,CacheKey.DICT+key);
    }

    @Override
    public void set(String key, Object val) {
        cacheDao.hset(EhcacheDao.CONSTANT,CacheKey.DICT+key,val);

    }
}
