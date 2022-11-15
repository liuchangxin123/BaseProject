package com.example.learn.filter;

import com.example.learn.config.JsonResult;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @ClassName ExceptionHandler
 * @Description TODO
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
