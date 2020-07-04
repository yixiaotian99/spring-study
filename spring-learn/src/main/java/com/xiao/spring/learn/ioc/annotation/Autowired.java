package com.xiao.spring.learn.ioc.annotation;

import java.lang.annotation.*;

/**
 * @Author sunjinwei
 * @Date 2020-07-04 20:54
 * @Description 自定义注解，实现 @Autowired 功能
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Autowired {
}
