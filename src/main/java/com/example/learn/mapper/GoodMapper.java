package com.example.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.learn.data.pojo.Good;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName GoodMapper
 * @Description TODO
 * @Date 2022/11/16 17:43
 * @Author pluto
 */
@Mapper
public interface GoodMapper extends BaseMapper<Good> {
}
