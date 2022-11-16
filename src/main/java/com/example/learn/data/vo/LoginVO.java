package com.example.learn.data.vo;

import lombok.Data;

/**
 * @ClassName LoginVO
 * @Description 登录信息
 * @Date 2022/11/16 14:14
 * @Author pluto
 */
@Data
public class LoginVO {

    private Integer id;

    private String phone;

    private String username;

    private String loginType;

    private String password;
}
