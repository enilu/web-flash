package cn.enilu.flash.api.config;

import cn.enilu.flash.api.interceptor.JwtFilter;
import cn.enilu.flash.security.ApiRealm;
import cn.enilu.flash.security.SystemLogoutFilter;
import cn.enilu.flash.utils.Maps;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：enilu
 * @date ：Created in 2019/7/30 23:08
 */
@Configuration
public class ShiroConfig {
    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(ApiRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(realm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put("jwt", new JwtFilter());
        factoryBean.setFilters(filterMap);

        filterMap.put("logout", new SystemLogoutFilter());
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");

        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         * 这里最好用LinkedHashMap,否则可能回出现anon配置无效的情况
         */
        Map<String, String> filterRuleMap = new LinkedHashMap<String, String>();
        // 所有请求通过我们自己的JWT Filter
        //swagger资源不拦截
        filterRuleMap.put("/swagger-ui/**", "anon");
        filterRuleMap.put("/v3/**","anon");
        filterRuleMap.put("/doc.html","anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/images/**", "anon");
        filterRuleMap.put("/configuration/security", "anon");
        filterRuleMap.put("/configuration/ui", "anon");

        filterRuleMap.put("/file/download", "anon");
        filterRuleMap.put("/file/getImgStream", "anon");
        filterRuleMap.put("/file/getImgBase64", "anon");

        filterRuleMap.put("/test/**", "anon");
        filterRuleMap.put("/workflow/request/png/**", "anon");
        //工作流定义文件流
        filterRuleMap.put("/workflow/process/definition/getDefinitionXML","anon");
        //druid监控地址不拦截
        filterRuleMap.put("/druid/**", "anon");
        //登录，二维码登录，登出不拦截
        filterRuleMap.put("/account/login", "anon");
        filterRuleMap.put("/account/qrcode/**", "anon");
        filterRuleMap.put("/logout", "logout");
        //H5前端不拦截
        filterRuleMap.put("/offcialsite/**", "anon");
        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
