package cn.enilu.flash.dao.message;


import cn.enilu.flash.bean.entity.message.MessageSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface MessagesenderRepository extends PagingAndSortingRepository<MessageSender,Long>
    , JpaRepository<MessageSender,Long>, JpaSpecificationExecutor<MessageSender>{
}

