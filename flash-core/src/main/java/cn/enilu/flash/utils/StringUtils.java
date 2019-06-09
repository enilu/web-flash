package cn.enilu.flash.utils;


import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author enilu
 *
 */
public class StringUtils {

    public static final String EMPTY = "";
    private static final AtomicLong ORDER_SEQ = new AtomicLong(1);
    private static final Pattern SELECT_PATTERN_COMPILE = Pattern.compile("(?si)^.*?select(.*?)from");

    private static final Pattern PATTERN_URL_VERSION = Pattern.compile("/.*?/.*?/.*?/(v[0-9]+)");
    private static  final Pattern PATERN_IP = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    /**
     * 是否为空字符
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        if ("null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    /**
     * 是否包含空字符串
     *
     * @param strs 字符串列表
     * @return 是否包含空字符串
     */
    public static boolean hasBlank(String... strs) {
        if (CollectionKit.isEmpty(strs)) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 是否为非空字符
     */
    public static boolean isNotEmpty(String str) {
        return (!isEmpty(str));
    }

    /**
     * 判断是否为null或空字符
     */
    public static boolean isNullOrEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (String.valueOf(o).replace((char) 12288, ' ').trim().length() == 0) {
            return true;
        }
        if ("null".equals(o)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为null或非空字符
     */
    public static boolean isNotNullOrEmpty(Object o) {
        return !isNullOrEmpty(o);
    }

    /**
     * 判断字符串的内容是否是数字
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; ++i) {
            if (!(Character.isDigit(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 判断中文汉字和符号
    public static boolean haveChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 判断中文汉字和符号
    public static boolean allChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    public static String trimAll(String o) {
        if (isNotNullOrEmpty(o)) {
            o = o.replaceAll(" ", "");
            o = o.replaceAll(",", "");
        }
        return trim(o);
    }

    public static String trim(Object o) {
        return trim(o, "");
    }

    public static String trim(Object o, String defaultString) {
        if (o == null) {
            return defaultString;
        }
        return o.toString().trim();
    }

    /**
     * 简单判断字符串是否符合json格式
     */
    public static boolean isJson(String str) {
        if (!Strings.isNullOrEmpty(str)) {
            if (str.trim().startsWith("{") || str.trim().startsWith("[{")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 中文转码,因为将CharacterEncodingFilter纳入了Nutz来管理,所以造成包装请求参数失效
     */
    public static String toGBK(String str) {
        String result = "";
        try {
            result = new String(str.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 全角转半角
     */
    public static String toDBC(String value) {
        if (value == null) {
            return "";
        }
        // 全角转半角
        char[] c = value.toCharArray();
        for (int index = 0; index < c.length; index++) {
            if (c[index] == '　') {
                c[index] = ' ';
            } else if ((c[index] > 65280) && (c[index] < 65375)) {
                c[index] = (char) (c[index] - 65248);
            }
        }
        return String.valueOf(c).replaceAll("[\\s\\t\\u00A0]+$", "").replaceAll("^[\\s\\t\\u00A0]+", "");
    }

    /**
     * 根据文件扩展名判断文件类型,是否为图片文件
     */
    public static boolean isPic(String fileName) {
        // return fileName.matches("[\\s\\S]+.[gif|jpg|png|jpeg|bmp]");
        return fileName.matches("^[\\s\\S]+\\.+(gif|jpg|jpeg|png|bmp)$");
    }

    public static boolean isIp(String ip) {
        return PATERN_IP.matcher(ip).find();
    }

    public static String getMethod(Field field) {
        return (field.getType() == boolean.class ? "is" : "get") + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    public static String getMethod(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    /**
     * 根据文件扩展名判断文件类型,是否为图片文件
     */
    public static boolean validateImgFile(String suffixName) {
        String[] imgFileType = {"jpg", "jpeg", "gif", "bmp", "png"};
        return inArray(imgFileType, suffixName);
    }

    /**
     * 判断元素是否存在于数组中
     */
    public static boolean inArray(String[] arry, String elem) {
        if (arry != null && arry.length > 0 && elem != null && elem.trim().length() > 0) {
            for (String arr : arry) {
                if (elem.trim().equalsIgnoreCase(arr)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取MD5摘要信息
     */
    public static String getMD5(String msg) {
        return MD5.getMD5String(msg);
    }

    /**
     * 获取新的订单号
     */
    public static String getNewOrderNo() {
        String orderNo = DateUtil.format(new Date(),"yyyyMMddHHmmssSSS");
        orderNo += String.format("%04d", ORDER_SEQ.incrementAndGet() % 9999 + 1);
        return orderNo;
    }

    /**
     * 获取新支付交易号
     */
    public static String getReqSnNo(String mertid) {
        String rst = "";
        DecimalFormat formatter = new DecimalFormat("00000");
        String index = formatter.format(ORDER_SEQ.incrementAndGet() % 9999 + 1);
        rst = mertid + DateUtil.getAllTime()+ index;
        return rst;
    }

    /**
     * 获取新的用户唯一标识码
     */
    public static String getNewUserNo() {
        return MD5.getMD5String(RandomUtils.getUUIDRandom());
    }

    public static boolean validIP(String rule, String ip) {
        if ("1.1.1.1".equals(ip)) {
            return false;
        }
        if ("0.0.0.0".equals(ip)) {
            return true;
        }
        if (rule != null && !rule.contains(ip)) {
            return false;
        }

        return true;
    }

    public static String cleanString(String str) {
        return str.replaceAll("\\t|\\s", "");
    }

    /**
     * 组织机构代码清理
     */
    public static String orgCodeFormat(String orgCode) {
        if (Strings.isNullOrEmpty(orgCode)) {
            return "";
        }
        orgCode = orgCode.replaceAll("[^\\da-zA-Z]", "");
        return orgCode.length() == 9 ? orgCode : "";
    }



    public static String getResUrl(String url) {
        return url.replaceAll("/v[0-9]+", "");
    }

    public static String getUrlVersion(String url) {
        Matcher m = PATTERN_URL_VERSION.matcher(url);
        if (m.find()) {
            return m.group(1);
        }

        return null;
    }

    public static String cutLeft(String str, int width) {
        if (str == null) {
            return "";
        }

        int len = str.length();
        if (len <= width) {
            return str;
        }

        return str.substring(0, width);
    }

    public static String countSql(String sql) {
        if (Strings.isNullOrEmpty(sql)) {
            return "";
        }
        return SELECT_PATTERN_COMPILE.matcher(sql.toString()).replaceAll("select count(1) from");
    }

    public static String firstIpInSection(String ip) {
        if (Strings.isNullOrEmpty(ip)) {
            return null;
        }
        return ip.substring(0, ip.lastIndexOf(".")) + ".1";
    }

    /**
     * 过滤特殊字符
     *
     * @param nickname
     * @return
     */
    public static String filterSpecialChar(String nickname) {
        if (nickname == null) {
            return null;
        }
        nickname = nickname.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
        return nickname;
    }

    /**
     * 判断指定字符串是否是中文名，匹配规则：包含汉字或者汉字中间可以有·
     * @param userName
     * @return
     */
    public static boolean isChineseName(String userName) {
        return !Strings.isNullOrEmpty(userName) && userName.matches("[\\u4e00-\\u9fa5]+·?[\\u4e00-\\u9fa5]+");
    }
    /**
     * <p>
     * 包含大写字母
     * </p>
     *
     * @param word 待判断字符串
     * @return
     */
    public static boolean containsUpperCase(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * 是否为Boolean类型(包含普通类型)
     * </p>
     *
     * @param propertyCls
     * @return
     */
    public static Boolean isBoolean(Class<?> propertyCls) {
        return propertyCls != null && (boolean.class.isAssignableFrom(propertyCls) || Boolean.class.isAssignableFrom(propertyCls));
    }

    /**
     * <p>
     * 第一个首字母小写,之后字符大小写的不变<br>
     * StringUtils.firstCharToLower( "UserService" )     = userService
     * StringUtils.firstCharToLower( "UserServiceImpl" ) = userServiceImpl
     * </p>
     *
     * @param rawString 需要处理的字符串
     * @return
     */
    public static String firstCharToLower(String rawString) {
        return prefixToLower(rawString, 1);
    }

    /**
     * <p>
     * 前n个首字母小写,之后字符大小写的不变
     * </p>
     *
     * @param rawString 需要处理的字符串
     * @param index     多少个字符(从左至右)
     * @return
     */
    public static String prefixToLower(String rawString, int index) {
        String beforeChar = rawString.substring(0, index).toLowerCase();
        String afterChar = rawString.substring(index, rawString.length());
        return beforeChar + afterChar;
    }

    public static String sNull(Object obj) {
            return obj==null?"":obj.toString();
    }



    /**
     * 格式化文本, {} 表示占位符<br>
     * 例如：format("aaa {} ccc", "bbb")   ---->    aaa bbb ccc
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values 参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... values) {
        if (CollectionKit.isEmpty(values) || isEmpty(template)) {
            return template;
        }

        final StringBuilder sb = new StringBuilder();
        final int length = template.length();

        int valueIndex = 0;
        char currentChar;
        for (int i = 0; i < length; i++) {
            if (valueIndex >= values.length) {
                sb.append(sub(template, i, length));
                break;
            }

            currentChar = template.charAt(i);
            if (currentChar == '{') {
                final char nextChar = template.charAt(++i);
                if (nextChar == '}') {
                    sb.append(values[valueIndex++]);
                } else {
                    sb.append('{').append(nextChar);
                }
            } else {
                sb.append(currentChar);
            }

        }

        return sb.toString();
    }

    /**
     * 格式化文本，使用 {varName} 占位<br>
     * map = {a: "aValue", b: "bValue"}
     * format("{a} and {b}", map)    ---->    aValue and bValue
     *
     * @param template 文本模板，被替换的部分用 {key} 表示
     * @param map 参数值对
     * @return 格式化后的文本
     */
    public static String format(String template, Map<?, ?> map) {
        if (null == map || map.isEmpty()) {
            return template;
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return template;
    }
    /**
     * 改进JDK subString<br>
     * index从0开始计算，最后一个字符为-1<br>
     * 如果from和to位置一样，返回 "" <br>
     * 如果from或to为负数，则按照length从后向前数位置，如果绝对值大于字符串长度，则from归到0，to归到length<br>
     * 如果经过修正的index中from大于to，则互换from和to
     * example: <br>
     * 	abcdefgh 2 3 -> c <br>
     * 	abcdefgh 2 -3 -> cde <br>
     *
     * @param string String
     * @param fromIndex 开始的index（包括）
     * @param toIndex 结束的index（不包括）
     * @return 字串
     */
    public static String sub(String string, int fromIndex, int toIndex) {
        int len = string.length();
        if (fromIndex < 0) {
            fromIndex = len + fromIndex;
            if(fromIndex < 0 ) {
                fromIndex = 0;
            }
        } else if(fromIndex >= len) {
            fromIndex = len -1;
        }
        if (toIndex < 0) {
            toIndex = len + toIndex;
            if(toIndex < 0) {
                toIndex = len;
            }
        } else if(toIndex > len) {
            toIndex = len;
        }
        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }
        if (fromIndex == toIndex) {
            return EMPTY;
        }
        char[] strArray = string.toCharArray();
        char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
        return new String(newStrArray);
    }


    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }


    /**
     * 去掉指定前缀
     *
     * @param str 字符串
     * @param prefix 前缀
     * @return 切掉后的字符串，若前缀不是 preffix， 返回原字符串
     */
    public static String removePrefix(String str, String prefix) {
        if(isEmpty(str) || isEmpty(prefix)){
            return str;
        }

        if (str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }


    /**
     * 去掉指定后缀
     *
     * @param str 字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSuffix(String str, String suffix) {
        if(isEmpty(str) || isEmpty(suffix)){
            return str;
        }

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    /**
     * 获得字符串对应byte数组
     * @param str 字符串
     * @param charset 编码，如果为<code>null</code>使用系统默认编码
     * @return bytes
     */
    public static byte[] getBytes(String str, Charset charset){
        if(null == str){
            return null;
        }
        return null == charset ? str.getBytes() : str.getBytes(charset);
    }


    /**
     * 切分字符串<br>
     * from jodd
     *
     * @param str 被切分的字符串
     * @param delimiter 分隔符
     * @return 字符串
     */
    public static String[] split(String str, String delimiter) {
        if (str == null) {
            return null;
        }
        if (str.trim().length() == 0) {
            return new String[] { str };
        }

        int dellen = delimiter.length(); // del length
        int maxparts = (str.length() / dellen) + 2; // one more for the last
        int[] positions = new int[maxparts];

        int i, j = 0;
        int count = 0;
        positions[0] = -dellen;
        while ((i = str.indexOf(delimiter, j)) != -1) {
            count++;
            positions[count] = i;
            j = i + dellen;
        }
        count++;
        positions[count] = str.length();

        String[] result = new String[count];

        for (i = 0; i < count; i++) {
            result[i] = str.substring(positions[i] + dellen, positions[i + 1]);
        }
        return result;
    }



    /**
     * 比较两个字符串（大小写敏感）。
     *
     * <pre>
     * equals(null, null)   = true
     * equals(null, &quot;abc&quot;)  = false
     * equals(&quot;abc&quot;, null)  = false
     * equals(&quot;abc&quot;, &quot;abc&quot;) = true
     * equals(&quot;abc&quot;, &quot;ABC&quot;) = false
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     *
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }


    /**
     * 编码字符串
     *
     * @param str 字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(String str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.getBytes();
        }
        return str.getBytes(charset);
    }


    /**
     * 解码字节码
     *
     * @param data 字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }
}
