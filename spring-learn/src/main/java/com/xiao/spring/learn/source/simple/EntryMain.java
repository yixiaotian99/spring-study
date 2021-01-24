package com.xiao.spring.learn.source.simple;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author sunjinwei
 * @Date 2020-07-05 14:59
 * @Description 测试主流程
 **/
public class EntryMain {

    public static void main(String[] args) {

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

//        //普通Bean
//        Object userService = applicationContext.getBean("userService");
//        System.out.println(userService);


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);


        //增强 Bean
        UserService2 userService = (UserService2) applicationContext.getBean("userService");
        System.out.println(userService.getName());

    }
}
