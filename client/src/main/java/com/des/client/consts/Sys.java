package com.des.client.consts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/20 11:36
 */
public class Sys {
    /**
     *时间格式
     * */
    public static final String DATE = "yyyy-MM-dd-HH-mm-ss";

    /**
     *时间格式化类
     * */
    public static String DF(){
        SimpleDateFormat df = new SimpleDateFormat(DATE);
        return df.format(new Date());
    }
}
