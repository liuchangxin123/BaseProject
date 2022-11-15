package com.example.learn.data.dto;

import lombok.Data;

/**
 * @ClassName AccountDTO
 * @Description TODO
 * @Date 2022/11/15 10:27
 * @Author pluto
 */
@Data
public class AccountDTO {

    private Integer id;

    private String name;

    private Integer age;

    private String phone;

    private String address;

    private Boolean disable;
}
