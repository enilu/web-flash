package cn.enilu.flash.utils;

import cn.enilu.flash.bean.entity.system.User;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 封装json工具类<br>
 *  通过该类减少项目中对特定的json库依赖，方便统一切换json库，目前使用nutz的Json工具类
 * @author ：enilu
 * @date ：Created in 2020/5/31 21:55
 */
public class JsonUtil {
    public static  String toJsonForHuman(Object obj ){
        return Json.toJson(obj);
    }
    public static  String toJson(Object obj){
      return Json.toJson(obj, JsonFormat.compact());
    }
    public static  Object fromJson(String jsonStr){
        return StringUtil.isNotEmpty(jsonStr)?Json.fromJson(jsonStr):null;
    }

    public static <T> T fromJson (Class<T> valueType,String jsonStr) {
     return Json.fromJson(valueType,jsonStr);
    }
    public static <T> List<T> fromJsonAsList(Class<T> valueType,String jsonStr){
    return Json.fromJsonAsList(valueType,jsonStr);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAccount("admin");
        user.setCreateTime(new Date());

        String json = toJson(user);

        System.out.println("压缩后的json字符串：=======》");
        System.out.println(json);

        System.out.println("方便调试查看的json字符串：==》");
        System.out.println(toJsonForHuman(user));

        User user2 = fromJson(User.class,json);

        System.out.println("json字符串转对象：==========》");
        System.out.println(user2.getAccount()+user2.getCreateTime());

        Object obj = fromJson(json);

        System.out.println("json字符串转换为map========》");
        System.out.println(obj instanceof Map);



        List<User> users = Lists.newArrayList(user);
        String jsons = toJson(users);
        List<User> users2 = fromJsonAsList(User.class,jsons );
        System.out.println("转换为集合后的集合长度：=======》");
        System.out.println(users2.size());

        Object obj2 = fromJson(jsons);
        System.out.println("json字符串转换为List========》");
        System.out.println(obj2 instanceof List);

    }

}
