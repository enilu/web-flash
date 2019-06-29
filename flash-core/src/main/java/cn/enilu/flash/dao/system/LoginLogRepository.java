package cn.enilu.flash.dao.system;

import cn.enilu.flash.bean.entity.system.LoginLog;
import cn.enilu.flash.dao.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface LoginLogRepository extends BaseRepository<LoginLog,Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from t_sys_login_log")
    int clear();
}
