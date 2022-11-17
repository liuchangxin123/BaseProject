//package com.example.learn.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @ClassName WebConfiguration
// * @Description JWT 拦截
// * @Date 2022/11/17 16:50
// * @Author pluto
// */
//@Configuration
//public class WebConfiguration implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    /**
//     * 配置拦截器、拦截路径
//     * 每次请求到拦截的路径，就会去执行拦截器中的方法
//     * @param
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/test/login");
//        WebMvcConfigurer.super.addInterceptors(registry);
//
//    }
//}
