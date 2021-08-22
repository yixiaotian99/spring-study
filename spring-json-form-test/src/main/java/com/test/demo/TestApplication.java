package com.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:50 PM
 * @Description TODO
 **/
@SpringBootApplication
public class TestApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
        System.out.println("**************** startup complete ****************");
    }
}
