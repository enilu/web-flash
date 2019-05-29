package cn.enilu.flash.service.cms;

import cn.enilu.flash.bean.enumeration.cms.BannerTypeEnum;
import cn.enilu.flash.bean.vo.offcialSite.Banner;
import cn.enilu.flash.dao.cms.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    /**
     * 查询首页banner数据
     * @return
     */
    public Banner queryIndexBanner(){
    return queryBanner(BannerTypeEnum.INDEX.getValue());
    }

    public Banner queryBanner(String type){
        Banner banner = new Banner();
        List<cn.enilu.flash.bean.entity.cms.Banner> bannerList = bannerRepository.findAllByType(type);
        banner.setIndex(0);
        banner.setList(bannerList);
        return  banner;
    }
}
