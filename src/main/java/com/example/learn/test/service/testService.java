package com.example.learn.test.service;

import com.example.learn.data.from.AccountFrom;
import com.example.learn.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName testService
 * @Description TODO
 * @Date 2022/11/21 14:12
 * @Author pluto
 */
@Service
public class testService {

    @Autowired
    private firstService firstService;

     @Autowired
     private AccountMapper accountMapper;


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void transactional() {
        AccountFrom accountFrom = new AccountFrom();
        accountFrom.setName("测试");
        accountFrom.setPhone("测试");
        accountFrom.setAge(0);
        accountFrom.setAddress("测试");
        accountMapper.add(accountFrom);
        firstService.aa();
            int ii = 1/0;
    }
}
