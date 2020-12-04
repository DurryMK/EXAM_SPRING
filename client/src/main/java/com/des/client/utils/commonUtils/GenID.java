package com.des.client.utils.commonUtils;

import java.util.Random;

public class GenID {
    public static final String ARRAY = "$ABCDEFGHIJKLMNOPQRSTUUVWXZY";
    public static Random random = new Random();

    public static String AID() {
        String ID = "";
        String head = ARRAY.charAt(random.nextInt(23) + 1) + "";
        String body = System.currentTimeMillis() + "";
        String tail = random.nextInt(1024) + 1 + "";
        ID = head + body.substring(5) + tail;
        return ID;
    }
}
