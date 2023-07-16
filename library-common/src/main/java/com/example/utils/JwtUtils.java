package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* @author 陈磊
* @version 2.0
* @date 2023/7/11 21:52
* 
*/
public class JwtUtils {
    public static String createJwt(String username) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);

        String sign = JWT.create().withSubject("web json token")
                .withClaim("username", username)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("chengfen"));

        System.out.println("jwt will expire at "+format.format(calendar.getTime()));
        return sign;
    }

    public static boolean verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("chengfen")).build();
        DecodedJWT jwt = verifier.verify(token);
        if (jwt == null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String jwt = createJwt("zhangsan");
        System.out.println(verify(jwt));
    }
}
