package cn.enilu.flash.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

/**
 * 加密工具类
 * @author  enilu
 */
public class CryptUtils {

	private static final String DES = "DES";

	/**
	 * 加密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key) {
		// DES算法要求有一个可信任的随机数源
		try {
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] src, byte[] key) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 数据解密
	 *
	 * @param data
	 * @param key  密钥
	 * @return String
	 * @throws Exception
	 */
	private final static String decrypt(String data, String key) {
		if (data != null) {
			return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
		}
		return null;
	}

	/**
	 * 数据加密
	 *
	 * @param data
	 * @param key  密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (data != null) {
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 *
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append('0');
			}
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException();
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 对字符串按密钥进行DES加密
	 */
	public static String encode(String data) {
		return encrypt(data, Constants.CRYPT_DES_KEY);
	}

	/**
	 * 对字符串按密钥进行DES解密
	 */
	public static String decode(String data) {
		return decrypt(data, Constants.CRYPT_DES_KEY);
	}

	/**
	 * 对字符串按密钥进行DES加密
	 */
	public static String encode(String data, String key) {
		if (data != null && key != null) {
			try {
				SecretKey secretKey = generateSecretKey(key);
				Cipher cipher = Cipher.getInstance(DES);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return encryptBASE64(cipher.doFinal(data.getBytes()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 对字符串按密钥进行DES解密
	 */
	public static String decode(String data, String key) {
		if (data != null && key != null) {
			try {
				SecretKey secretKey = generateSecretKey(key);
				Cipher cipher = Cipher.getInstance(DES); // Get the cipher
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(decryptBASE64(data)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 根据密码和keyGenerator生成密钥。
	 * @param key
	 * @return
	 */
	private static SecretKey generateSecretKey(String key) {
		SecretKey secretKey = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = key.getBytes();
			md.update(bytes, 0, bytes.length);
			// Generate 16 bytes
			byte[] mdBytes = md.digest();
			// Fetch 8 bytes for DESKeySpec
			byte[] truncatedBytes = Arrays.copyOf(mdBytes, 8);
			DESKeySpec keySpec = new DESKeySpec(truncatedBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	/**
	 * 转换成字符串
	 */
	public static String convertToHexString(byte[] data) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			strBuffer.append(hex.toUpperCase());
		}
		return strBuffer.toString();
	}

	/**
	 * BASE64加密
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (Base64.getEncoder()).encodeToString(key);
	}

	/**
	 * BASE64解密
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.getDecoder().decode(key);
	}
	public static String encodeBASE64(byte[] bytes) {

		String encode = Base64.getEncoder().encodeToString(bytes);
		encode = encode.replaceAll("\n", "");
		return encode;
	}

	/**
	 * 文件内容生成BASE64编码的字符串
	 */
	public static String encodeBASE64(File file) {
		Base64.Encoder encoder =  Base64.getEncoder();
		StringBuilder sb = new StringBuilder();
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			for (int len = input.read(temp); len != -1; len = input.read(temp)) {
				out.write(temp, 0, len);
				sb.append(encoder.encode(out.toByteArray()));
				out.reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * BASE64编码的字符串解码为文件
	 */
	public static void decodeBASE64(String encoder, File file) {
	Base64.Decoder decoder = Base64.getDecoder();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] decoderBytes = decoder.decode(encoder);
			fos.write(decoderBytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] decodeBASE64(String encoder) {
		Base64.Decoder decoder = Base64.getDecoder();

		return decoder.decode(encoder);

	}
	public static String getSign(String privateKey) {
		return getSign(privateKey, new Date());
	}
	public static String getMD5ofStr(String inStr) {
		MessageDigest md5 = null;
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			// 获得密文
			byte[] md5Bytes = md5.digest(byteArray);
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	public static String getSign(String privateKey, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sign = getMD5ofStr(privateKey + sdf.format(date));

		return sign;
	}

}
