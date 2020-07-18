package cn.enilu.flash.bean.vo.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 配合vue-treeselect使用的节点对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeSelectNode {
    private String id;
    private String label;
    private List<TreeSelectNode> children;
}
