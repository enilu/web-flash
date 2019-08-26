package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.vo.SpringContextHolder;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * 被修改的bean临时存放的地方
 *
 * @author enilu
 * @date 2019-08-13
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable{

    private Object object = null;

    public void set(Object obj) {
        try {
            //为表面后面的逻辑对obj进行变更，这里克隆一份存储，用于后续变化对比
            Object cloneObj = BeanUtils.cloneBean(obj);
            this.object = cloneObj;
        }   catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object get() {
        return object;
    }

    public static LogObjectHolder me(){
        LogObjectHolder bean = SpringContextHolder.getBean(LogObjectHolder.class);
        return bean;
    }
}
