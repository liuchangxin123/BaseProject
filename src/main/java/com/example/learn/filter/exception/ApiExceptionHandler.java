package com.example.learn.filter.exception;

import com.example.learn.config.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @ClassName ExceptionHandler
 * @Description 全局异常处理
 * @Date 2022/11/15 15:32
 * @Author pluto
 */
@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(BindException.class)
    public JsonResult runtimeExceptionHandler(BindException ex) {
        log.error("运行时异常：{}", ex.getMessage(), ex);
        return JsonResult.error(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
