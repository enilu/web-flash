package cn.enilu.flash.service.message.sms;

import java.util.LinkedHashMap;

public interface SmsSender {

    /**
     * 发送短信，如果内容content不为空，则直接发送内容
     *
     * @param tplCode  短信运营商模板号码
     * @param receiver
     * @param params
     * @param content
     * @return
     */
    boolean sendSms(String tplCode, String receiver, LinkedHashMap params, String content);
}
