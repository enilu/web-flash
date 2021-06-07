package cn.enilu.flash.api.interceptor;

import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.security.JwtToken;
import cn.enilu.flash.security.JwtUtil;
import cn.enilu.flash.service.system.UserService;
import cn.enilu.flash.utils.HttpUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：enilu
 * @date ：Created in 2019/7/30 23:07
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     * 登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                logger.info(e.getMessage());
                //判断如果抛出token失效，则执行刷新token逻辑
                if (e.getMessage().contains("expired")) {
                    //获取用户信息
                    String oldToken = HttpUtil.getToken();
                    Long userId = JwtUtil.getUserId(oldToken);
                    UserService userService = SpringContextHolder.getBean(UserService.class);
                    User user = userService.get(userId);
                    //验证refreshToken是否有效
                    if (userService.refreshTokenIsValid(oldToken)) {
                        //生成新token 返回界面
                        String newToken = userService.loginForToken(user);
                        JwtToken jwtToken = new JwtToken(newToken);
                        this.getSubject(request, response).login(jwtToken);
                        HttpUtil.getResponse().setHeader("token", newToken);
                        return true;
                    }
                }
                response401(request, response);
                return false;
            }
        }
        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求返回httpcode:401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.setStatus(401);
//            httpServletResponse.getWriter().println("401");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 重新该方法直接返回false，因为走到这个方法的请求都是因为401过来的，所以拒绝继续访问
     * 如果不重写该方法，父类的方法回返回WWW-Authenticate 头信息导致浏览器自身弹出验证框，影响用户使用体验。本项目的业务要求前端自行判断401的话往登录页面跳转，不需要浏览器自己弹框。
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }
}
