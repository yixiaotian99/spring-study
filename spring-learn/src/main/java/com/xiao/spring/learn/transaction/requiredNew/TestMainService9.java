package com.xiao.spring.learn.transaction.requiredNew;

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
public class TestMainService9 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;

    /**
     * 使用 REQUIRED_NEW 事务
     * 创建一个新的事务，如果当前存在事务就挂起当前事务
     * <p>
     * <p>
     * 结论：节点1，节点2、节点3回滚
     * 调用链条 testMainHasException-->内部调用 testNoException
     * 前者有 REQUIRED 事务，后者有 REQUIRED_NEW 事务，执行时会将前者事务挂起，后者创建一个新事务
     * 后者新事务提交并不受前者事务的影响，执行成功
     * 前者因为事务被挂起，等于后者执行完成后事务继续执行，发生异常事务回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testMainHasException() {
        serviceA.testA("异常前A插入");   //插入回滚  节点1
        testNoException();   //service方法内部的调用，REQUIRED_NEW不起作用，因为 Spring AOP切面特性
        int i = 1 / 0;   //模拟出现异常
    }

    /**
     * 多次调用 serviceB 方法
     *
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void testNoException() {
        serviceB.testB("前面有异常B1插入");   //插入回滚  节点2

        serviceB.testB("前面有异常B2插入");   //插入回滚  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService9 testMainService = (TestMainService9) context.getBean("testMainService9");
        testMainService.testMainHasException();
    }
}
