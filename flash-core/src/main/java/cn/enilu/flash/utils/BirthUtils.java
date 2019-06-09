package cn.enilu.flash.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BirthUtils {
	
	public static final String[] ZODIAC_ARR = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };
	 
	public static final String[] CONSTELLATION_ARR = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
	 
	public static final int[] CONSTELLATION_EDGE_DAY = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
	 
	/**
	 * 根据日期获取生肖
	 * @return
	 */
	public static String getZodica(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return ZODIAC_ARR[cal.get(Calendar.YEAR) % 12];
	}
	 
	/**
	 * 根据日期获取星座
	 * @return
	 */
	public static String getConstellation(Date date) {
	    if (date == null) {
	        return "";
	    }
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    if (day < CONSTELLATION_EDGE_DAY[month]) {
	        month = month - 1;
	    }
	    if (month >= 0) {
	        return CONSTELLATION_ARR[month];
	    }
	    // default to return 魔羯
	    return CONSTELLATION_ARR[11];
	}
	
	/**
	 *  根据身份证号判断用户性别
	 * @param cardNo
	 * @return
	 */
	public static String getSex(String cardNo) {
		String sexStr = "0";
		if (cardNo.length() == 15) {
			sexStr = cardNo.substring(14, 15);
		} else if (cardNo.length() == 18) {
			sexStr = cardNo.substring(16,17);
		}
		int sexNo = Integer.parseInt(sexStr);
		return sexNo % 2 == 0 ? "女" : "男";
	}
	
	/**
	 *  根据身份证号判断用户生肖
	 * @param cardNo
	 * @return
	 */
	public static String getZodica(String cardNo) {
		// 获取出生日期
		String birthday = cardNo.substring(6, 14);
		Date birthdate = null;
		try {
			birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
			return getZodica(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  根据身份证号判断用户星座
	 * @param cardNo
	 * @return
	 */
	public static String getConstellation(String cardNo) {
		// 获取出生日期
		String birthday = cardNo.substring(6, 14);
		Date birthdate = null;
		try {
			birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
			return getConstellation(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static int getAge(String cardNo) {
        String birthday = cardNo.substring(6, 14);
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar currentDay = new GregorianCalendar();
        currentDay.setTime(birthdate);
        int birYear = currentDay.get(Calendar.YEAR);

        // 获取年龄
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String thisYear = simpleDateFormat.format(new Date());
        int age = Integer.parseInt(thisYear) - birYear;

        return age;
    }

    public static int getAge(String cardNo, Date date) {
        String birthday = cardNo.substring(6, 14);
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar currentDay = new GregorianCalendar();
        currentDay.setTime(birthdate);
        int birYear = currentDay.get(Calendar.YEAR);

        // 获取年龄
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String thisYear = simpleDateFormat.format(date);
        int age = Integer.parseInt(thisYear) - birYear;

        return age;
    }
}
