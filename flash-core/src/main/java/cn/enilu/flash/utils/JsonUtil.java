package cn.enilu.flash.utils;

import cn.enilu.flash.bean.entity.system.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 封装json工具类<br>
 *  通过该类减少项目中对特定的json库依赖，方便统一切换json库，目前使用jackson
 * @author ：enilu
 * @date ：Created in 2020/5/31 21:55
 */
public class JsonUtil {
    public static  String toJsonForHuman(Object obj ){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static  String toJson(Object obj){
        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }
    public static  String toJsonNotNull(Object obj){
        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.writeValue(sw, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static <T> T fromJson (Class<T> klass,String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, klass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static <T> List<T> fromJsonAsList(Class<T> klass,String jsonStr){
        ObjectMapper mapper = new ObjectMapper();
        List<T> objList = null;
        try {
            JavaType t = mapper.getTypeFactory().constructParametricType(
                    List.class, klass);
            objList = mapper.readValue(jsonStr, t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objList;
    }

    /**
     * 判断给定的字符串是否是json格式
     * @param jsonStr
     * @return
     */
    public static boolean isJson(String jsonStr) {
        try {
            if (jsonStr.startsWith("{")) {
                fromJson(Map.class, jsonStr);
            } else {
                fromJsonAsList(Map.class, jsonStr);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        User user = new User();
        user.setAccount("admin");
        user.setCreateTime(new Date());

        String json = toJson(user);

        System.out.println("压缩后的json字符串：========》");
        System.out.println(json);

        System.out.println("方便调试查看的json字符串：===》");
        System.out.println(toJsonForHuman(user));

        System.out.println("不输出值为null的json字符串：==》");
        System.out.println(toJsonNotNull(user));

        User user2 = fromJson(User.class,json);

        System.out.println("json字符串转对象：==========》");
        System.out.println(user2.getAccount()+user2.getCreateTime());

        List<User> users = Lists.newArrayList(user);
        String jsons = toJson(users);
        List<User> users2 = fromJsonAsList(User.class,jsons );
        System.out.println("转换为集合后的集合长度：=======》");
        System.out.println(users2.size());

        System.out.println(isJson(json));
        System.out.println(isJson(jsons));
        System.out.println(isJson("{\"aaaa\":}"));
    }

}
