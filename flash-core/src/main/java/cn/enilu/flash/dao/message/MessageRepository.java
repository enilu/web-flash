package cn.enilu.flash.dao.message;


import cn.enilu.flash.bean.entity.message.Message;
import cn.enilu.flash.dao.BaseRepository;

import java.util.ArrayList;


public interface MessageRepository extends BaseRepository<Message,Long> {
    void deleteAllByIdIn(ArrayList<String> list);
}

