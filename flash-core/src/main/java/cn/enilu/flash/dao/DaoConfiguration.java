package cn.enilu.flash.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Name: DabConfiguration<br>
 * User: Yao<br>
 * Date: 2018/2/27<br>
 * Time: 13:54<br>
 */
@Configuration
@EnableJpaRepositories("cn.enilu.flash.dao")
public class DaoConfiguration {
}
