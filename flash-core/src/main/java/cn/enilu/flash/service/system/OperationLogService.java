package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.OperationLog;
import cn.enilu.flash.dao.system.OperationLogRepository;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.factory.Page;
import com.google.common.base.Strings;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created  on 2018/3/26 0026.
 *
 * @author enilu
 */
@Service
public class OperationLogService {

    @Autowired
    private OperationLogRepository operationLogRepository;

    public Page getOperationLogs(Page<OperationLog> page, final String beginTime, final String endTime, final String logName, final String logType) {
        Pageable pageable = null;
        if(page.isOpenSort()) {
            pageable = new PageRequest(page.getCurrent()-1, page.getSize(), page.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, page.getOrderByField());
        }else{
            pageable = new PageRequest(page.getCurrent()-1,page.getSize(),Sort.Direction.DESC,"id");
        }

        org.springframework.data.domain.Page<OperationLog> operationLogPage = operationLogRepository.findAll(new Specification<OperationLog>(){

            @Override
            public Predicate toPredicate(Root<OperationLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(!Strings.isNullOrEmpty(beginTime)){
                    list.add(criteriaBuilder.greaterThan(root.get("createTime").as(Date.class), DateUtil.parseDate(beginTime)));
                }
                if(!Strings.isNullOrEmpty(endTime)){
                    list.add(criteriaBuilder.lessThan(root.get("createTime").as(Date.class), DateUtil.parseDate(endTime)));
                }
                if(!Strings.isNullOrEmpty(logName)){
                    list.add(criteriaBuilder.like(root.get("logname").as(String.class),logName));
                }
                if(!Strings.isNullOrEmpty(logType)){{
                    list.add(criteriaBuilder.equal(root.get("logtype").as(String.class),logType));
                }}
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        page.setTotal(Integer.valueOf(operationLogPage.getTotalElements()+""));
        page.setRecords(operationLogPage.getContent());
        return page;
    }

    public OperationLog get(Long id) {
        Optional<OperationLog> optional = operationLogRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
