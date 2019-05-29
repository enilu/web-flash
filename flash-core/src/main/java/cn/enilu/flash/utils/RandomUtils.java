package cn.enilu.flash.utils;


import java.text.DecimalFormat;
import java.util.*;

/**
 * 随机数工具
 */
public class RandomUtils {

    private static char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static Random ran;

    static {
        ran = new Random();
        ran.setSeed(System.currentTimeMillis());
    }

    public static char nextChar() {
        return alpha[Math.abs(ran.nextInt()) % 36];
    }

    public static String nextChar(int i) {
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < i; j++) {
            sb.append(nextChar());
        }

        return sb.toString();
    }


    /**
     * 产生普通的随机数
     * fmt: #0.00 数值格式
     */
    public static String getNormalRandom(int range, String fmt) {
        DecimalFormat format = new DecimalFormat(fmt);
        double randomNum = Math.random() * 20;
        return format.format(randomNum);
    }

    /**
     * 产生指定位数的字符随机字符串
     */
    public static String getCharacterRandom(int length) {
        return getRandom("a-z,A-Z", length);
    }

    /**
     * 产生指定位数的数字随机字符串
     */
    public static String getNumberRandom(int length) {
        return getRandom("1-9", length);
    }

    /**
     * 产生指定范围和位数的随机字符串
     * range:a-z,A-Z,0-9
     */
    public static String getRandom(String range, int length) {
        StringBuffer result = new StringBuffer();
        List<Character> randomRange = new ArrayList<Character>();
        try {
            if (range != null && range.trim().length() > 0) {
                String[] rangeGroup = range.split(",");
                for (String group : rangeGroup) {
                    String[] charStr = group.split("-");
                    for (char i = charStr[0].charAt(0); i <= charStr[1].charAt(0); i++) {
                        randomRange.add(i);
                    }
                }
            }
            for (int i = 0; i < length; i++) {
                Object randomObj = randomRange.get(new Random().nextInt(randomRange.size()));
                result.append(randomObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * 产生36位的UUID随机字符串
     */
    public static String getUUIDRandom() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生可定制的时间戳随机字符串
     */
    public static String getTimestampRandom(String head, String tail, int tailBit) {
        return head + DateUtil.getAllTime() + tail + RandomUtils.getNumberRandom(tailBit);
    }

    public static String randomCode() {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

}
