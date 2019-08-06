package cn.enilu.flash.bean.vo.offcialsite;

import lombok.Data;

import java.util.List;

@Data
public class BannerVo {
    private Integer index = 0;
    private List<cn.enilu.flash.bean.entity.cms.Banner> list;

}
