
package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.Task;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;

public interface TaskRepository extends BaseRepository<Task,Long> {

    long countByNameLike(String name);

    List<Task> findByNameLike(String name);
    List<Task> findAllByDisabled(boolean disable);
}
