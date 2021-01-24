package com.xiao.spring.learn.transaction.nested;

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
public class TestMainService15 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private TestService6 testService;

    /**
     * 使用 NESTED 事务
     * 嵌套事务，当前存在事务则称为父事务，被调用方法称为子事务，父事务回滚子事务也回滚，子事务回滚父事务不受影响
     * <p>
     * <p>
     * 结论：节点1、节点2、节点3 插入回滚
     * 调用链条 testMainNoException-->外部调用 testHasException
     * 前者有 REQUIRED 事务，后者有 NESTED 事务，执行时会将前者事务挂起，后者创建一个子事务，
     * 如果父事务有异常回滚，子事务也会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testMainHasException() {
        serviceA.testA("有异常A插入");   //插入回滚  节点1

        testService.testNoException();   //service方法外部的调用才会起作用，因为 Spring AOP切面特性

        int i = 1 / 0;   //父事务回滚，会导致子事务也回滚
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService15 testMainService = (TestMainService15) context.getBean("testMainService15");
        testMainService.testMainHasException();
    }
}

@Service
class TestService6 {


    @Autowired
    private ServiceB serviceB;

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void testNoException() {
        serviceB.testB("无异常B1插入");   //插入回滚  节点2

        serviceB.testB("无异常B2插入");   //插入回滚  节点3
    }
}