package cn.enilu.flash.service.message;


import cn.enilu.flash.bean.entity.message.Message;
import cn.enilu.flash.bean.entity.message.MessageSender;
import cn.enilu.flash.bean.entity.message.MessageTemplate;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.dao.message.MessageRepository;
import cn.enilu.flash.dao.message.MessagesenderRepository;
import cn.enilu.flash.dao.message.MessagetemplateRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.service.message.email.EmailSender;
import cn.enilu.flash.service.message.sms.SmsSender;
import cn.enilu.flash.utils.StringUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.text.StrSubstitutor;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
/**
 * MessageService
 *
 * @author enilu
 * @version 2019/05/17 0017
 */
@Service
public class MessageService extends BaseService<Message,Long,MessageRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessagesenderRepository messagesenderRepository;
    @Autowired
    private MessagetemplateRepository messagetemplateRepository;



    public boolean delete(String ids) {
        final ArrayList<String> list = Lists.newArrayList(Splitter.on(',').split(ids));
        messageRepository.deleteAllByIdIn(list);
        return true;
    }

    public void sendTplEmail(String tplCode, String from, String to, String cc, String title, Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode,from,to,cc,title,content,messageTemplate,null,null);
    }
    public void sendTplEmail(String tplCode, String from, String to, String cc, String title,
                             String attachmentFilename, InputStreamSource inputStreamSource,
                             Map<String, Object> dataMap) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), dataMap);
        sendEmailMessage(tplCode,from,to,cc,title,content,messageTemplate,attachmentFilename,inputStreamSource);
    }

    public void sendSimpleEmail(String tplCode, String from, String to, String cc, String title, String... args) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), args);
        sendEmailMessage(tplCode,from,to,cc,title,content,messageTemplate,null,null);
    }
    public void sendSms(String tplCode, String receiver, String... args) {
        MessageTemplate messageTemplate = messagetemplateRepository.findByCode(tplCode);
        String content = getContent(messageTemplate.getContent(), args);
        boolean isSuccess = false;
        try {
            isSuccess = this.sendSmsMessage(receiver, content, messageTemplate, args);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        saveMessage(0,tplCode,receiver,content,isSuccess);
    }
    private void sendEmailMessage(String tplCode, String from, String to, String cc, String title,
                                  String content,MessageTemplate messageTemplate,
                                  String attachmentFilename, InputStreamSource inputStreamSource){
        try {
            EmailSender emailSender = getEmailSender(messageTemplate);
            boolean isSuccess = false;
            if(inputStreamSource!=null){
                isSuccess = emailSender.sendEmail(from, to, cc, title, content,attachmentFilename,inputStreamSource);
            }else {
                  isSuccess = emailSender.sendEmail(from, to, cc, title, content);
            }
            saveMessage(1, tplCode, to, content, isSuccess);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            saveMessage(1, tplCode, to, content, false);
        }
    }

    private String getContent(String template, String... args) {
        List<Object> argList = new ArrayList<>();
        argList.add("");
        if (args != null) {
            Collections.addAll(argList, args);
        }
        String content = MessageFormat.format(template, Lang.collection2array(argList));
        return content;
    }

    private String getContent(String template, Map<String, Object> dataMap) {
        return StrSubstitutor.replace(template, dataMap);
    }

    private void saveMessage(Integer type, String tplCode, String receiver, String content, Boolean sendResult) {
        Message message = new Message();
        message.setType(type);
        message.setTplCode(tplCode);
        message.setType(0);
        message.setState(sendResult ? 1 : 2);
        message.setReceiver(receiver);
        message.setCreateTime(new Date());
        message.setContent(content);
        messageRepository.save(message);

    }




    private boolean sendSmsMessage( String receiver, String content,  MessageTemplate messageTemplate,String... args) throws Exception {
        String tplCode = getTpl(messageTemplate);
        SmsSender smsSender = getSmsSender(messageTemplate);

        boolean success = false;
        String[] receivers = receiver.split(",|;", -1);
        for (String oneReceiver : receivers) {
            try {

                if (StringUtils.isNotEmpty(oneReceiver)) {
                    success = smsSender.sendSms(tplCode, oneReceiver, args, content);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return success;
    }

    private SmsSender getSmsSender(MessageTemplate messageTemplate) throws Exception {
        MessageSender messageSender = messagesenderRepository.findById(messageTemplate.getIdMessageSender()).get();
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
        MessageSender messageSender = messagesenderRepository.findById(messageTemplate.getIdMessageSender()).get();
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
    private String getTpl(MessageTemplate messageTemplate) {
        MessageSender messageSender = messagesenderRepository.findById(messageTemplate.getIdMessageSender()).get();

        if (messageSender != null && StringUtils.isNotEmpty(messageSender.getTplCode())) {
            return messageSender.getTplCode();
        } else {
            return null;
        }
    }
}

