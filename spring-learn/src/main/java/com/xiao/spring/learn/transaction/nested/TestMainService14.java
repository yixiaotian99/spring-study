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
public class TestMainService14 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private TestService5 testService;

    /**
     * 使用 NESTED 事务
     * 嵌套事务，当前存在事务则称为父事务，被调用方法称为子事务，父事务回滚子事务也回滚，子事务回滚父事务不受影响
     * <p>
     * <p>
     * 结论：节点1、节点2插入回滚、节点3未执行
     * 调用链条 testMainNoException-->外部调用 testHasException
     * 前者有 REQUIRED 事务，后者有 NESTED 事务，执行时会将前者事务挂起，后者创建一个子事务，
     * 子事务中有异常，如果父事务已经捕获异常，则子事务都回滚，父事务不受影响
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testMainNoException() {
        serviceA.testA("无异常A插入");   //插入成功  节点1

        try {
            //捕获子事务异常，不会导致父事务回滚
            testService.testHasException();   //service方法外部的调用才会起作用，因为 Spring AOP切面特性
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceA.testA("子事务异常之后A插入");   //插入成功  节点4
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService14 testMainService = (TestMainService14) context.getBean("testMainService14");
        testMainService.testMainNoException();
    }
}

@Service
class TestService5 {


    @Autowired
    private ServiceB serviceB;

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B1插入");   //插入回滚  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B2插入");   //未执行  节点3
    }
}