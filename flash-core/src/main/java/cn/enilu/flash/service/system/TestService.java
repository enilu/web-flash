package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.dao.system.UserRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created  on 2021/6/2.
 *
 * @author enilu
 */
@Service
public class TestService extends BaseService<User, Long, UserRepository> {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private UserRepository userRepository;

    public void lock() {
        User user = userRepository.findForUpdate();
        logger.info(user.getEmail());
        user.setEmail("test@qq.com");
        userRepository.save(user);
        logger.info(user.getEmail());
    }
    @Transactional
    public void userTrans() {
        User user = new User();
        user.setAccount("test111");
        userRepository.save(user);

        User user2 = userRepository.findByAccount("test111");
        logger.info(JsonUtil.toJson(user2));
        String a = null;
        System.out.println(a.length());
    }

    public void noTrans() {
        User user = new User();
        user.setAccount("test111");
        userRepository.save(user);
        User user2 = userRepository.findByAccount("test111");
        logger.info(JsonUtil.toJson(user2));
        String a = null;
        System.out.println(a.length());
    }
}
