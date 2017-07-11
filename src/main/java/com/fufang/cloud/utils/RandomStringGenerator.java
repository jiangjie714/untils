package com.fufang.cloud.utils;

import java.util.Random;

public class RandomStringGenerator {
    public static String randomString(int length) {
        final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomString(10));
    }
}