package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.OperationLog;
import cn.enilu.flash.dao.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface OperationLogRepository extends BaseRepository<OperationLog,Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from t_sys_operation_log")
    int clear();
}
