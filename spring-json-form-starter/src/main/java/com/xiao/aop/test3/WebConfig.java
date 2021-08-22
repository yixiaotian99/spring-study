package com.xiao.aop.test3;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author sunjinwei
 * @Date 8/21/21 11:48 PM
 * @Description 启用拦截器
 **/
//@Configuration
@ConditionalOnClass(HttpRequestFilter.class)
public class WebConfig {

    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HttpRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("myFilterRegistration");
        return registration;
    }
}
