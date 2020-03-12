package cn.enilu.flash;

import cn.enilu.flash.api.ApiApplication;
import cn.enilu.flash.dao.BaseRepositoryFactoryBean;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Name: ApplicationStartTest<br>
 * User: Yao<br>
 * Date: 2018/1/24<br>
 * Time: 16:26<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@EntityScan(basePackages="cn.enilu.flash.bean.entity")
@EnableJpaRepositories(basePackages= "cn.enilu.flash.dao", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@ComponentScan(basePackages = "cn.enilu.flash")
@TestPropertySource(locations = {"classpath:application-test.properties"})

public class BaseApplicationStartTest {
    protected final Logger log= LoggerFactory.getLogger(getClass());

}
