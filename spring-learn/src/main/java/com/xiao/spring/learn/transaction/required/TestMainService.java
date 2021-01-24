package com.xiao.spring.learn.transaction.required;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author sunjinwei
 * @Date 1/24/21 2:53 PM
 * @Description TODO
 * @see https://zhuanlan.zhihu.com/p/148504094
 **/
@Service
public class TestMainService {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;


    /**
     * 模拟在没有事务的情况下，如果方法发生异常，会怎么样
     */
    public void testMainNoException() {
        serviceA.testA("无异常A插入");   //插入成功
        testOther();
    }

    /**
     * 多次调用 serviceB 方法
     */
    public void testOther() {
        serviceB.testB("无异常B插入");   //插入成功
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //插入失败
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService testMainService = (TestMainService) context.getBean("testMainService");
        testMainService.testMainNoException();
    }
}
