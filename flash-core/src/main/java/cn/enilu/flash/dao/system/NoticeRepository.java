package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.Notice;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface NoticeRepository extends BaseRepository<Notice,Long> {
    List<Notice> findByTitleLike(String name);
}
