package cn.enilu.flash.utils;


import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5 {

    public static final Logger LOG = LoggerFactory.getLogger(MD5.class);

    /**
     * 16进制字符集
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 1024;
    /**
     * 加盐参数
     */
    public final static String HASH_ALGORITHM_NAME = "MD5";
    /**
     * 指定算法为MD5的MessageDigest
     */
    private static MessageDigest MESSAGE_DIGEST = null;

    /** 初始化messageDigest的加密算法为MD5 */
    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * * MD5加密字符串
     *
     * @param str 目标字符串
     * @return MD5加密后的字符串
     */

    public static String getMD5String(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        return getMD5String(str.getBytes());
    }

    /**
     * * MD5加密以byte数组表示的字符串
     *
     * @param bytes 目标byte数组
     * @return MD5加密后的字符串
     */

    public static String getMD5String(byte[] bytes) {
        MESSAGE_DIGEST.update(bytes);
        return bytesToHex(MESSAGE_DIGEST.digest());
    }

    /**
     * 获取文件的MD5值
     *
     * @param file 目标文件
     * @return MD5字符串
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MESSAGE_DIGEST.update(byteBuffer);
            ret = bytesToHex(MESSAGE_DIGEST.digest());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(ch);
        }
        return ret;
    }

    /**
     * * 获取文件的MD5值
     *
     * @param fileName 目标文件的完整名称
     * @return MD5字符串
     */
    public static String getFileMD5String(String fileName) {
        return getFileMD5String(new File(fileName));
    }


    /**
     * * 将字节数组转换成16进制字符串
     *
     * @param bytes 目标字节数组
     * @return 转换结果
     */
    public static String bytesToHex(byte[] bytes) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    /**
     * * 将字节数组中指定区间的子数组转换成16进制字符串
     *
     * @param bytes 目标字节数组
     * @param start 起始位置（包括该位置）
     * @param end   结束位置（不包括该位置）
     * @return 转换结果
     */
    public static String bytesToHex(byte[] bytes, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * * 将单个字节码转换成16进制字符串
     *
     * @param bt 目标字节
     * @return 转换结果
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }


    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param salt        密码盐
     * @return
     */
    public static String md5(String credentials, String salt) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            //先加盐
            messageDigest.update(salt.getBytes("UTF-8"));
            //再放需要被加密的数据
            messageDigest.update(credentials.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5.md5("admin", "8pgby"));
    }
}