package com.example.learn.service;

import com.example.learn.converter.LoginConverter;
import com.example.learn.data.dto.LoginDTO;
import com.example.learn.data.from.LoginFrom;
import com.example.learn.data.vo.LoginVO;
import com.example.learn.mapper.LoginMapper;
import com.example.learn.service.dao.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Description 登录service
 * @Date 2022/11/16 13:39
 * @Author pluto
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private LoginConverter loginConverter;

    @Override
    public LoginVO login(String username, String password) {
        return loginConverter.toLoginVO(loginMapper.login(username, password));
    }

    @Override
    public Boolean register(LoginFrom from) {
        LoginDTO dto = loginConverter.toLoginDTO(from);
        loginMapper.register(dto);
        return true;

    }
}
