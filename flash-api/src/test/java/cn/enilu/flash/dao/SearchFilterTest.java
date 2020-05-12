package cn.enilu.flash.dao;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.entity.test.Boy;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.test.BoyService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：enilu
 * @date ：Created in 2020/5/12 18:14
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchFilterTest extends BaseApplicationStartTest {
    @Autowired
    private BoyService boyService;

    /**
     * 准备测试数据
     */
    @Test
    public void test_00_prepareData() {
        for (int i = 0; i < 10; i++) {
            Boy boy = new Boy();
            boy.setBirthday(i%3==0?"199" + i + "-02-10":null);
            boy.setHasGirFriend(i % 3 == 0);
            boy.setAge(30 - i);
            boy.setName("张三" + i);
            boyService.insert(boy);
        }
    }
    @Test
    public void test_01_isNull(){
        List<Boy> list = boyService.queryAll(SearchFilter.build("birthday", SearchFilter.Operator.ISNULL));
        System.out.println(Json.toJson(list));
        Assert.assertTrue(!list.isEmpty());
    }
    @Test
    public void test_02_isNotNull(){
        List<Boy> list = boyService.queryAll(SearchFilter.build("birthday", SearchFilter.Operator.ISNOTNULL));
        System.out.println(Json.toJson(list));
        Assert.assertTrue(!list.isEmpty());
    }
}
