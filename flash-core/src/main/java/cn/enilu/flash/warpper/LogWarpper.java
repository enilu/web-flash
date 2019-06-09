package cn.enilu.flash.warpper;

import cn.enilu.flash.core.factory.Contrast;
import cn.enilu.flash.service.system.impl.ConstantFactory;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.ToolUtil;

import java.util.Date;
import java.util.Map;

/**
 * 日志列表的包装类
 *
 * @author fengshuonan
 * @date 2017年4月5日22:56:24
 */
public class LogWarpper extends BaseControllerWarpper {

    public LogWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        String message = (String) map.get("message");

        Long userid = Long.valueOf(map.get("userid").toString());
        map.put("userName", ConstantFactory.me().getUserNameById(userid));

        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put("message", subMessage);
        }
        map.put("createtime",DateUtil.format((Date) map.get("createTime"),"yyyy-MM-dd hh:MM:ss"));
        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (ToolUtil.isNotEmpty(message) && message.indexOf(Contrast.SEPARATOR) != -1) {
            String[] msgs = message.split(Contrast.SEPARATOR);
            map.put("regularMessage",msgs);
        }else{
            map.put("regularMessage",message);
        }
    }

}
