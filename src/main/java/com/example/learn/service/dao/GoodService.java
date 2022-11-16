package com.example.learn.service.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.learn.data.pojo.Good;

import java.util.List;

/**
 * @ClassName GoodService
 * @Description TODO
 * @Date 2022/11/16 17:47
 * @Author pluto
 */
public interface GoodService extends IService<Good> {

    List<Good> findBySn(List<String> sn);
}
