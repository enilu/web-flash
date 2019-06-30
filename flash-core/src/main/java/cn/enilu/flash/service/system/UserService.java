package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.dao.system.UserRepository;
import cn.enilu.flash.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created  on 2018/3/23 0023.
 *
 * @author enilu
 */
@Service
public class UserService  extends BaseService<User,Long,UserRepository> {
    @Autowired
    private UserRepository userRepository;

    public User findByAccount(String userName) {
        return userRepository.findByAccount(userName);
    }
}
