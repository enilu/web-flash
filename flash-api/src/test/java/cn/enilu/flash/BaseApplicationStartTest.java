package cn.enilu.flash;

import cn.enilu.flash.api.ApiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试模块基类<br>
 * 不要直接在该类中编写测试代码，而是通过继承该类。 参考DeptServiceTest,BaseRepositoryTest等
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@TestPropertySource(locations = {"classpath:application.yml"})
public class BaseApplicationStartTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Test
    public void makeTestPass() {

    }

}
