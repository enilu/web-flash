package cn.enilu.flash.bean.vo.offcialsite;

import lombok.Data;

@Data
public class News {
    /**
     * 资讯标题
     */
    private String desc;
    /**
     * 详情链接
     */
    private String url;
    /**
     * 图片地址
     */
    private String src;
    public News(){

    }

    public News(String desc, String url, String src) {
        this.desc = desc;
        this.url = url;
        this.src = src;
    }
}
