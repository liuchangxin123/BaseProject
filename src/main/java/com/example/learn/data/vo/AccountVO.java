package com.example.learn.data.vo;

import lombok.Data;

/**
 * @ClassName AccountVO
 * @Description TODO
 * @Date 2022/11/15 10:28
 * @Author pluto
 */
@Data
public class AccountVO {

    private Integer id;

    private String name;

    private Integer age;

    private String phone;

    private String address;

    private Boolean disable;
}
