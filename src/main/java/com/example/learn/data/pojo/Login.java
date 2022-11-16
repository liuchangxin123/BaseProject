package com.example.learn.data.pojo;

import lombok.Data;

/**
 * @ClassName Login
 * @Description 登录
 * @Date 2022/11/16 11:39
 * @Author pluto
 */
@Data
public class Login {

    private Integer id;

    private String phone;

    private String username;

    private String loginType;

    private String password;
}
