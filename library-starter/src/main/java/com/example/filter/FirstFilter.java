package com.example.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/22 21:42
 */
//@Component
@Slf4j
public class FirstFilter extends BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter {} init success", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[ ");
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            stringBuilder.append(key).append(" : ");
            String value = Arrays.stream(servletRequest.getParameterMap().get(key)).collect(Collectors.toList()).get(0).toString();
            stringBuilder.append(value);
        }
        stringBuilder.append(" ]");

        log.info("recieve request: {}", stringBuilder.toString());

        servletRequest.setAttribute("test", "begin");
        log.info("filter doFilter successfully");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("filter destroyed");
    }
}
