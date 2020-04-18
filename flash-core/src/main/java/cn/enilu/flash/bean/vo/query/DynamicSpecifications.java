package cn.enilu.flash.bean.vo.query;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

/**
 * 将SearchFilter查询条件解析为jpa查询对象Predicate
 *
 * @author ：enilu
 * @date ：Created in 2019/6/30 16:04
 */
public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        SimpleSpecification<T> simpleSpecification = new SimpleSpecification<T>(filters);
        return simpleSpecification;
    }
}
