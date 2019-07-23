package cn.enilu.flash.api.controller.cms;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.cms.Contacts;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.cms.ContactsService;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactsController extends BaseController {
    @Autowired
    private ContactsService contactsService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(@RequestParam(required = false) String userName,
                       @RequestParam(required = false) String mobile) {
        Page<Contacts> page = new PageFactory<Contacts>().defaultPage();
        if(StringUtils.isNotEmpty(userName)){
            page.addFilter(SearchFilter.build("userName", SearchFilter.Operator.EQ, userName));
        }
        if (StringUtils.isNotEmpty(mobile)) {
            page.addFilter(SearchFilter.build("mobile", SearchFilter.Operator.EQ, mobile));
        }
        page = contactsService.queryPage(page);
        return Rets.success(page);
    }
}
