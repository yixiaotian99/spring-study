package com.xiao.spring.learn.source.circular;

import com.xiao.spring.learn.ioc.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 14:42
 * @Description 测试循环引用问题
 **/
@Component
public class IndexService {


    /**
     * 测试循环引用
     */
    @Autowired
    private UserService userService;


    public IndexService() {
        System.out.println("IndexService实例");
    }


    /**
     * 测试方法
     */
    public void fun1(){
        System.out.println("fun1");
    }
}
