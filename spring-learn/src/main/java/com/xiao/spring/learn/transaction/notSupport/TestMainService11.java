package com.xiao.spring.learn.transaction.notSupport;

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
public class TestMainService11 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private TestService2 testService;

    /**
     * 使用 NOT_SUPPORT 事务
     * 无论当前有没有事务，都以非事务方式运行，即每条sql一个事务，如果当前存在事务，则挂起事务
     * <p>
     * <p>
     * 结论：节点1回滚，节点2插入成功、节点3未执行
     * 调用链条 testMainNoException-->外部调用 testHasException
     * 前者有 REQUIRED 事务，后者有 NOT_SUPPORT 事务，执行时会将前者事务挂起，后者以非事务方式运行，即每条sql一个事务
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testMainNoException() {
        serviceA.testA("异常前A插入");   //插入回滚  节点1

        testService.testHasException();   //service方法外部的调用，REQUIRED_NEW才会起作用，因为 Spring AOP切面特性
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService11 testMainService = (TestMainService11) context.getBean("testMainService11");
        testMainService.testMainNoException();
    }
}

@Service
class TestService2 {


    @Autowired
    private ServiceB serviceB;

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B1插入");   //插入成功  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B2插入");   //未执行  节点3
    }
}