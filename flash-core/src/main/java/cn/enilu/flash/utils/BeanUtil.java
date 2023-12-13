package cn.enilu.flash.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.nutz.mapl.Mapl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2018/2/26 0026.
 *
 * @author enilu
 */
public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    /**
     * 记录每个修改字段的分隔符
     */
    public static final String SEPARATOR = ";;;";
    /**
     * 缓存字段名和中文注释对应关系的map
     */
    private static Map<String, String> fieldMap = cn.enilu.flash.utils.Maps.newHashMap();
    public static final Pattern COLUMN_DEFINITION_PATTERN = Pattern
            .compile("([A-Za-z]+)(?:\\(\\d+\\))?\\s*(?:(?:COMMENT|[Cc]omment)\\s+'(.*?)')?");

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = Mapl.maplistToT(map,clazz);
                list.add(bean);
            }
        }
        return list;
    }

    public static <T> List<T> objectToObjects(List<Object> objectList, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (objectList != null && objectList.size() > 0) {
            Object source = null;
            T bean = null;
            for (int i = 0, size = objectList.size(); i < size; i++) {
                source = objectList.get(i);
                bean = clazz.newInstance();
                BeanUtils.copyProperties(source, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 比较两个对象pojo1和pojo2,并输出不一致信息
     *
     * @param key
     * @param pojo1 旧数据
     * @param pojo2 新数据
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static String contrastObj(String key, Map<String,Object> pojo1 , Map<String,Object>  pojo2)
            {
        StringBuilder str = new StringBuilder(key).append("=").append(pojo2.get(key)).append(";;;");

        try {

            for(Map.Entry<String,Object> entry:pojo1.entrySet()){
                String entryKey = entry.getKey();
                Object oldVal = entry.getValue();
                Object newVal = pojo2.get(entryKey);
                if("createTime".equals(entryKey)||"createBy".equals(entryKey)||"modifyTime".equals(entryKey)||"modifyBy".equals(entryKey)||"id".equals(entryKey)){
                    continue;
                }
                if(StringUtil.isNullOrEmpty(newVal) && StringUtil.isNullOrEmpty(oldVal)){
                    continue;
                }
                if(String.valueOf(newVal).equals(String.valueOf(oldVal))){
                    continue;
                }
                str.append(entryKey + ":" + String.valueOf(oldVal) + "=>" + String.valueOf(newVal)).append(";");

            }

        } catch (Exception e) {
            logger.warn("保存业务日志异常",e);
        }
        return str.toString();
    }

    private static Method getReadMethod(String fieldName, Class clazz) {
        String methodName = "get" + StringUtil.firstCharToUpperCase(fieldName);
        Method method = null;
        ;
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (Exception e) {
            methodName = "is" + StringUtil.firstCharToUpperCase(fieldName);
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (Exception e1) { 
                e1.printStackTrace();
            } 
        }  

        return method;
    }
    /**
     * 从实体类的columDefinition中获取字段的中文注释，如果
     *
     * @param clazz
     * @param field
     * @return
     */
    public static String getFieldComment(Class clazz, Field field) {
        String key = clazz.getName() + field.getName();
        String comment = fieldMap.get(key);
        if (comment == null) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Column) {
                    Column columnAnno = (Column) annotation;
                    String columnDefinition = columnAnno.columnDefinition();
                    if (columnDefinition != null && !"".equals(columnDefinition.trim())) {
                        Matcher matcher = COLUMN_DEFINITION_PATTERN.matcher(columnDefinition.trim());
                        if (matcher.find()) {
                            comment = matcher.group(2);
                            fieldMap.put(key, comment);
                            break;
                        }
                    }
                }
            }
        }
        if (comment == null) {
            comment = field.getName();
            fieldMap.put(key, comment);
        }
        return comment;
    }

    /**
     * 解析多个key(逗号隔开的)
     */
    public static String parseMutiKey(Map<String, String> requests) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : requests.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        return sb.toString();

    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (StringUtil.isNullOrEmpty(o)) {
                return true;
            }
        }
        return false;
    }

}
