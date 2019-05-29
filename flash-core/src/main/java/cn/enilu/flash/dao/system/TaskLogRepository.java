
package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskLogRepository  extends PagingAndSortingRepository<TaskLog,Long>
        ,JpaSpecificationExecutor<TaskLog>
        ,JpaRepository<TaskLog,Long> {

}
