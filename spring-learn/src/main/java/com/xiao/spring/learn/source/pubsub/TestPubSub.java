package com.xiao.spring.learn.source.pubsub;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 23:09
 * @Description TODO
 **/
public class TestPubSub {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);


        MyApplicationEventPublisher publisher = (MyApplicationEventPublisher) applicationContext.getBean("myApplicationEventPublisher");
        publisher.setApplicationContext(applicationContext);

        publisher.publish("今天有大事要发生");
    }

}
