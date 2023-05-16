package cn.enilu.flash.service.message;


import cn.enilu.flash.bean.entity.message.Message;
import cn.enilu.flash.bean.entity.message.MessageSender;
import cn.enilu.flash.bean.entity.message.MessageTemplate;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.dao.message.MessageRepository;
import cn.enilu.flash.dao.message.MessagesenderRepository;
import cn.enilu.flash.dao.message.MessagetemplateRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.service.message.email.DefaultEmailSender;
import cn.enilu.flash.service.message.email.EmailSender;
import cn.enilu.flash.service.message.sms.SmsSender;
import cn.enilu.flash.utils.StringUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MessageService
 *
 * @author enilu
 * @version 2019/05/17 0017
 */
@Service
public class MessageService extends BaseService<Message, Long, MessageRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessagesenderRepository messagesenderRepository;
    @Autowired
    private MessagetemplateRepository messagetemplateRepository;
    @Autowired
    private DefaultEmailSender defaultEmailSender;


    public boolean delete(String ids) {
        final ArrayList<String> list = Lists.newArrayList(Splitter.on(',').split(ids));
        messageRepository.deleteAllByIdIn(list);
        return true;
    }

    /**
     *
     * @param tplCode
     * @param to
     * @param cc
     * @param title
     * @param dataMap
     */
    public void sendTplEmail(String tplCode,  String to, String cc, String title, Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = StringUtil.replace(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, null, null);
    }

    /**
     *
     * @param tplCode
     * @param from
     * @param to
     * @param cc
     * @param title
     * @param dataMap
     */
    public void sendTplEmail(String tplCode, String from, String to, String cc, String title, Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = StringUtil.replace(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, null, null);
    }

    /**
     *
     * @param tplCode
     * @param from
     * @param to
     * @param cc
     * @param title
     * @param attachmentFilename
     * @param inputStreamSource
     * @param dataMap
     */
    public void sendTplEmailWithFile(String tplCode, String from, String to, String cc, String title,
                             String attachmentFilename, InputStreamSource inputStreamSource,
                             Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = StringUtil.replace(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, attachmentFilename, inputStreamSource);
    }
    public void sendSimpleEmail(String to, String cc, String title, String body) {
        sendEmailMessage(null, from, to, cc, title, body, null, null, null);
    }
    public void sendSimpleEmail(String tplCode, String from, String to, String cc, String title, String... args) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = StringUtil.replace(messageTemplate.getContent(), args);
        sendEmailMessage(tplCode, from, to, cc, title, content, messageTemplate, null, null);
    }

    public void sendSms(String tplCode, String receiver, String... args) {
        LinkedHashMap params = new LinkedHashMap();
        for (int i = 0; i < args.length; i++) {
            params.put((i + 1) + "", args[i]);
        }
        sendSms(tplCode, receiver, params);
    }

    public void sendSms(String tplCode, String receiver, LinkedHashMap params) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = StringUtil.replace(messageTemplate.getContent(), params);
        boolean isSuccess = false;
        try {
            isSuccess = this.sendSmsMessage(receiver, content, messageTemplate, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        saveMessage(0, tplCode, receiver, content, isSuccess);
    }

    private void sendEmailMessage(String tplCode, String from, String to, String cc, String title,
                                  String content, MessageTemplate messageTemplate,
                                  String attachmentFilename, InputStreamSource inputStreamSource) {
        try {
            EmailSender emailSender = defaultEmailSender;
            if(messageTemplate!=null) {
                emailSender = getEmailSender(messageTemplate);
            }
            boolean isSuccess = false;
            if (inputStreamSource != null) {
                isSuccess = emailSender.sendEmail(from, to, cc, title, content, attachmentFilename, inputStreamSource);
            } else {
                isSuccess = emailSender.sendEmail(from, to, cc, title, content);
            }
            saveMessage(1, StringUtil.isNotEmpty(tplCode)?tplCode:"no_template", to, content, isSuccess);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            saveMessage(1, tplCode, to, content, false);
        }
    }





    /**
     * 保存消息发送记录
     *
     * @param type
     * @param tplCode
     * @param receiver
     * @param content
     * @param sendResult
     */
    private void saveMessage(Integer type, String tplCode, String receiver, String content, Boolean sendResult) {
        Message message = new Message();
        message.setType(type);
        message.setTplCode(tplCode);
        message.setState(sendResult ? 1 : 2);
        message.setReceiver(receiver);
        message.setContent(content);
        messageRepository.save(message);

    }


    private boolean sendSmsMessage(String receiver, String content, MessageTemplate messageTemplate, LinkedHashMap params) throws Exception {
        SmsSender smsSender = getSmsSender(messageTemplate);
        boolean success = false;
        String[] receivers = receiver.split(",|;", -1);
        for (String oneReceiver : receivers) {
            try {

                if (StringUtil.isNotEmpty(oneReceiver)) {
                    success = smsSender.sendSms(messageTemplate.getRemoteTplCode(), oneReceiver, params, content);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return success;
    }

    private SmsSender getSmsSender(MessageTemplate messageTemplate) throws Exception {
        MessageSender messageSender = messagesenderRepository.getOne(messageTemplate.getIdMessageSender());
        if (messageSender != null) {
            try {
                return SpringContextHolder.getBean(messageSender.getClassName());
            } catch (Exception e) {
                logger.error("获取SmsService实现类失败", e);
                throw new Exception("smsService名称配置失败:" + messageSender.getClassName());
            }
        } else {
            throw new Exception("未配置运营商模版id");
        }
    }

    private EmailSender getEmailSender(MessageTemplate messageTemplate) throws Exception {
        MessageSender messageSender = messagesenderRepository.getOne(messageTemplate.getIdMessageSender());
        if (messageSender != null) {
            try {
                return SpringContextHolder.getBean(messageSender.getClassName());
            } catch (Exception e) {
                logger.error("获取SmsService实现类失败", e);
                throw new Exception("smsService名称配置失败:" + messageSender.getClassName());
            }
        } else {
            throw new Exception("未配置运营商模版id");
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("code", 11122);
        String template = "短信验证码为${code},谨慎保存";
        MessageService m = new MessageService();
        String ret = StringUtil.replace(template, map);
        System.out.println(ret);
        template = "你好:{1},欢迎使用{2}";
        System.out.println(StringUtil.replace(template,"张三","web-flash"));
    }

}

