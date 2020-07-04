package com.xiao.spring.learn.ioc.annotation;

/**
 * @Author sunjinwei
 * @Date 2020-07-04 17:52
 * @Description 使用自定义注解实现 @Autowired
 **/
public class UserController {

    @Deprecated
    @Autowired
    private UserService userService;



    public void fun(){
        System.out.println(userService);
    }

}
