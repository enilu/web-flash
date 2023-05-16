package cn.enilu.flash.dao;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.entity.test.Boy;
import cn.enilu.flash.bean.vo.node.ZTreeNode;
import cn.enilu.flash.dao.test.BoyRepository;
import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.Lists;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 测试BaseRepository
 *
 * @author ：enilu
 * @date ：Created in 2020/3/13 0:01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseRepositoryTest extends BaseApplicationStartTest {

    /**
     * 使用BoyRepository 来测试BaseRepository的所有接口
     */
    @Autowired
    private BoyRepository boyRepository;

    @Test
    public void test_00_prepareData() {
        List<Boy> boyList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Boy boy = new Boy();
            boy.setBirthday("199" + i + "-02-10");
            boy.setHasGirFriend(i % 3 == 0);
            boy.setAge(30 - i);
            boy.setName("张三" + i);
            boyList.add(boy);
        }
        boyRepository.saveAll(boyList);
    }

    @Test
    public void test_01_queryBySql() {
        List<Map> list = boyRepository.queryMapBySql("select name,age from t_test_boy where age>25");
        System.out.println(JsonUtil.toJson(list));
        Assert.assertTrue(list.get(0) instanceof Map);

    }

    @Test
    public void test_02_queryBySql() {
        List<Boy> list = boyRepository.queryBySql("select * from t_test_boy where age>25");
        System.out.println(JsonUtil.toJson(list));
        Assert.assertTrue(list.get(0) instanceof Boy);

    }

    @Test
    public void test_03_getBySql() {
        Map map = boyRepository.getMapBySql("select name,age from t_test_boy where age=25");
        System.out.println(JsonUtil.toJson(map));
        Assert.assertTrue(map.get("age").toString().equals("25"));
    }

    @Test
    public void test_04_getBySql() {
        Boy boy = boyRepository.get("select * from t_test_boy where age=25");
        Assert.assertTrue(boy.getAge() == 25);
    }

    @Test
    public void test_05_getOne() {
        Boy boy = boyRepository.getOne(1L);
        Assert.assertTrue(boy.getAge() == 18);
    }

    @Test
    public void test_06_get() {
        Boy boy = boyRepository.get("select * from t_test_boy where age=30");
        Assert.assertTrue(boy.getAge() == 30);
    }

    //todo 测试有问题
    @Test
    @Transactional(readOnly = false)
    public void test_07_execute() {
        int ret = boyRepository.execute("update t_test_boy set name='李四'");
        System.out.println(ret);
        Assert.assertTrue(ret > 0);
    }

    @Test
    public void test_08_query() {
        List<Boy> list = boyRepository.query("select * from t_test_boy where name is not null");
        Assert.assertTrue(!list.isEmpty());

    }


    @Test
    public void test_09_queryObjBySql() {
        String sql = "SELECT id, pid AS pId, simplename AS name, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS open FROM t_sys_dept";
        List<ZTreeNode> list = (List<ZTreeNode>) boyRepository.queryObjBySql(sql, ZTreeNode.class);
        System.out.println(JsonUtil.toJson(list));
        Assert.assertTrue(list.get(0) instanceof ZTreeNode);

    }
}
