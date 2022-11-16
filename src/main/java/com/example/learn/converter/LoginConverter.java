package com.example.learn.converter;

import com.example.learn.data.dto.LoginDTO;
import com.example.learn.data.from.LoginFrom;
import com.example.learn.data.pojo.Login;
import com.example.learn.data.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName LoginConverter
 * @Description 登录信息相关转换
 * @Date 2022/11/16 13:44
 * @Author pluto
 */
@Component
public class LoginConverter {

    public LoginDTO toLoginDTO(LoginFrom from) {
        LoginDTO dto = new LoginDTO();
        BeanUtils.copyProperties(from, dto);
        if (StringUtils.isNotBlank(from.getPhone())) {
            dto.setLoginType("PHONE");
        } else if (StringUtils.isNotBlank(from.getUsername())) {
            dto.setLoginType("USER_NAME");
        }
        return dto;
    }

    public LoginVO toLoginVO(Login login) {
        if (login == null) {
            return null;
        }
        LoginVO vo = new LoginVO();
        BeanUtils.copyProperties(login, vo);
        return vo;
    }
}
