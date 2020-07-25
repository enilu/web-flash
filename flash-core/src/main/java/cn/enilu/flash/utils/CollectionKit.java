package cn.enilu.flash.utils;


import cn.enilu.flash.utils.cache.exception.ToolBoxException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合相关工具类，包括数组
 *
 * @author xiaoleilu
 */
public class CollectionKit {

    private CollectionKit() {
        // 静态类不可实例化
    }


    /**
     * 新建一个HashMap
     *
     * @return HashMap对象
     */
    public static <T, K> HashMap<T, K> newHashMap() {
        return new HashMap<T, K>(100);
    }

    /**
     * 新建一个HashMap
     *
     * @param size 初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
     * @return HashMap对象
     */
    public static <T, K> HashMap<T, K> newHashMap(int size) {
        return new HashMap<T, K>((int) (size / 0.75));
    }

    /**
     * 将新元素添加到已有数组中<br/>
     * 添加新元素会生成一个新的数组，不影响原数组
     *
     * @param buffer     已有数组
     * @param newElement 新元素
     * @return 新数组
     */
    public static <T> T[] append(T[] buffer, T newElement) {
        T[] t = resize(buffer, buffer.length + 1, newElement.getClass());
        t[buffer.length] = newElement;
        return t;
    }

    /**
     * 生成一个新的重新设置大小的数组
     *
     * @param buffer        原数组
     * @param newSize       新的数组大小
     * @param componentType 数组元素类型
     * @return 调整后的新数组
     */
    public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
        T[] newArray = newArray(componentType, newSize);
        System.arraycopy(buffer, 0, newArray, 0, buffer.length >= newSize ? newSize : buffer.length);
        return newArray;
    }

    /**
     * 新建一个空数组
     *
     * @param componentType 元素类型
     * @param newSize       大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }


    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return false == isEmpty(array);
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合是否为非空
     *
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return false == isEmpty(collection);
    }

    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Map是否为非空
     *
     * @param map 集合
     * @return 是否为非空
     */
    public static <T> boolean isNotEmpty(Map<?, ?> map) {
        return false == isEmpty(map);
    }


    /**
     * 判定给定对象是否为数组类型
     *
     * @param obj 对象
     * @return 是否为数组类型
     */
    public static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    /**
     * 数组或集合转String
     *
     * @param obj 集合或数组对象
     * @return 数组字符串，与集合转字符串格式相同
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (isArray(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception e) {
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return Arrays.toString((long[]) obj);
                    case "int":
                        return Arrays.toString((int[]) obj);
                    case "short":
                        return Arrays.toString((short[]) obj);
                    case "char":
                        return Arrays.toString((char[]) obj);
                    case "byte":
                        return Arrays.toString((byte[]) obj);
                    case "boolean":
                        return Arrays.toString((boolean[]) obj);
                    case "float":
                        return Arrays.toString((float[]) obj);
                    case "double":
                        return Arrays.toString((double[]) obj);
                    default:
                        throw new ToolBoxException(e);
                }
            }
        }
        return obj.toString();
    }
}
