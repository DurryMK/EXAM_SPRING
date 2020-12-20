package com.des.client.utils.commonUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 9:17
 * 根据出生日期计算年龄
 */
public class AgeUtil {
    public static int get(String birth) {
        int age = 0;
        try {
            String[] split = birth.split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int day = Integer.parseInt(split[2]);
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);  //当前年份
            int monthNow = cal.get(Calendar.MONTH) + 1;  //当前月份
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
            if (yearNow < year) {
                return 0;
            }
            age = yearNow - year;
            if (monthNow > month) {
                age += 1;
            } else if (dayOfMonthNow > day) {
                age += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }
}
