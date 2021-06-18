package cn.enilu.flash.warpper;

import cn.enilu.flash.service.system.impl.ConstantFactory;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DeptWrapper extends BaseControllerWrapper {

    public DeptWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {

        Long pid = (Long) map.get("pid");

        if (pid == null || pid.intValue() == 0) {
            map.put("pName", "--");
        } else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
    }

}
