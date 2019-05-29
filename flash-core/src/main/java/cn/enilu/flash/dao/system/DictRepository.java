
package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.Dict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict, Long> {
    List<Dict> findByPid(Long pid);
    List<Dict> findByNameAndPid(String name,Long pid);

    List<Dict> findByNameLike(String name);
}
