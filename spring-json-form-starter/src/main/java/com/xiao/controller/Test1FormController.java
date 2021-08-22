package com.xiao.controller;

import cn.hutool.json.JSONUtil;
import com.xiao.aop.test1.RequestJson;
import com.xiao.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:52 PM
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("test1")
public class Test1FormController {

    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestJson String roleId) {

        log.info("测试添加用户, roleId:{}", roleId);
        return ResponseEntity.ok("success!");
    }


    @PostMapping(value = "/addUser2")
    public ResponseEntity<String> addUser2(@RequestJson UserVO userVO) {

        log.info("测试添加用户2, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }


    @PostMapping(value = "/addUser3")
    public ResponseEntity<String> addUser3(@ModelAttribute("userVO") UserVO userVO) {

        log.info("测试添加用户3, userVO:{}", JSONUtil.toJsonStr(userVO));
        return ResponseEntity.ok("success!");
    }


    @ModelAttribute
    public void preRun(@RequestParam(value="body",required=false) String body, Map<String,Object> map) {
        System.out.println("Test Pre-Run");
        log.info("原始body:{}", body);
        UserVO userVO = JSONUtil.toBean(body, UserVO.class);
        map.put("userVO", userVO);
    }
}
