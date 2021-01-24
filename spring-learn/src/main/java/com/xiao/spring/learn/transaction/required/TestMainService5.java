package com.xiao.spring.learn.transaction.required;

import com.xiao.spring.learn.transaction.ServiceA;
import com.xiao.spring.learn.transaction.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author sunjinwei
 * @Date 1/24/21 2:53 PM
 * @Description TODO
 * @see https://zhuanlan.zhihu.com/p/148504094
 **/
@Service
public class TestMainService5 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;

    /**
     * 多次调用 serviceB 方法
     * <p>
     * 为什么节点2也会成功？声明了事务 REQUIRED 之后，节点2与节点3不应该是一个事务吗？为什么节点2成功了？
     * <p>
     * 原理:Spring中事务的默认实现使用的是AOP，也就是代理的方式，如果大家在使用代码测试时，
     * 同一个Service类中的方法相互调用需要使用注入的对象来调用，不要直接使用this.方法名来调用，
     * this.方法名调用是对象内部方法调用，不会通过Spring代理，也就是事务不会起作用
     * <p>
     * 直接调用 testHasException 经过 Spring AOP 切面，事务为一个
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B插入");   //插入回滚  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //未插入  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService5 testMainService = (TestMainService5) context.getBean("testMainService5");
        testMainService.testHasException();
    }
}
