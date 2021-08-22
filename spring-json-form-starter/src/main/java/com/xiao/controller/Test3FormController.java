package com.xiao.controller;

import cn.hutool.json.JSONUtil;
import com.xiao.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping(value = "/addUser2")
    public ResponseEntity<String> addUser2(UserVO userVO) {

        log.info("测试添加用户2, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }


    @PostMapping(value = "/addUser3")
    public ResponseEntity<String> addUser3(UserVO userVO) {

        log.info("测试添加用户3, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }


}
