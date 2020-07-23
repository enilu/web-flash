package cn.enilu.flash.cache;

import cn.enilu.flash.bean.enumeration.ConfigKeyEnum;

/**
 * 全局配置数据访问
 */
public interface ConfigCache extends Cache {


    /**
     * 获取全局配置参数值，可选本地缓存
     *
     * @param key
     * @param local true:从缓存中获取，false:从数据库获取
     * @return
     */
    String get(String key, boolean local);

    /**
     * 获取全局配置参数值(带默认值)
     *
     * @param key the key
     * @param def the default value
     * @return the config
     */
    String get(String key, String def);

    /**
     * 获取全局配置参数值
     *
     * @param configKeyEnum
     * @return
     */
    String get(ConfigKeyEnum configKeyEnum);

    /**
     * 删除缓存
     *
     * @param key
     * @param val
     */
    void del(String key, String val);
}
