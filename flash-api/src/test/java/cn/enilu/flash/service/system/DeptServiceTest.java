package cn.enilu.flash.service.system;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.vo.node.ZTreeNode;
import org.junit.Test;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created  on 2018/4/5 0005.
 *
 * @author enilu
 */
public class DeptServiceTest extends BaseApplicationStartTest {
    @Autowired
    private DeptService deptService;
    @Test
    public void tree() throws Exception {
        List<ZTreeNode> list = deptService.tree();
        for(ZTreeNode treeNode:list) {
            System.out.println(Json.toJson(treeNode));
        }
    }

}
