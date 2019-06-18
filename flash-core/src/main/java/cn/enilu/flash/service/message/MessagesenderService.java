package cn.enilu.flash.service.message;


import cn.enilu.flash.bean.entity.message.MessageSender;
import cn.enilu.flash.bean.entity.message.MessageTemplate;
import cn.enilu.flash.dao.message.MessagesenderRepository;
import cn.enilu.flash.dao.message.MessagetemplateRepository;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MessagesenderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessagesenderRepository messageSenderRepository;
    @Autowired
    private MessagetemplateRepository messagetemplateRepository;

    public void save(MessageSender messageSender){
        messageSenderRepository.save(messageSender);
    }
    public boolean  delete(Long id){
        List<MessageTemplate> templateList = messagetemplateRepository.findByIdMessageSender(id);
        if(templateList.isEmpty()) {
            messageSenderRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Page<MessageSender> findPage(Page<MessageSender> page, HashMap<String, String> params) {
        Pageable pageable  = new PageRequest(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC,"id");
        org.springframework.data.domain.Page<MessageSender> pageResult = messageSenderRepository.findAll(new Specification<MessageSender>() {
            @Override
            public Predicate toPredicate(Root<MessageSender> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                    //根据实际业务构建复杂的查询条件
                    for(Map.Entry<String,String> entry:params.entrySet()){
                        if (StringUtils.isNotEmpty(entry.getValue())) {
                            list.add(criteriaBuilder.equal(root.get(entry.getKey()).as(String.class), entry.getValue()));
                        }
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
            }, pageable);
        page.setTotal(Integer.valueOf(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }

    public List<MessageSender> queryAll(){
        return messageSenderRepository.findAll();
    }
}

