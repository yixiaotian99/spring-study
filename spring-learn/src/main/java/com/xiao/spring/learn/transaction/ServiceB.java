package com.xiao.spring.learn.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author sunjinwei
 * @Date 1/24/21 2:53 PM
 * @Description 用于模拟一个暴露的 service 服务
 **/
@Service
public class ServiceB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 模拟执行插入数据库操作
     */
    public void testB(String userName) {
        jdbcTemplate.execute("insert into tt_user (user_name, sex) values ('" + userName + "', 1);");
    }

}
