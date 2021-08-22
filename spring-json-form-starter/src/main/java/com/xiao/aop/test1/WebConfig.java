//package com.xiao.aop;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.nio.charset.Charset;
//import java.util.List;
//
///**
// * @Author sunjinwei
// * @Date 8/20/21 11:48 PM
// * @Description TODO
// **/
//@Configuration
//@SuppressWarnings("deprecation")
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new RequestJsonHandlerMethodArgumentResolver());
//    }
//
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
//    }
//
//
//}
