package com.xiao.aop.test3;

import java.lang.annotation.*;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:33 PM
 * @Description 自定义方法参数注解
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestCustomBody {


}
