package com.example.learn.data.dto;

import lombok.Data;

/**
 * @ClassName LoginDTO
 * @Description TODO
 * @Date 2022/11/16 11:45
 * @Author pluto
 */
@Data
public class LoginDTO {

    private String phone;

    private String username;

    private String loginType;

    private String password;
}
