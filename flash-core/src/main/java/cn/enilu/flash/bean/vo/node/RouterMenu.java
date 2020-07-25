package cn.enilu.flash.bean.vo.node;

import cn.enilu.flash.utils.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/30 22:00
 */
@Data
public class RouterMenu {
    private Long id;
    private Long parentId;
    private String path;
    private String component;
    private String name;
    private Integer num;
    private Boolean hidden = false;
    private MenuMeta meta;
    private List<RouterMenu> children = Lists.newArrayList();

}
