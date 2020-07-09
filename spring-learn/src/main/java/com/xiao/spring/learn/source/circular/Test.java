package com.xiao.spring.learn.source.circular;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 14:45
 * @Description 测试循环引用问题
 **/
public class Test {


    public static void main(String[] args) {

        //使用注解扫描
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);


        //获取实例
        IndexService indexService = (IndexService)applicationContext.getBean("indexService");


        indexService.fun1();

    }
}
