package com.xiao.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.xiao.UserDAO;
import com.xiao.aop.test3.RequestCustomBody;
import com.xiao.domain.Company;
import com.xiao.domain.CompanyTaoBao;
import com.xiao.domain.UserVO;
import com.xiao.listener.UserVOListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:52 PM
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("test3")
public class Test3FormController {

    @Autowired
    private UserDAO userDAO;

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


    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DownloadData}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     * @see https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/web/WebTest.java
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserVO.class).sheet("用户模板").doWrite(data());
    }


    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UserVO.class, new UserVOListener(userDAO)).sheet().doRead();
        return "success";
    }


    private List<UserVO> data() {
        List<UserVO> list = new ArrayList<UserVO>();
        for (int i = 0; i < 10; i++) {
            UserVO data = new UserVO();
            data.setId(Long.valueOf(100 + i));
            data.setName("测试-" + i);
            data.setSex(RandomUtil.randomInt(0, 2));
            data.setBirthDay(RandomUtil.randomDate(new Date(), DateField.HOUR_OF_DAY, 1, 10));
            list.add(data);
        }
        return list;
    }
}
