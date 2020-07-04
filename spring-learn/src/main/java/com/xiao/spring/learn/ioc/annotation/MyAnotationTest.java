package com.xiao.spring.learn.ioc.annotation;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @Author sunjinwei
 * @Date 2020-07-04 21:00
 * @Description 使用自定义注解
 **/
public class MyAnotationTest {


    public static void main(String[] args) throws NoSuchFieldException {

        //获取 class 对象
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();


        //获取所有属性
        Field[] fields = clazz.getDeclaredFields();

        //判断是否属性上有自定义注解
        Stream.of(fields).forEach(field -> {

            //如果是 Autowired 注解
            Autowired annotation = field.getAnnotation(Autowired.class);

            if (annotation != null) {

                //设置访问级别
                field.setAccessible(true);

                //获取属性类型
                Class<?> type = field.getType();
                try {

                    //创建实例
                    Object val = type.newInstance();

                    //设置属性值
                    field.set(userController, val);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


        userController.fun();

    }
}
