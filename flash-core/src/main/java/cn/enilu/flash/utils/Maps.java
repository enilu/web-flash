package cn.enilu.flash.utils;


import cn.enilu.flash.bean.entity.system.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 工具类
 *
 * @author enilu
 */
public final class Maps {

    private Maps() {
    }

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>(100);
    }

    public static <K, V> Map<K, V> newHashMap(K k, V v) {
        HashMap<K, V> map = new HashMap<K, V>(100);
        map.put(k, v);
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newHashMap(K k, V v,
                                                  Object... extraKeyValues) {
        if (extraKeyValues.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        Map<K,V> map = new HashMap<K,V>(100);
        map.put(k,v);

        for (int i = 0; i < extraKeyValues.length; i += 2) {
            k = (K) extraKeyValues[i];
            v = (V) extraKeyValues[i + 1];
            map.put(k, v);
        }
        return map;
    }
    public static <T> T mapToObj(Map source, Class<T> target)  {
        return JsonUtil.fromJson(target,JsonUtil.toJson(source));
    }
    public static  Map objToMap(Object source){
        return JsonUtil.fromJson(Map.class,JsonUtil.toJson(source));
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAccount("zhangsan");
        user.setPassword("123434");
        Map map = objToMap(user);
        System.out.println(JsonUtil.toJson(map));
        System.out.println(JsonUtil.toJson(mapToObj(map,User.class)));
    }

}