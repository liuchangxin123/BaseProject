package com.example.learn.service.dao;

import com.example.learn.data.from.LoginFrom;
import com.example.learn.data.vo.LoginVO;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Date 2022/11/16 13:39
 * @Author pluto
 */
public interface LoginService {

    LoginVO login(String username, String password);

    Boolean register(LoginFrom from);
}
