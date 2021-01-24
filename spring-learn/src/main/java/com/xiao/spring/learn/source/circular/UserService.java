package com.xiao.spring.learn.source.circular;

import com.xiao.spring.learn.ioc.annotation.MyAutowired;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 14:43
 * @Description 测试循环引用
 **/
@Component
public class UserService {


    @MyAutowired
    private IndexService indexService;


    public UserService() {
        System.out.println("UserService实例");
    }
}
