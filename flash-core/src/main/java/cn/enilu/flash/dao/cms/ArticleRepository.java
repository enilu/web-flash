
package cn.enilu.flash.dao.cms;

import cn.enilu.flash.bean.entity.cms.Article;
import cn.enilu.flash.dao.BaseRepository;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article,Long> {
    /**
     * 查询指定栏目下所有文章列表
     * @param idChannel
     * @return
     */
    List<Article> findAllByIdChannel(Long idChannel);
}
