package cn.enilu.flash.dao.message;


import cn.enilu.flash.bean.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;


public interface MessageRepository extends PagingAndSortingRepository<Message,Long>
    , JpaRepository<Message,Long>, JpaSpecificationExecutor<Message>{
    void deleteAllByIdIn(ArrayList<String> list);
}

