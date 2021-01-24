package com.xiao.spring.learn.transaction.suppors;

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
public class TestMainService6 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;


    /**
     * 模拟在没有事务的情况下，如果方法发生异常，会怎么样
     * <p>
     * <p>
     * 被调用方法 testHasException 使用 SUPPORS
     * 如果当前存在事务，就加入到当前事务中，如果当前不存在事务，则以非事务方式运行
     * <p>
     * 执行结论：节点1、节点2都执行成功，节点3未执行
     * 调用链条  testMainNoException --> 内部调用 testHasException
     * 因为前者调用方法没有事务支持，后者有 SUPPORTS 事务支持，最终是每条 sql 都是一个事务
     */
    public void testMainNoException() {
        serviceA.testA("无异常A插入");   //插入成功  节点1
        testHasException();
    }

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void testHasException() {
        serviceB.testB("无异常B插入");   //插入成功  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //未执行  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService6 testMainService = (TestMainService6) context.getBean("testMainService6");
        testMainService.testMainNoException();
    }
}
