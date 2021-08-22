package com.xiao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author sunjinwei
 * @Date 8/20/21 11:55 PM
 * @Description TODO
 **/
@Data
public class UserVO implements Serializable {

    private Long id;

    private String name;

    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDay;

    private List<Address> addressList;
}
