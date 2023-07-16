package com.example.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/7/15 20:18
 */

@Slf4j
public class JwtVerifyFilter extends BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("JwtVerifyFilter init successfully! ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("cookie name: " + cookie.getName());
            log.info("cookie Value: " + cookie.getValue());
            log.info("cookie domain: " + cookie.getDomain());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("JwtVerifyFilter destroyed ! ");
    }

    private String createJwt(String username) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);

        String sign = JWT.create().withSubject("web json token")
                .withClaim("username", username)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("chengfen"));

        System.out.println("jwt will expire at " + format.format(calendar.getTime()));
        return sign;
    }

    private boolean verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("chengfen")).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt != null;
    }
}
