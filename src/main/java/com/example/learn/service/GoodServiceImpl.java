package com.example.learn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.learn.data.pojo.Good;
import com.example.learn.mapper.GoodMapper;
import com.example.learn.service.dao.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodServiceImpl
 * @Description TODO
 * @Date 2022/11/16 17:47
 * @Author pluto
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<Good> findBySn(List<String> sn) {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.in("sn", sn).eq("status", true);
        return goodMapper.selectList(wrapper);
    }
}
