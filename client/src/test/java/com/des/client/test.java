package com.des.client;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/20 11:37
 */
public class test {


    public static void main(String[] args) {
        String s = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat d = new SimpleDateFormat(s);
        System.out.println(d.format(new Date()));
    }
}
