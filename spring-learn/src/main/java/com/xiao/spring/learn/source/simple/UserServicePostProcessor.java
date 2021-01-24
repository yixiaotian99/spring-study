package com.xiao.spring.learn.source.simple;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-09 08:57
 * @Description 在 Bean 实例化之前 BeanFactory 之前增强 Bean
 **/
@Component
public class UserServicePostProcessor implements BeanPostProcessor {


    /**
     * 重写初始前增强器
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

//        System.out.println(beanName);

        //找到待增强Bean
        if ("userService2".equals(beanName)) {
            UserService2 userService = (UserService2) bean;
            userService.setName("增强Bean");

            return userService;
        }


        return bean;
    }
}
