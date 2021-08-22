package com.xiao.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author sunjinwei
 * @Date 8/22/21 6:13 PM
 * @Description TODO
 **/
@Data
public class CompanyTaoBao implements Serializable {

    private Long id;

    private String name;

    private UserVO userVO;
}
