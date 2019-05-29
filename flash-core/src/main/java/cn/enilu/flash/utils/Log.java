package cn.enilu.flash.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author deanyule
 */
public class Log {

    public static Logger get(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
