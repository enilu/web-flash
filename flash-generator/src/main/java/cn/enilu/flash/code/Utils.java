package cn.enilu.flash.code;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类<br>
 * </p> Copyright by easecredit.com<br>
 * 作者: zhangtao <br>
 * 创建日期: 16-7-27<br>
 */
public class Utils {
    /**
     * 将以“_”分割的单词转换为首字母小写驼峰格式
     *
     * @param src
     * @return
     */
    public static String lowerCamel(String src) {
        src = src.toLowerCase();
        StringBuilder result = new StringBuilder();
        for (String sitem : src.split("_")) {
            if (result.toString().length() == 0) {
                result.append(sitem);
            } else {
                result.append(Strings.upperFirst(sitem));
            }
        }
        return result.toString();
    }

    /**
     * 以“_”分割的单词转换为首字母大写驼峰格式
     *
     * @param src
     * @return
     */
    public static String upperCamel(String src) {
        if (!src.contains("_")) {
            return src;
        }
        src = src.toLowerCase();
        StringBuilder result = new StringBuilder();
        for (String sitem : src.split("_")) {
            if (result.toString().length() == 0) {
                result.append(Strings.upperFirst(sitem));
            } else {
                result.append(Strings.upperFirst(sitem));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(Json.toJson(Lang.collection2array(list)));
        System.out.println(Json.toJson(list.toArray()));
        System.out.println(upperCamel("AAAA_BBBB"));
    }
}
