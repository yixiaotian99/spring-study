package com.xiao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author sunjinwei
 * @Date 8/22/21 5:26 PM
 * @Description TODO
 **/
@Data
public class Address implements Serializable {

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
