package cn.enilu.flash.api.controller.cms;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.dictmap.CommonDict;
import cn.enilu.flash.bean.entity.cms.Banner;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.dao.cms.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * banner管理
 */
@RestController
@RequestMapping("/banner")
public class BannerMgrController extends BaseController {
    @Autowired
    private BannerRepository bannerRepository;
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑banner",key="title",dict = CommonDict.class)
    public Object save(@ModelAttribute Banner banner){
        bannerRepository.save(banner);
        return Rets.success();
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除banner",key="id",dict = CommonDict.class)
    public Object remove(Long id){
        bannerRepository.deleteById(id);
        return Rets.success();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(){
        List<Banner> list = bannerRepository.findAll();
        return Rets.success(list);
    }
}
