package cn.enilu.flash.cache;

import cn.enilu.flash.bean.core.ShiroUser;
import cn.enilu.flash.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户登录时，生成的Token与用户ID的对应关系
 */
@Service
public   class TokenCache {

    @Autowired
    private CacheDao cacheDao;

    public   void put(String token, Long idUser) {
        cacheDao.hset(CacheDao.SESSION,token, idUser);
    }

    public   Long get(String token) {
        return cacheDao.hget(CacheDao.SESSION,token,Long.class);
    }
    public Long getIdUser(){
        return cacheDao.hget(CacheDao.SESSION, HttpUtil.getToken(),Long.class );
    }

    public   void remove(String token) {
        cacheDao.hdel(CacheDao.SESSION,token+"user");
    }

    public void setUser(String token, ShiroUser shiroUser){
        cacheDao.hset(CacheDao.SESSION,token+"user",shiroUser);
    }
    public ShiroUser getUser(String token){
        return cacheDao.hget(CacheDao.SESSION,token+"user",ShiroUser.class);
    }
}
