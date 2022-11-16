package com.example.learn.mapper;

import com.example.learn.data.dto.LoginDTO;
import com.example.learn.data.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName LoginMapper
 * @Description 登录mapper
 * @Date 2022/11/16 11:38
 * @Author pluto
 */
@Repository
@Mapper
public interface LoginMapper {

    Login login(@Param("username") String username, @Param("password") String password);

    int register(LoginDTO loginDTO);
}
