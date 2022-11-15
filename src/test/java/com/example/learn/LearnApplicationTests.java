package com.example.learn;

import com.example.learn.data.dto.AccountDTO;
import com.example.learn.data.from.AccountFrom;
import com.example.learn.data.pojo.Account;
import com.example.learn.mapper.AccountMapper;
import com.example.learn.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LearnApplicationTests {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void query() {
        List<Account> all = accountMapper.findAll();
        all.forEach(System.out::println);
    }


    @Test
    public void insert() {
        AccountFrom from = new AccountFrom();
        from.setName("pluto");
        from.setAge(18);
        from.setPhone("18500088812");
        from.setAddress("上海市徐汇区");
        accountMapper.add(from);
    }

    @Test
    public void findByDTO() {
        AccountDTO dto = new AccountDTO();
        dto.setId(1);
        List<Account> mapper = accountMapper.findByDTO(dto);
        mapper.forEach(System.out::println);
    }

    @Test
    public void getredis() {
        String value = redisUtil.get("pluto");
        if (StringUtils.isBlank(value)){
            System.out.println("key no value");
        } else {
            System.out.println(value);
        }
    }

    @Test
    public void setRedis() {
        redisUtil.set("pluto","testValue");
    }

    @Test
    public void removeRedis() {
        redisUtil.del("pluto");
    }

}
