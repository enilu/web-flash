package cn.enilu.flash.bean.vo.query;

import cn.enilu.flash.utils.Maps;
import cn.enilu.flash.utils.StringUtil;

import java.util.Map;

/**
 * 查询条件封装类
 *
 * @author ：enilu
 * @date ：Created in 2019/6/30 16:02
 */
public class SearchFilter {
    public enum Operator {
        EQ, NE, LIKE, LIKEL, LIKER, GT, LT, GTE, LTE, IN, NOTIN, ISNULL, ISNOTNULL, BETWEEN
    }

    public enum Join {
        and, or
    }

    public Join join = Join.and;
    public String fieldName;
    public Object value;
    public Operator operator;

    public static SearchFilter build(String fieldName, Object value) {
        return new SearchFilter(fieldName, Operator.EQ, value);
    }

    public static SearchFilter build(String fieldName, Operator operator) {
        return new SearchFilter(fieldName, operator);
    }

    public static SearchFilter build(String fieldName, Operator operator, Object value) {
        return new SearchFilter(fieldName, operator, value);
    }


    public static SearchFilter build(String fieldName, Object value, Join join) {
        return build(fieldName, Operator.EQ, value, join);

    }
    public static SearchFilter build(String fieldName, Operator operator,  Object value, Join join) {
        return new SearchFilter(fieldName, operator, value, join);

    }

    public SearchFilter(String fieldName, Operator operator) {
        this.fieldName = fieldName;
        this.operator = operator;

    }

    public SearchFilter(String fieldName, Operator operator, Object value) {
        if (!StringUtil.isNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
        }
    }

    public SearchFilter(String fieldName, Operator operator, Object value, Join join) {
        if (!StringUtil.isNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
            this.join = join;
        }
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
			/*if (StringUtil.isBlank((String) value)) {
				continue;
			}*/

            // 拆分operator与filedAttribute
            String[] names = StringUtil.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }
}
