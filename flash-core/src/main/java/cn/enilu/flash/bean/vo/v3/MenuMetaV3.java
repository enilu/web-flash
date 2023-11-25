package cn.enilu.flash.bean.vo.v3;

import lombok.Data;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/30 22:01
 */
@Data
public class MenuMetaV3 {
    private String title;
    private String icon;

    private Boolean isAffix;
    private Boolean isFull=false;
    private Boolean isHide=false;
    private Boolean isKeepAlive=true;
    private Boolean isLink=null;
}
