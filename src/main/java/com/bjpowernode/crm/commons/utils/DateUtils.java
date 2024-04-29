package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 年月日时分秒格式化
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String newDate = sdf.format(date);
        return newDate;
    }

    /**
     * 年月日格式化
     * @param date
     * @return
     */
    public static String formateDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(date);
        return newDate;
    }

    /**
     * 时分秒格式化
     * @param date
     * @return
     */
    public static String formateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        String newDate = sdf.format(date);
        return newDate;
    }
}
