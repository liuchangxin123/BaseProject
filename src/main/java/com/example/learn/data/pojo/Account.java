package com.example.learn.data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Account
 * @Description TODO
 * @Date 2022/11/15 10:23
 * @Author pluto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;

    private String name;

    private Integer age;

    private String phone;

    private String address;

    private Boolean disable;
}
