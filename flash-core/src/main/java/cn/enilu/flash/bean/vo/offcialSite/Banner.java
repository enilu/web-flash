package cn.enilu.flash.bean.vo.offcialSite;

import lombok.Data;

import java.util.List;

@Data
public class Banner {
    private Integer index = 0;
    private List<cn.enilu.flash.bean.entity.cms.Banner> list;

}
