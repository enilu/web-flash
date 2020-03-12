package cn.enilu.flash;

import cn.enilu.flash.dao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Name: ServiceConfiguration<br>
 * User: Yao<br>
 * Date: 2018/2/27<br>
 * Time: 13:54<br>
 */
@EnableCaching
@SpringBootApplication
@EntityScan(basePackages="cn.enilu.flash.bean.entity")
@EnableJpaRepositories(basePackages= "cn.enilu.flash.dao", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ServiceConfiguration {



    public static void main(String[] args) {
        SpringApplication.run(ServiceConfiguration.class);
    }
}
