package com.xiao.controller;

import cn.hutool.json.JSONUtil;
import com.xiao.aop.test3.RequestCustomBody;
import com.xiao.domain.Company;
import com.xiao.domain.CompanyTaoBao;
import com.xiao.domain.UserVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:52 PM
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("test3")
public class Test3FormController {

    @GetMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestParam("id") String id) {

        log.info("测试添加用户, id:{}", id);
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
     * * {
     * "id": 3,
     * "name": "张三",
     * "sex": 0,
     * "birthDay": "2021-01-01",
     * "addressList": [
     * {
     * "id": "1",
     * "name": "北京",
     * "updateTime": "2021-01-01 01:01:01"
     * },
     * {
     * "id": "2",
     * "name": "上海",
     * "updateTime": "2009-01-01 01:01:01"
     * }
     * ]
     * }
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


    /**
     * 使用泛型类型
     *
     * @param company
     * @return
     */
    @PostMapping(value = "/addUser6")
    public ResponseEntity<String> addUser6(@RequestCustomBody Company<CompanyTaoBao> company) {

        log.info("【有注解用泛型结构，使用 www-form-urlencoded 方式提交】测试添加用户6, company:{}", JSONUtil.toJsonStr(company));
        return ResponseEntity.ok("success!");
    }


    /**
     * 使用泛型类型
     *
     * @param company
     * @return
     */
    @PostMapping(value = "/addUser7")
    public ResponseEntity<String> addUser7(@RequestCustomBody Company<CompanyTaoBao> company) {

        log.info("【有注解用泛型结构，使用 json 方式提交】测试添加用户7, company:{}", JSONUtil.toJsonStr(company));
        return ResponseEntity.ok("success!");
    }
}
