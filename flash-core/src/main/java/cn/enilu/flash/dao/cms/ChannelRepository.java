
package cn.enilu.flash.dao.cms;

import cn.enilu.flash.bean.entity.cms.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChannelRepository extends PagingAndSortingRepository<Channel,Long>
        ,JpaRepository<Channel,Long>,JpaSpecificationExecutor<Channel> {
}
