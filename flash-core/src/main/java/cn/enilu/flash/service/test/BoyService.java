package cn.enilu.flash.service.test;


import cn.enilu.flash.bean.entity.test.Boy;
import cn.enilu.flash.dao.test.BoyRepository;
import cn.enilu.flash.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoyService extends BaseService<Boy, Long, BoyRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BoyRepository boyRepository;

}

