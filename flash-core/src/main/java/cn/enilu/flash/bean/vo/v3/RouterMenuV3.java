package cn.enilu.flash.bean.vo.v3;

import cn.enilu.flash.utils.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/30 22:00
 */
@Data
public class RouterMenuV3 {
    private Long id;
    private Long parentId;
    private String path;
    private String component;
    private String name;
    private Integer num;
    private Boolean hidden = false;
    private MenuMetaV3 meta=new MenuMetaV3();
    private List<RouterMenuV3> children = Lists.newArrayList();

}
