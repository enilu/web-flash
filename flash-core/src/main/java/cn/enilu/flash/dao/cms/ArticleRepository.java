
package cn.enilu.flash.dao.cms;

import cn.enilu.flash.bean.entity.cms.Article;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article,Long> {

    List<Article> findAllByIdChannel(Long idChannel);
}
