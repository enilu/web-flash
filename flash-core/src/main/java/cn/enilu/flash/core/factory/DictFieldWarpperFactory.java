package cn.enilu.flash.core.factory;

import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.service.system.IConstantFactory;
import cn.enilu.flash.service.system.impl.ConstantFactory;

import java.lang.reflect.Method;

/**
 * 字段的包装创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-06 15:12
 */
public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object field, String methodName) {
        IConstantFactory me = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, field.getClass());
            Object result = method.invoke(me, field);
            return result;
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Long.class);
                Object result = method.invoke(me, Long.valueOf(field.toString()));
                return result;
            } catch (Exception e1) {
                try {
                    Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                    Object result = method.invoke(me, Integer.parseInt(field.toString()));
                    return result;
                } catch (Exception e2) {
                    throw new ApplicationException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
                }
            }
        }
    }

}
