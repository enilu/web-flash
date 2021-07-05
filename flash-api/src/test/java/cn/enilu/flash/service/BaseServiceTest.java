package cn.enilu.flash.service;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.entity.test.Boy;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.test.BoyService;
import cn.enilu.flash.utils.Lists;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试基础服务类
 *
 * @author ：enilu
 * @date ：Created in 2020/5/4 23:38
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseServiceTest extends BaseApplicationStartTest {
    @Autowired
    private BoyService boyService;

    @Test
    public void test_01_insert() {
        for (int i = 0; i < 19; i++) {
            Boy boy = new Boy();
            boy.setName("李四");
            boy.setAge(18 + i);
            boy.setHasGirFriend(i % 3 == 0);
            boyService.insert(boy);
        }
    }

    @Test
    public void test_02_get() {
        Long id = boyService.queryAll().get(0).getId();
        Boy boy = boyService.get(id);
        Assert.assertTrue(boy.getId().intValue() == id.intValue());
    }

    @Test
    public void test_03_get() {
        SearchFilter searchFilter = SearchFilter.build("name", "李四");
        Boy boy = boyService.get(searchFilter);
        Assert.assertTrue(boy.getAge() == 18);
    }

    @Test
    public void test_04_get() {
        List<SearchFilter> filters = Lists.newArrayList(
                SearchFilter.build("name", "李四"),
                SearchFilter.build("age", SearchFilter.Operator.LT, 19)
        );
        Boy boy = boyService.get(filters);
        Assert.assertTrue(boy.getAge() == 18);
    }

    @Test
    public void test_05_query() {
        List<Long> ids = Lists.newArrayList(3L, 5L);
        List<Boy> list = boyService.query(ids);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void test_06_queryAll() {
        List<Boy> list = boyService.queryAll();
        Assert.assertTrue(list.size() > 5);
    }

    @Test
    public void test_07_queryPage() {
    }

    @Test
    public void test_08_queryAll() {
    }

    @Test
    public void test_09_queryAll() {
    }

    @Test
    public void test_10_queryAll() {
    }

    @Test
    public void test_11_queryAll() {
    }

    @Test
    public void test_12_count() {
        long count = boyService.count(SearchFilter.build("name", "李四"));
        Assert.assertTrue(count >1);
    }

    @Test
    public void test_13_count() {
        long count = boyService.count(Lists.newArrayList(SearchFilter.build("name", "李四")));
        Assert.assertTrue(count >1);
    }

    @Test
    public void test_14_update() {
        Boy boy = boyService.get(1L);
        boolean pre = boy.getHasGirFriend();
        boy.setHasGirFriend(!pre);
        Assert.assertTrue(pre != boy.getHasGirFriend());
    }

    @Test
    public void test_15_delete() {
        List<Boy> list = boyService.queryAll();
        boyService.delete(1L);
    }

    @Test
    public void test_16_delete1() {
        List<Long> ids = Lists.newArrayList(18L, 19L);
        boyService.delete(ids);
    }


    @Test
    public void test_17_clear() {
        boyService.clear();
        boyService.truncate();
        long count = boyService.count(Lists.newArrayList());
        Assert.assertTrue(count == 0);
    }
}
