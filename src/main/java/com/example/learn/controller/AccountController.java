package com.example.learn.controller;

import com.example.learn.data.pojo.Account;
import com.example.learn.service.dao.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @ClassName AccountController
 * @Description 账号管理
 * @Date 2022/11/15 11:19
 * @Author pluto
 */
@RestController
@Api(tags = "账号管理")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/pluto/account/find/all")
    @ApiOperation(value = "查询所有的账号信息")
    public List<Account> findAll() {
        return accountService.findAll();
    }

}
