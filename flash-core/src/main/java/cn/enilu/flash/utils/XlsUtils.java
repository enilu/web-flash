package cn.enilu.flash.utils;

import java.util.Date;

public class XlsUtils {
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        if(StringUtil.isEmpty(fmt)){
            return DateUtil.getTime(date);
        }else{
            return DateUtil.formatDate(date, fmt);
        }
    }
}
