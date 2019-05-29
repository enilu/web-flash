package cn.enilu.flash.api.controller.cms;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.dictmap.CommonDict;
import cn.enilu.flash.bean.entity.cms.Article;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.dao.cms.ArticleRepository;
import cn.enilu.flash.service.cms.ArticleService;
import cn.enilu.flash.utils.Maps;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章管理
 */
@RestController
@RequestMapping("/article")
public class ArticleMgrController extends BaseController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑文章",key="name",dict = CommonDict.class)
    public Object save(){
        Article article = getFromJson(Article.class);
        if(article.getId()!=null){
            Article old = articleRepository.findById(article.getId()).get();
            old.setAuthor(article.getAuthor());
            old.setContent(article.getContent());
            old.setIdChannel(article.getIdChannel());
            old.setImg(article.getImg());
            old.setTitle(article.getTitle());
            articleRepository.save(old);
        }else {
            articleRepository.save(article);
        }
        return Rets.success();
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除文章",key="id",dict = CommonDict.class)
    public Object remove(Long id){
        articleRepository.deleteById(id);
        return Rets.success();
    }
    @RequestMapping(method = RequestMethod.GET)
    public Object get(@Param("id") Long id) {
        Article article = articleRepository.findById(id).get();
        return Rets.success(article);
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
        Page<Article> page = new PageFactory<Article>().defaultPage();

        page = articleService.findPage(page, Maps.newHashMap("cfgName",cfgName,"cfgValue",cfgValue));
        page.setRecords(page.getRecords());
        return Rets.success(page);
    }
}
