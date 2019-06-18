package cn.enilu.flash.dao.message;


import cn.enilu.flash.bean.entity.message.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MessagetemplateRepository extends PagingAndSortingRepository<MessageTemplate,Long>
    , JpaRepository<MessageTemplate,Long>, JpaSpecificationExecutor<MessageTemplate>{
    MessageTemplate findByCode(String code);

    List<MessageTemplate> findByIdMessageSender(Long idMessageSender);
}

