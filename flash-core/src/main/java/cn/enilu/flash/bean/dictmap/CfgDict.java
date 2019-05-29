package cn.enilu.flash.bean.dictmap;

import cn.enilu.flash.bean.dictmap.base.AbstractDictMap;

/**
 * 字典map
 *
 * @author fengshuonan
 * @date 2017-05-06 15:43
 */
public class CfgDict extends AbstractDictMap {

    @Override
    public void init() {
        put("cfgId","参数id");
        put("cfgName","参数名称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
