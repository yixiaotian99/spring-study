package com.xiao.spring.learn.source.pubsub;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 23:02
 * @Description TODO
 **/
@Component
public class MyApplicationListener02 implements ApplicationListener<MyApplicationEvent> {


    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        String message = event.getMessage();
        System.out.println("监听者2 监听到消息: " + message);
    }
}
