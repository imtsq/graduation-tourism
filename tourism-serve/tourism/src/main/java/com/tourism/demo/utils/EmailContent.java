package com.tourism.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class EmailContent {
    public static String Ip = "http://localhost:8081/#/BackPassword/";

    public static String getEmailUrl (String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        String jwt = JwtUtilsUser.generateJwt(map);
        String EmailContent ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            width: 700px;\n" +
                "            background-color: #3333;\n" +
                "            box-shadow: 2px 5px rgba(0,0,0,0.2);\n" +
                "            margin: 0 auto;\n" +
                "            \n" +
                "            animation-name: run;\n" +
                "            animation-duration: 2s;\n" +
                "        }\n" +
                "        @keyframes run {\n" +
                "        from {transform: translateY(-100%);}\n" +
                "        to {transform: translateY(0);}\n" +
                "        }\n" +
                "        tr {\n" +
                "            width: 100%;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        td {\n" +
                "            width: 100%;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <tbody >\n" +
                "          <tr>\n" +
                "            <td><img src=\"https://s.cn.bing.net/th?id=OHR.AlmondBloom_ZH-CN9441550492_1920x1080.webp\" style=\"width: 600px; height: 400px;\"></td>\n" +
                "           \n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td><p>我们收到了找回您的密码的请求。请使用下方的验证码以继续。</p></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td><a href=\""+ Ip + jwt + "\" style=\"text-decoration: none; color: #333;\"><div style=\"background-color: #0675c4; border-radius: 5px; height: 40px; width: 200px; margin: 0 auto; line-height: 40px; cursor: pointer;\">点击找回密码</div></a></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td><p>如果您并未申请找回密码，请忽略这封邮件。</p></td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "</body>\n" +
                "</html>";
        return EmailContent;
    }

}
