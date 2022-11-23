package com.example.learn.test.service;

import com.example.learn.data.pojo.Good;
import com.example.learn.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @ClassName firstService
 * @Description TODO
 * @Date 2022/11/21 14:12
 * @Author pluto
 */
@Service
public class firstService {

    @Autowired
    private GoodMapper goodMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void aa() {
        Good good = new Good();
        good.setSn("测试");
        good.setName("测试");
        good.setOperate("测试");
        good.setCostPrice(BigDecimal.ONE);
        good.setAlias("测试");
        good.setPicture("ceshi");
        good.setStock(1);
        good.setStatus(true);
        good.setOperate("ceshi");
        good.setCostPrice(BigDecimal.ONE);
        good.setPrice(BigDecimal.ONE);
        goodMapper.insert(good);




    }
}
