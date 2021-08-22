package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:50 PM
 * @Description TODO
 **/
@SpringBootApplication
public class JsonFormApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(JsonFormApplication.class, args);
        System.out.println("**************** startup complete ****************");
    }
}
