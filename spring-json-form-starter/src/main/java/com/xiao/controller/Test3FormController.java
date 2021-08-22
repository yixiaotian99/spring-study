package com.xiao.controller;

import cn.hutool.json.JSONUtil;
import com.xiao.aop.test3.RequestCustomBody;
import com.xiao.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:52 PM
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("test3")
public class Test3FormController {

    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(String roleId) {

        log.info("测试添加用户, roleId:{}", roleId);
        return ResponseEntity.ok("success!");
    }


    /**
     * 使用 www-form-urlencoded 方式提交
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/addUser2")
    public ResponseEntity<String> addUser2(@RequestCustomBody UserVO userVO) {

        log.info("【有注解使用 www-form-urlencoded 方式提交】测试添加用户2, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }

    /**
     * 使用 json 提交
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/addUser3")
    public ResponseEntity<String> addUser3(@RequestCustomBody UserVO userVO) {

        log.info("【有注解使用 json 方式提交】测试添加用户3, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }

    /**
     * 无注解表单提交
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/addUser4")
    public ResponseEntity<String> addUser4(UserVO userVO) {

        log.info("【无注解使用 www-form-urlencoded 方式提交】测试添加用户4, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }

    /**
     * 无注解json提交
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/addUser5")
    public ResponseEntity<String> addUser5(UserVO userVO) {

        log.info("【无注解使用 json 方式提交】测试添加用户5, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }

}
