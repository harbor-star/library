package com.example.config;

import com.example.filter.FirstFilter;
import com.example.filter.JwtVerifyFilter;
import com.example.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/22 22:13
 */
@Configuration
public class FilterRegistrator {

    @Bean
    public FilterRegistrationBean<FirstFilter> registerFirstFilter() {
        FilterRegistrationBean<FirstFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FirstFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("firstFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> registerSecondFilter() {
        FilterRegistrationBean<SecondFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecondFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("secondFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean<JwtVerifyFilter> registerJwtFilter() {
        FilterRegistrationBean<JwtVerifyFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtVerifyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("jwtVerifyFilter");
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
