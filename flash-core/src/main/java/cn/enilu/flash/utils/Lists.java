package cn.enilu.flash.utils;


import net.sf.ehcache.hibernate.management.impl.BeanUtils;

import java.util.*;

/**
 * 集合工具类
 *
 * @author enilu
 */
public final class Lists {

    private Lists() {
    }


    /**
     * 碾平集合咯，主要针对集合元素为集合的情况有效果
     *
     * @param list
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> flatten(List<?> list) {
        List<T> result = new ArrayList<T>();
        for (Object o : list) {
            if (o instanceof List) {
                List<T> subResult = flatten((List<?>) o);
                result.addAll(subResult);
            } else {
                result.add((T) o);
            }
        }
        return result;
    }

    /**
     * 压缩集合，去掉集合中的null记录
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> compact(List<T> list) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 提取集合中指定属性值
     *
     * @param list
     * @param property
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> list, String property) {
        List<R> result = new ArrayList<R>();
        for (T t : list) {

            try {
                R r = (R) getProperty(t, property);
                result.add(r);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * 将集合转换为map
     *
     * @param list
     * @param keyProperty
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(List<V> list, String keyProperty) {
        Map<K, V> map = new HashMap<K, V>(100);
        for (V v : list) {

            try {
                K k = (K) getProperty(v, keyProperty);
                map.put(k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    /**
     * 移除与value不想等的值，原集合不发生变化
     *
     * @param list
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, String property, Object value) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            try {
                Object v = getProperty(t, property);
                if ((v == null && value == null) || (v != null && v.equals(value))) {
                    result.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * 移除与value相等的值, 原数组不发生变化.
     *
     * @param list
     * @param value
     * @return
     */
    public static <T> List<T> without(List<T> list, T value) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if ((value == null && t == null)
                    || (value != null && value.equals(t))) {
                continue;
            }
            result.add(t);
        }
        return result;
    }

    /**
     * 对集合去重，原集合不发生变化
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> List<T> uniq(List<T> input) {
        LinkedHashMap<T, T> map = new LinkedHashMap<>();
        for (T t : input) {
            map.put(t, t);
        }
        return new ArrayList<T>(map.values());
    }

    /**
     * 按照给定的集合（keys）进行排序
     *
     * @param input       要排序的集合
     * @param keyProperty 排序的属性
     * @param keys        给定的键值集合
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> List<T> sortBy(List<T> input, String keyProperty,
                                        List<K> keys) {
        if (input.isEmpty()) {
            return new ArrayList();
        }

        Map<K, T> map = toMap(input, keyProperty);
        List<T> result = new ArrayList<T>();
        for (K k : keys) {
            T t = map.get(k);
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 对集合进行分组
     *
     * @param input
     * @param keyProperty
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, List<V>> group(List<V> input, String keyProperty) {
        Map<K, List<V>> result = new HashMap<>(100);

        for (V v : input) {

            try {
                K k = (K) getProperty(v, keyProperty);
                List<V> list = result.get(k);
                if (list == null) {
                    list = new ArrayList<>();
                    result.put(k, list);
                }
                list.add(v);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 将数组按n个一份拆分.
     *
     * @param <T>
     * @param input
     * @param n
     * @return
     */
    public static <T> List<List<T>> group(List<T> input, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }

        int size = input.size();
        int m = (size + n - 1) / n;
        List<List<T>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<T> items = new ArrayList(n);
            int end = i < m - 1 ? n : size - i * n;
            for (int j = 0; j < end; j++) {
                items.add(input.get(i * n + j));
            }
            result.add(items);
        }
        return result;
    }



    public static boolean containAny(Set parent, Set child) {
        if (parent == null || child == null) {
            return false;
        }
        Iterator iter = child.iterator();
        while (iter.hasNext()) {
            return parent.contains(iter.next());
        }
        return false;
    }

    public static <V> List<V> newArrayList(V... vs) {
        List<V> list = new ArrayList<V>();
        for (V v : vs) {
            list.add(v);
        }
        return list;
    }

    private static Object getProperty(Object bean,String name){
        return BeanUtils.getBeanProperty(bean,name);
    }
}
