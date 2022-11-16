package com.example.learn.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.learn.config.JsonResult;
import com.example.learn.data.from.LoginFrom;
import com.example.learn.data.vo.LoginVO;
import com.example.learn.service.dao.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.learn.controller.Routes.*;

/**
 * @ClassName LoginController
 * @Description 登录/注册
 * @Date 2022/11/16 13:37
 * @Author pluto
 */
@RestController
@Api(tags = "登录/注册接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(LOGIN)
    @ApiOperation("登录")
    public JsonResult login(@ApiParam(name = "username", value = "用户名") @RequestParam("username") String username,
                            @ApiParam(name = "password", value = "密码") @RequestParam("password") String password) {
        LoginVO vo = loginService.login(username, password);
        if (vo == null) {
            return JsonResult.error("账号密码不对");
        } else {
            StpUtil.login(vo.getId(), new SaLoginModel()
                    .setIsLastingCookie(true)// 是否为持久Cookie（临时Cookie在浏览器关闭时会自动删除，持久Cookie在重新打开后依然存在）
                    .setTimeout(60 * 60 * 24 * 7) // 指定此次登录token的有效期, 单位:秒 （如未指定，自动取全局配置的 timeout 值）
                    .setIsWriteHeader(true) // 是否在登录后将 Token 写入到响应头
            );
            SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
            return JsonResult.ok(saTokenInfo);
        }

    }

    @PostMapping(REGISTER)
    @ApiOperation("注册")
    public JsonResult register(@RequestBody LoginFrom from) {
        return JsonResult.ok(loginService.register(from));
    }

    @GetMapping(LOGINOUT)
    @ApiOperation("登出")
    public JsonResult loginout(@PathVariable String token) {
        StpUtil.logoutByTokenValue(token);
        return JsonResult.ok();
    }

}
