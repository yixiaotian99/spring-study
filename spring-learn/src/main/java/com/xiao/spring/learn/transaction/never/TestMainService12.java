package com.xiao.spring.learn.transaction.never;

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
public class TestMainService12 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private TestService3 testService;

    /**
     * 使用 NEVER 事务
     * 执行不能有事务，否则抛出异常
     * <p>
     * <p>
     * 结论：节点1回滚，节点2、节点3未执行
     * 调用链条 testMainNoException-->外部调用 testHasException
     * 前者有 REQUIRED 事务，后者有 NEVER 事务，执行时如果有事务直接抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testMainNoException() {
        serviceA.testA("异常前A插入");   //插入回滚  节点1

        testService.testHasException();   //service方法外部的调用，REQUIRED_NEW才会起作用，因为 Spring AOP切面特性
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService12 testMainService = (TestMainService12) context.getBean("testMainService12");
        testMainService.testMainNoException();
    }
}

@Service
class TestService3 {


    @Autowired
    private ServiceB serviceB;

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B1插入");   //未执行  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B2插入");   //未执行  节点3
    }
}