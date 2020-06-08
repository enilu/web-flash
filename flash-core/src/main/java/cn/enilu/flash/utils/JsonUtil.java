package cn.enilu.flash.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author ：enilu
 * @date ：Created in 2020/5/31 21:55
 */
public class JsonUtil {
    public static ObjectMapper objectMapper;

    public static  String toJson(Object obj){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static  Object fromJson(String jsonStr){
        return StringUtil.isNotEmpty(jsonStr)?fromJson(Map.class,jsonStr):null;
    }

    public static <T> T fromJson (Class<T> valueType,String jsonStr) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static <T> T fromJson(String jsonStr, TypeReference<T> valueTypeRef){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
