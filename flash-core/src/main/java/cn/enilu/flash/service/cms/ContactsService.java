package cn.enilu.flash.service.cms;

import cn.enilu.flash.bean.entity.cms.Contacts;
import cn.enilu.flash.dao.cms.ContactsRepository;
import cn.enilu.flash.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactsService extends BaseService<Contacts,Long,ContactsRepository> {
}
