package com.xiao.spring.learn.ioc.invoke;

/**
 * @Author sunjinwei
 * @Date 2020-07-04 17:52
 * @Description TODO
 **/
public class UserController {

    private UserService userService;

//    public UserService userService2;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
