package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.cache.CacheDao;
import cn.enilu.flash.dao.system.UserRepository;
import cn.enilu.flash.security.JwtUtil;
import cn.enilu.flash.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created  on 2018/3/23 0023.
 *
 * @author enilu
 */
@Service
public class UserService  extends BaseService<User,Long,UserRepository> {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CacheDao cacheDao;
    @Value("${jwt.token.expire.time}")
    private Long tokenExpireTime ;


    public User findByAccount(String account) {
        //由于：@Cacheable标注的方法，如果其所在的类实现了某一个接口，那么该方法也必须出现在接口里面，否则cache无效。
        //具体的原因是， Spring把实现类装载成为Bean的时候，会用代理包装一下，所以从Spring Bean的角度看，只有接口里面的方法是可见的，其它的都隐藏了，自然课看不到实现类里面的非接口方法，@Cacheable不起作用。
        //所以这里手动控制缓存
        User user =  cacheDao.hget(CacheDao.SESSION,account,User.class);
        if(user!=null){
            return user;
        }
        user = userRepository.findByAccount(account);
        cacheDao.hset(CacheDao.SESSION,account,user);
        return user;
    }

    @Override
    public User update(User record) {
        User user =  super.update(record);
        cacheDao.hset(CacheDao.SESSION,user.getAccount(),user);
        return user;
    }

    /**
     * 根据用户信息生成token
     * @param user
     * @return
     */
    public String loginForToken(User user){
        //获取用户token值
        String token = JwtUtil.sign(user,tokenExpireTime*60000);
        //将token作为RefreshToken Key 存到缓存中，缓存时间为token有效期的两倍
        String   refreshTokenCacheKey = token;
        Date expireDate = new Date(System.currentTimeMillis()+tokenExpireTime*120000);
        cacheDao.hset(CacheDao.SESSION,refreshTokenCacheKey,String.valueOf(expireDate.getTime()));
        logger.info("token:{}",token);
        return token;
    }

    /**
     * 获取refreshToken是否有效
     * @param token
     * @return
     */
    public boolean refreshTokenIsValid(String token){
        String  refreshTokenTime = (String) cacheDao.hget(CacheDao.SESSION,token);
        if(refreshTokenTime == null){
            return false;
        }
        return System.currentTimeMillis()<=Long.valueOf(refreshTokenTime);

    }
}
