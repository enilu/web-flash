package cn.enilu.flash.core.aop;

import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.dictmap.base.AbstractDictMap;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.cache.TokenCache;
import cn.enilu.flash.core.factory.Contrast;
import cn.enilu.flash.core.log.LogManager;
import cn.enilu.flash.core.log.LogTaskFactory;
import cn.enilu.flash.service.system.LogObjectHolder;
import cn.enilu.flash.utils.HttpKit;
import cn.enilu.flash.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午8:48:30
 */
@Aspect
@Component
public class LogAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(cn.enilu.flash.bean.core.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //获取用户id，admin和api模块获取idUser方式不同
        Long idUser = null;
        HttpServletRequest request = HttpKit.getRequest();
        String token = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(token)) {
            idUser = SpringContextHolder.getBean(TokenCache.class).get(token);
        }
        if(idUser==null) {
            return ;
            //如果当前用户未登录，不做日志
//            ShiroUser user = ShiroKit.getUser();
//            if (null == user) {
//                return;
//            }
//            idUser = user.getId();
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg="";
        if (bussinessName.indexOf("修改") != -1 || bussinessName.indexOf("编辑") != -1) {
            //todo api模块无法使用该方法获取数据
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpKit.getRequestParameters();
            try {
                msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
            }catch (Exception e){

            }
        } else {
            Map<String, String> parameters = HttpKit.getRequestParameters();
            AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
            msg = Contrast.parseMutiKey(dictMap,key,parameters);
        }

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(idUser, bussinessName, className, methodName, msg));
    }
}