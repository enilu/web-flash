package cn.enilu.flash.wrapper;

import cn.enilu.flash.service.system.impl.ConstantFactory;
import cn.enilu.flash.warpper.BaseControllerWrapper;

import java.util.Map;

/**
 * descript
 *
 * @Author enilu
 * @Date 2021/7/25 2:20
 * @Version 1.0
 */
public class TaskWrapper extends BaseControllerWrapper {
    public TaskWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Long userid = Long.valueOf(map.get("createBy").toString());
        map.put("userName", ConstantFactory.me().getUserNameById(userid));

    }
}
