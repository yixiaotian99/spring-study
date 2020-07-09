package com.xiao.spring.learn.source.pubsub;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 23:07
 * @Description 事件发布者
 **/
@Component
public class MyApplicationEventPublisher {

    /**
     * 通过 ApplicationContext 实现事件发布
     */
//    @Autowired
    @Setter
    @Getter
    private ApplicationContext applicationContext;


    public void publish(String msg) {
        applicationContext.publishEvent(new MyApplicationEvent(this, msg));
    }
}
