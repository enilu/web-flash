package cn.enilu.flash.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Name: ApplicationStartTest<br>
 * User: Yao<br>
 * Date: 2018/1/24<br>
 * Time: 16:26<br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConfiguration.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "cn.enilu.flash")
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class BaseApplicationStartTest {
    protected final Logger log= LoggerFactory.getLogger(getClass());



}
