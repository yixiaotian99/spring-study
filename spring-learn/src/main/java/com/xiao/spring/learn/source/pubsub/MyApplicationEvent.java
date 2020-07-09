package com.xiao.spring.learn.source.pubsub;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 22:59
 * @Description 我的事件角色，定义事件
 **/
public class MyApplicationEvent extends ApplicationEvent {

    /**
     * 事件消息
     */
    @Getter
    @Setter
    private String message;



    public MyApplicationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }


}
