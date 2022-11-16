package com.example.learn.data.from;

import lombok.Data;

/**
 * @ClassName LoginFrom
 * @Description TODO
 * @Date 2022/11/16 11:46
 * @Author pluto
 */
@Data
public class LoginFrom {

    private String phone;

    private String username;

    private String loginType;

    private String password;
}
