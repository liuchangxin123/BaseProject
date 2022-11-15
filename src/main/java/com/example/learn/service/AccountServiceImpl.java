package com.example.learn.service;

import com.example.learn.data.dto.AccountDTO;
import com.example.learn.data.from.AccountFrom;
import com.example.learn.data.pojo.Account;
import com.example.learn.mapper.AccountMapper;
import com.example.learn.service.dao.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Date 2022/11/15 11:18
 * @Author pluto
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public List<Account> findByDTO(AccountDTO accountDTO) {
        return accountMapper.findByDTO(accountDTO);
    }

    @Override
    public int add(AccountFrom accountFrom) {
        return accountMapper.add(accountFrom);
    }
}
