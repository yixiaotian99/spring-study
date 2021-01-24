package com.xiao.spring.learn.transaction.required;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author sunjinwei
 * @Date 1/24/21 2:53 PM
 * @Description 用于模拟一个暴露的 service 服务
 **/
@Component
public class ServiceA {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 模拟执行插入数据库操作
     */
    public void testA(String userName) {
        jdbcTemplate.execute("insert into tt_user (user_name, sex) values ('" + userName + "', 0);");
    }

}
