package cn.enilu.flash.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author ：enilu
 * @date ：Created in 2019/10/29 22:48
 */
public class RandomUtil {
    /**
     * 获取随机位数的字符串
     *
     * @author fengshuonan
     * @Date 2017/8/24 14:09
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return getRandomString(length,base);
    }
    public static String getRandomString(int length,String base) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomNum(int length) {
        String base = "0123456789";
        return getRandomString(length,base);
    }
    public static String getRandomChar(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz";
        return getRandomString(length,base);
    }
    public static String getRandomSpecialChar(int length) {
        String base = "@&._%$*!";
        return getRandomString(length,base);
    }
    public static String getRandomPassword() {
        char[] arr1 =  getRandomChar(8).toCharArray();
        char[] arr2 = getRandomSpecialChar(1).toCharArray();
        char[] arr3 = getRandomNum(3).toCharArray();
        List<Character> list = Lists.newArrayList();
        for(char c:arr1){
            list.add(c);
        }
        for(char c:arr2){
            list.add(c);
        }
        for(char c:arr3){
            list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
