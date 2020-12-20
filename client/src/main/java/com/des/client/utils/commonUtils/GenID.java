package com.des.client.utils.commonUtils;

import java.util.HashSet;
import java.util.Random;

public class GenID {
    public static final String ARRAY = "ABCDEFGHIJKLMNOPQRSTUUVWXZYabcdefghijklmnopqrstuvwxyz";
    public static Random random = new Random();

    public static String AID() {
        String ID = "";
        String head = ARRAY.charAt(random.nextInt(51) + 1) + "" + ARRAY.charAt(random.nextInt(51) + 1) + "" + ARRAY.charAt(random.nextInt(51) + 1) + "";
        String body = random.nextInt(99999) + "";
        ID = head + body;
        return ID;
    }

    public static void main(String[] args) {
        HashSet<String> s = new HashSet<>();
        for(int i = 0;i < 10000;i++){
            String id = AID();
            if(s.contains(id)){
                System.out.println("重复*************************************************************");
            }else{
                s.add(id);
                System.out.println(id);
            }
        }
    }
}
