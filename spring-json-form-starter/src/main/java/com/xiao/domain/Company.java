package com.xiao.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author sunjinwei
 * @Date 8/22/21 6:09 PM
 * @Description 使用泛型定义实体
 **/
@Data
public class Company<T> implements Serializable {

    private Long id;

    private String name;


    /**
     * 定义泛型类型
     */
    private T childCompany;

}
