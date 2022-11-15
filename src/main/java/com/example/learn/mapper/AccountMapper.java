package com.example.learn.mapper;

import com.example.learn.data.dto.AccountDTO;
import com.example.learn.data.from.AccountFrom;
import com.example.learn.data.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName AccountMapper
 * @Description TODO
 * @Date 2022/11/15 10:25
 * @Author pluto
 */
@Mapper
@Repository
public interface AccountMapper {

    public List<Account> findAll();

    public List<Account> findByDTO(AccountDTO accountDTO);

    public int add (AccountFrom accountFrom);



}
