package cn.enilu.flash.service.system;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.entity.system.Dept;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.utils.Lists;
import org.junit.Test;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created  on 2018/4/5 0005.
 *
 * @author enilu
 */
public class BaseServiceTest extends BaseApplicationStartTest {
    @Autowired
    private DeptService deptService;
    @Test
    public void queryAll() throws Exception {
        List<SearchFilter> filters = Lists.newArrayList();
        filters.add(SearchFilter.build("pid",1));
        filters.add(SearchFilter.build("pid",0, SearchFilter.Join.and));
        List<Dept> list = deptService.queryAll(filters);
        System.out.println(Json.toJson(list));
    }

}
