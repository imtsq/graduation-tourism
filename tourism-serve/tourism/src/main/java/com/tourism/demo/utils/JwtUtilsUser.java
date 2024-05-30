package com.tourism.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtilsUser {
    public static String signKey = "000ensixichasingKey1315471454";//密钥，只有服务器自己知道


    //生成jwt令牌
    public static String generateJwt(Map<String, Object> claims) {

        String jwt = Jwts.builder()
                .addClaims(claims)//自定义内容
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 1))//有效期
                .compact();//字符串返回值
        return jwt;
    }

    //解析jwt令牌
    public static Claims parseJWT(String jwt) {

        try {
            // 解析JWT
            Claims claims = Jwts.parser()
                    .setSigningKey(signKey)//服务器密钥
                    .parseClaimsJws(jwt) // 将parseClaimsJwt更改为parseClaimsJws，因为它处理的是经过签名的完整JWT
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            // JWT已过期，执行相应的处理逻辑
            // 返回错误信息或者重新生成JWT
            return null;
        }

    }

}
