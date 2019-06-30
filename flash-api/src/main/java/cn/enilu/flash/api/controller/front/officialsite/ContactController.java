package cn.enilu.flash.api.controller.front.officialsite;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.entity.cms.Contacts;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.service.cms.ContactsService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offcialSite/contact")
public class ContactController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ContactsService contactsService;
    @RequestMapping(method = RequestMethod.POST)
    public Object save(Contacts contacts){
        logger.info(JSON.toJSONString(contacts));
        contactsService.insert(contacts);
        return Rets.success();
    }
}
