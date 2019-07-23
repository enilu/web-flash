
package cn.enilu.flash.dao.cms;

import cn.enilu.flash.bean.entity.cms.Banner;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;

public interface BannerRepository extends BaseRepository<Banner,Long> {

    List<Banner> findAllByType(String type);
}
