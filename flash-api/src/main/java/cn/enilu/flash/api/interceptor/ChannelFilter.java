package cn.enilu.flash.api.interceptor;


import cn.enilu.flash.utils.WafRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 拦截所有请求过滤器，并将请求类型是HttpServletRequest类型的请求替换为自定义{@link WafRequestWrapper}
 * @create: 2023/11/28
 */
@Component
@WebFilter(filterName = "channelFilter", urlPatterns = {"/*"})
public class ChannelFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            ServletRequest requestWrapper = null;
            if (request instanceof HttpServletRequest) {
                requestWrapper = new WafRequestWrapper((HttpServletRequest) request);
            }
            if (requestWrapper == null) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void destroy() {
    }

}