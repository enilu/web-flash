package cn.enilu.flash.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.List;
import java.util.Map;

/**
 * @author ：enilu
 * @date ：Created in 2020/5/31 21:55
 */
public class JsonUtil {
    public static ObjectMapper objectMapper;
    public static  String toJsonForHuman(Object obj ){
        return Json.toJson(obj);
    }
    public static  String toJson(Object obj){
      return Json.toJson(obj, JsonFormat.compact());
    }
    public static  Object fromJson(String jsonStr){
        return StringUtil.isNotEmpty(jsonStr)?fromJson(Map.class,jsonStr):null;
    }

    public static <T> T fromJson (Class<T> valueType,String jsonStr) {
     return Json.fromJson(valueType,jsonStr);
    }
    public static <T> List<T> fromJsonAsList(Class<T> valueType,String jsonStr){
    return Json.fromJsonAsList(valueType,jsonStr);
    }


}
