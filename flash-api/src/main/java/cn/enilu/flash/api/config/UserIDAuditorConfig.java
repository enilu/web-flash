package cn.enilu.flash.api.config;

import cn.enilu.flash.cache.TokenCache;
import cn.enilu.flash.utils.HttpKit;
import cn.enilu.flash.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审计功能配置
 *
 * @author enilu
 * @version 2019/1/8 0008
 */
@Configuration
public class UserIDAuditorConfig implements AuditorAware<Long> {
    @Autowired
    private TokenCache tokenCache;
    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            String token = HttpKit.getRequest().getHeader("Authorization");
            if (StringUtils.isNotEmpty(token)) {
                return Optional.of(tokenCache.get(token));
            }
        }catch (Exception e){
            //返回系统用户id
            return Optional.of(-1L);
        }
        //返回系统用户id
        return Optional.of(-1L);
    }
}
