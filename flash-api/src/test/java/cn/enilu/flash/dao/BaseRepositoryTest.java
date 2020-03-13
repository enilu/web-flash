package cn.enilu.flash.dao;

import cn.enilu.flash.BaseApplicationStartTest;
import cn.enilu.flash.vo.UserVo;
import cn.enilu.flash.dao.system.UserRepository;
import org.junit.Test;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：enilu
 * @date ：Created in 2020/3/13 0:01
 */
public class BaseRepositoryTest  extends BaseApplicationStartTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void queryBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex";
        List list = userRepository.queryBySql(sql, UserVo.class);
        System.out.println(Json.toJson(list));
    }
    @Test
    public void getBySql(){
        String sql = "select sex,count(1) as count from t_sys_user group by sex  having sex=1";
        UserVo ret = (UserVo) userRepository.getBySql(sql, UserVo.class);
        System.out.println(Json.toJson(ret));
    }


}
