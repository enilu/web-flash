package cn.enilu.flash.dao.system;


import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.dao.BaseRepository;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface UserRepository extends BaseRepository<User,Long> {
    User findByAccount(String account);

    User findByAccountAndStatusNot(String account, Integer status);
}
