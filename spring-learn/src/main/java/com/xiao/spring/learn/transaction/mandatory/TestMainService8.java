package com.xiao.spring.learn.transaction.mandatory;

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
public class TestMainService8 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;


    /**
     * 模拟在 MANDATORY 事务的情况下，如果方法发生异常，会怎么样
     * <p>
     * <p>
     * 被调用方法 testHasException 使用 MANDATORY
     * 如果当前存在事务，就加入到当前事务中，如果当前不存在事务，则抛出异常
     * <p>
     * 执行结论：节点1 执行成功，节点2、节点3未执行
     * 调用链条  testMainNoException --> 内部调用 testHasException
     * 因为前者调用方法没有事务支持，后者有 MANDATORY 事务，则在执行后者时会导致异常发生
     *
     * 注意 这里的方法调用，因为Spring AOP是切在方法层面上，方法内部的调用是不起作用的
     */
//    public void testMainNoException() {
//        serviceA.testA("无异常A插入");   //插入成功  节点1
//        testHasException();
//    }

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B插入");   //未执行  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //未执行  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService8 testMainService = (TestMainService8) context.getBean("testMainService8");

        //只能通过 service 调用的方法，才会被 Spring AOP 管理，没有事务就直接抛出异常
        testMainService.testHasException();
    }
}
