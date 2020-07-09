package com.xiao.spring.learn.source.simple;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 2020-07-05 15:03
 * @Description TODO
 **/
@Component
public class UserService {

    @Getter
    @Setter
    private String name = "普通Bean";


    public UserService() {
        System.out.println("userService constructor");
    }
}
