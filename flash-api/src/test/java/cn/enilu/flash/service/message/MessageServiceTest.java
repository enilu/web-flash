package cn.enilu.flash.service.message;

import cn.enilu.flash.BaseApplicationStartTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Map;

/**
 * 消息测试
 *
 * @author ：enilu
 * @date ：Created in 2023/03/10 15:03
 */
public class MessageServiceTest extends BaseApplicationStartTest {
    @Autowired
    private MessageService messageService;


    @Test
    public void sendTplEmail() {
        messageService.sendTplEmail("EMAIL_HTML_TEMPLATE_TEST","82552623@qq.com","zhangtao@xinshucredit.com","邮件标题",
                Map.of("userName","张三","appName","web-flash"));
    }
    @Test
    public void sendTplEmail2() {
        messageService.sendTplEmail("EMAIL_HTML_TEMPLATE_TEST","eniluzt@qq.com","82552623@qq.com","zhangtao@xinshucredit.com","邮件标题",
                Map.of("userName","张三2","appName","web-flash2"));
    }

    @Test
    public void sendTplEmailWithFile() {
        FileSystemResource fileSystemResource=new FileSystemResource(new File("d:\\test.txt"));
        messageService.sendTplEmailWithFile("EMAIL_HTML_TEMPLATE_TEST","eniluzt@qq.com","82552623@qq.com","zhangtao@xinshucredit.com","邮件标题",
                fileSystemResource.getFilename(),fileSystemResource, Map.of("userName","张三2","appName","web-flash2"));
    }
    @Test
    public void sendSimpleEmail() {
        messageService.sendSimpleEmail("8255223@qq.com","909051758@qq.com","测试简单邮件标题","测试简单邮件正文");
    }
    @Test
    public void sendSimpleEmail2() {
        messageService.sendSimpleEmail("EMAIL_TEST","eniluzt@qq.com","82552623@qq.com","909051758@qq.com","测试简单邮件标题2","参数1","参数2");
    }


    @Test
    public void sendSms() {
        messageService.sendSms("REGISTER_CODE","15021xxxxxx","1023");
    }
}