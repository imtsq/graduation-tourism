package com.tourism.demo.utils;

public class IdentityCardUtil {
    public static String IdentityCard(String code) {
        String lastTwoDigits = code.substring(code.length() - 4);
        // 打印最后两位
        return "**************" + lastTwoDigits;
    }
}
