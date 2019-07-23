package cn.enilu.flash.dao.message;


import cn.enilu.flash.bean.entity.message.MessageTemplate;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;


public interface MessagetemplateRepository extends BaseRepository<MessageTemplate,Long> {
    MessageTemplate findByCode(String code);

    List<MessageTemplate> findByIdMessageSender(Long idMessageSender);
}

