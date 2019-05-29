
package cn.enilu.flash.dao.cms;

import cn.enilu.flash.bean.entity.cms.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BannerRepository extends PagingAndSortingRepository<Banner,Long>
        ,JpaRepository<Banner,Long>,JpaSpecificationExecutor<Banner> {

    List<Banner> findAllByType(String type);
}
