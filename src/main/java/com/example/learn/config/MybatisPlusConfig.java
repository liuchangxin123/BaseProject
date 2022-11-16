package com.example.learn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description MybatisPlusConfig
 * @Date 2022/11/16 17:39
 * @Author pluto
 */
@Configuration
@MapperScan(basePackages = "com.example.learn.mapper")
public class MybatisPlusConfig {

    /***
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
