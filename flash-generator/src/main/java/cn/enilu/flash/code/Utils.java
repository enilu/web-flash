package cn.enilu.flash.code;

import org.nutz.lang.Strings;

/**
 * 工具类<br>
 * </p> Copyright by easecredit.com<br>
 * 作者: zhangtao <br>
 * 创建日期: 16-7-27<br>
 */
public class Utils {
    /**
     * 将以“_”分割的单词转换为首字母小写驼峰格式
     * @param src
     * @return
     */
    public static String lowerCamel(String src){
        src = src.toLowerCase();
        StringBuilder result = new StringBuilder();
        for(String sitem:src.split("_")){
            if(result.toString().length()==0){
                result.append(sitem);
            }else{
                result.append(Strings.upperFirst(sitem));
            }
        }
        return result.toString();
    }

    /**
     * 以“_”分割的单词转换为首字母大写驼峰格式
     * @param src
     * @return
     */
    public static String upperCamel(String src){
        if(!src.contains("_")){
            return src;
        }
        src = src.toLowerCase();
        StringBuilder result = new StringBuilder();
        for(String sitem:src.split("_")){
            if(result.toString().length()==0){
                result.append(Strings.upperFirst(sitem));
            }else{
                result.append(Strings.upperFirst(sitem));
            }
        }
        return result.toString();
    }
    public static  void main(String[] args){
        System.out.println(upperCamel("AAAA_BBBB"));
    }
}
