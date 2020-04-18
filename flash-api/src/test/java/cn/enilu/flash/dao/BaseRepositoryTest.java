package cn.enilu.flash.dao;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.dao.system.UserRepository;
import cn.enilu.flash.vo.UserVo;
import org.junit.Test;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author ：enilu
 * @date ：Created in 2020/3/13 0:01
 */
public class BaseRepositoryTest  extends BaseApplicationStartTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void queryListMapBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex";
        List<Map> list = userRepository.queryBySql(sql);
        System.out.println(Json.toJson(list));
    }
    @Test
    public void getMapBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex  having sex=1";
        Map ret =  userRepository.getBySql(sql);
        System.out.println(Json.toJson(ret));
    }
    @Test
    public void queryListBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex";
        List list = userRepository.queryBySql(sql, UserVo.class);
        System.out.println(Json.toJson(list));
    }
    @Test
    public void getObjectBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex  having sex=1";
        UserVo ret = (UserVo) userRepository.getBySql(sql, UserVo.class);
        System.out.println(Json.toJson(ret));
    }

    public void query(   ){
        String sql = "select * from t_sys_user where id in (1,2,3)";
        List<User> list = userRepository.query(sql);
        System.out.println(Json.toJson(list));
    }


}
