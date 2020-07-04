package com.xiao.spring.learn.ioc.invoke;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author sunjinwei
 * @Date 2020-07-04 17:51
 * @Description 测试使用反射生成对象
 **/
public class MyInvokeTest {


    /**
     * 测试生成 UserController 中的 UserService 对象
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        //首先得到 class 对象
//        Class<UserController> clazz = UserController.class;
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();

        //ioc容器是帮我们创建对象的，我们 new 出来
        UserService userService = new UserService();

        //得到属性值
        Field userField = clazz.getDeclaredField("userService");

        //对于私有属性设置可访问
        userField.setAccessible(true);

        //设置所有属性都可访问
//        AccessibleObject.setAccessible(clazz.getDeclaredFields(), true);

        //得到 setXX 方法
        String serviceName = userField.getName();
        String setMethod = "set" + serviceName.substring(0, 1).toUpperCase() + serviceName.substring(1);
        System.out.println(setMethod);

        //获取有参数 setXX 方法，注意参数类型 UserService.class
        Method method = clazz.getMethod(setMethod, UserService.class);

        //执行 setXX 方法, 注意用实例调用
        method.invoke(userController, userService);

        System.out.println(userService);
        System.out.println(userController.getUserService());
    }

}
