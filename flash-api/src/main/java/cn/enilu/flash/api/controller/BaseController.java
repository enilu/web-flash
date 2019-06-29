package cn.enilu.flash.api.controller;


import cn.enilu.flash.api.utils.ApiConstants;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.cache.TokenCache;
import cn.enilu.flash.utils.HttpKit;
import cn.enilu.flash.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * 基础controller
 *
 * @author enilu
 * @version 2018-07-25
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 根据token获取用户id，如果不存在则抛出异常
     *
     * @param request
     * @return
     */
    public Long getIdUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Long idUser = SpringContextHolder.getBean(TokenCache.class).get(token);
        if (idUser == null) {
            throw new RuntimeException("用户不存在");
        }
        return idUser;
    }

    /**
     * 获取客户端token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String getToken() {
        return HttpKit.getRequest().getHeader("Authorization");
    }

    /**
     * 获取客户端ip
     *
     * @param req
     * @return
     */
    public String getRealIp(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ApiConstants.IP_UNKNOW.equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ApiConstants.IPV6_LOCALHOST.equals(ip)) {
            ip =ApiConstants.IPV4_LOCALHOST;
        }
        return ip;
    }

    /**
     * 获取前端传递过来的json字符串<br>
     * 如果前端使用axios的data方式传参则使用改方法接收参数
     *
     * @return
     */
    public String getjsonReq() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(HttpKit.getRequest().getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            br.close();
            if (sb.length() < 1) {
                return "";
            }
            String reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
            reqBody = reqBody.substring(reqBody.indexOf("{"));
            return reqBody;

        } catch (IOException e) {

            logger.error("获取json参数错误！{}", e.getMessage());

            return "";

        }

    }

    public <T> T getFromJson(Class<T> klass) {
        String jsonStr = getjsonReq();
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        JSONObject json = JSONObject.parseObject(jsonStr);
        return JSON.toJavaObject(json, klass);
    }


}
