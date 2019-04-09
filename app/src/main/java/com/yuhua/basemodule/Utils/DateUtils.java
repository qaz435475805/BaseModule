package com.yuhua.basemodule.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Create By: 俞华
 * Date: 2019/04/09
 */

public class DateUtils {

    /**
     * 将时间戳转换为时间
     *
     * @param s          需要转换的时间
     * @param formatType 转换的格式yyyy-MM-dd HH:mm:ss
     */
    public static String longToString(String s, String formatType) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间转换为时间戳(long)
     *
     * @param strTime    需要转换的时间
     * @param formatType 需要转换的时间的格式yyyy-MM-dd HH:mm:ss
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * 将时间转换为时间戳(Date)
     *
     * @param strTime    需要转换的时间
     * @param formatType 需要转换的时间的格式yyyy-MM-dd HH:mm:ss
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * date转换的long类型的时间
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

}
