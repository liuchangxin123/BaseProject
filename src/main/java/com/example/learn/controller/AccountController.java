package com.example.learn.controller;

import com.example.learn.data.pojo.Account;
import com.example.learn.service.dao.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @ClassName AccountController
 * @Description TODO
 * @Date 2022/11/15 11:19
 * @Author pluto
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/pluto/account/find/all")
    public List<Account> findAll() {
        return accountService.findAll();
    }

}
