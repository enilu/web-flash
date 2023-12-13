package cn.enilu.flash.api.config;

import org.hibernate.dialect.MySQL57InnoDBDialect;
import org.hibernate.dialect.MySQL8Dialect;

/**
 * description
 *
 * @author ：enilu
 * @date ： 2023/12/13 12:01
 */
public class DefaultMySQL8InnoDBDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
