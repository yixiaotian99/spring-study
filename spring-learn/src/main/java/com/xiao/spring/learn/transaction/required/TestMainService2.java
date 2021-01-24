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
public class TestMainService2 {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;

    /**
     * 使用 REQUIRED 事务
     * 如果当前存在事务，就加入到当前事务中，如果当前不存在事务，就创建一个新事务
     *
     * 在 REQUIRED (默认)事务前提下，结论：节点1和节点2都执行了，但是回滚了，会占用mysql数据库自增主键值，节点3未执行
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testMainNoException() {
        serviceA.testA("无异常A插入");   //插入回滚  节点1
        testHasException();
    }

    /**
     * 多次调用 serviceB 方法
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testHasException() {
        serviceB.testB("无异常B插入");   //插入回滚  节点2
        int i = 1 / 0;   //模拟出现异常
        serviceB.testB("前面有异常B插入"); //未插入  节点3
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TestMainService2 testMainService = (TestMainService2) context.getBean("testMainService2");
        testMainService.testMainNoException();
    }
}
