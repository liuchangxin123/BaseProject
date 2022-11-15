package com.example.learn.service.dao;

import com.example.learn.data.dto.AccountDTO;
import com.example.learn.data.from.AccountFrom;
import com.example.learn.data.pojo.Account;

import java.util.List;

/**
 * @ClassName AccountService
 * @Description TODO
 * @Date 2022/11/15 11:17
 * @Author pluto
 */
public interface AccountService {

    List<Account> findAll();

    List<Account> findByDTO(AccountDTO accountDTO);

    int add (AccountFrom accountFrom);
}
