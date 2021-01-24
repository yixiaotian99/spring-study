package com.xiao.spring.learn.transaction.none;

import com.xiao.spring.learn.transaction.ServiceA;
import com.xiao.spring.learn.transaction.ServiceB;
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
     * <p>
     * 在没有事务前提下，结论：节点1和节点2都成功了，节点3未执行
     * 每一个与数据库的交互，都是一个事务，互相之间不影响
     */
    public void testMainNoException() {
        serviceA.testA("无异常A插入");   //插入成功  节点1
        testHasException();
    }

    /**
     * 多次调用 serviceB 方法
     */
    public void testHasException() {
        serviceB.testB("无异常B插入");   //插入成功  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //未执行  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService testMainService = (TestMainService) context.getBean("testMainService");
        testMainService.testMainNoException();
    }
}
