package cn.enilu.flash.utils;


import com.google.common.base.Strings;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtil {
	public static final Set<String> PREFIX_IDS = new HashSet<String>();

    final static Pattern PHONE_START_WITH_ZERO = Pattern.compile("^01(3[0-9]|4[579]|5[0-35-9]|7[0135678]|8[0-9])[0-9]{8}$");

	static {
		PREFIX_IDS.add("12590");
		PREFIX_IDS.add("12593");
		PREFIX_IDS.add("17901");
		PREFIX_IDS.add("17911");
		PREFIX_IDS.add("17951");
        PREFIX_IDS.add("10193");
	}

	public enum MobileType {ChinaTelecom, ChinaUnicom, ChinaMobile, UnKnow}

	final static String CHINA_TELECOM_REGEX = "(133|153|180|181|189|177|173)\\d{8}|1700\\d{7}";
	final static String CHINA_UNICOM_REGEX = "(130|131|132|155|156|145|185|186|176)\\d{8}|1709\\d{7}";
	final static String CHINA_MOBILE_REGEX = "(13[5-9]|15[0-2|7-9]|18[2-4|7-8]|147|178)\\d{8}|(134[0-8]|1705)\\d{7}";

	public static final Pattern P_RULE_1 = Pattern.compile("^((10)|(2[1-9])).*");
	public static final Pattern P_RULE_2 = Pattern.compile("^[3-9].*");

	public static MobileType type(String mobile) {
		if (mobile.matches(CHINA_TELECOM_REGEX)) {
			return MobileType.ChinaTelecom;
		} else if (mobile.matches(CHINA_UNICOM_REGEX)) {
			return MobileType.ChinaUnicom;
		} else if (mobile.matches(CHINA_MOBILE_REGEX)) {
			return MobileType.ChinaMobile;
		}

		return MobileType.UnKnow;
	}

	public static String cleanPhone(String value) {
		value = strClean(value);

		if (value.startsWith("+86")) {
			value = value.substring(3);
		}

		if (value.length() == 13) {
			// 8613800210500
			if (value.startsWith("86")) {
				value = value.substring(2);
			}
		} else if (value.length() >= 14 && value.length() <= 16) {
			// 008613637037976
			if (value.startsWith("0086")) {
				value = value.substring(4);
			}
		}

        if (PHONE_START_WITH_ZERO.matcher(value).matches()) {
            value = value.substring(1);

        }

		// 如果号码是10位，且前2位是10,21,22,23,24,25,26,27,28,29，应该是固话，在第一位补上0
		// 如果号码是10位，且第1位是3,4,5,6,7,8,9（非400、800开头），应该也是固话，在第一位补上0
		// 如果号码是11位，且第1位是3,4,5,6,7,8,9，应该也是固话，在第一位补上0
		if (value.length() == 10) {
			Matcher m = P_RULE_1.matcher(value);
			if (m.find()) {
				value = "0" + value;
			} else {
				m = P_RULE_2.matcher(value);
				if (m.find()) {
					if (!value.startsWith("400") && !value.startsWith("800")) {
						value = "0" + value;
					}
				}
			}
		} else if (value.length() == 11) {
			Matcher m = P_RULE_2.matcher(value);
			if (m.find()) {
				value = "0" + value;
			}
		} else if (value.length() == 12) {
			if (value.startsWith("01")) {
				value = value.substring(1);
			}
		} else if (value.length() == 16) {
			for (String id : PREFIX_IDS) {
				if (value.startsWith(id)) {
					value = value.substring(5);
					break;
				}
			}
		}

		return value;
	}

	public static String strClean(String value) {
		if (Strings.isNullOrEmpty(value)) {
			return "";
		}

		// 去除首尾空白字符、全角空格等
		return value.trim().replaceAll("[\\s\\u00A0\\u3000]+$", "").replaceAll("^[\\s\\u00A0\\u3000]+", "");
	}

}
