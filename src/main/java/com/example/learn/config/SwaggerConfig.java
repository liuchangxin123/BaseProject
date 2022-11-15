package com.example.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @ClassName SwaggerConfig
 * @Description Swagger配置类
 * @Date 2022/11/15 13:49
 * @Author pluto
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev", "test"})
public class SwaggerConfig {

    public static final String SWAGGER_GROUP = "Pluto";
    public static final int BAD_REQUEST_CODE = 400;

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responses = new ArrayList<>();
        responses.add(new ResponseMessageBuilder()
                .code(BAD_REQUEST_CODE)
                .message("请求参数错误")
                .responseModel(new ModelRef("JsonResult"))
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(SWAGGER_GROUP)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .directModelSubstitute(OffsetDateTime.class, String.class)
                .genericModelSubstitutes(Optional.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responses)
                .globalResponseMessage(RequestMethod.POST, responses)
                .globalResponseMessage(RequestMethod.PUT, responses)
                .globalResponseMessage(RequestMethod.DELETE, responses)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(basePackage("com.example.learn.controller"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build().enableUrlTemplating(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("Pluto项目中Swagger2接口规范")
                //文档描述
                .description("接口说明")
                //服务条款URL
                .termsOfServiceUrl("http://localhost:8080/")
                //版本号
                .version("1.0.0")
                .build();
    }
}
