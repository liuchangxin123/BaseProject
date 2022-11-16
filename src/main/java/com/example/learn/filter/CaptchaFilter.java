package com.example.learn.filter;

import com.example.learn.filter.exception.ApiExceptionHandler;
import com.example.learn.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CaptchaFilter
 * @Description 验证码过滤器
 * @Date 2022/11/15 17:27
 * @Author pluto
 */

/**
 * 在验证码过滤器中，需要先判断请求是否是登录请求，若是登录请求，则进行验证码校验，从redis中通过userKey查找对应的验证码，
 * 看是否与前端所传验证码参数一致，当校验成功时，因为验证码是一次性使用的，一个验证码对应一个用户的一次登录过程，
 * 所以需用hdel将存储的HASH删除。当校验失败时，则交给登录认证失败处理器LoginFailureHandler进行处理
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

        private static final String CAPTCHA_KEY = "captcha";
        @Autowired
        RedisUtil redisUtil;

        @Autowired
        ApiExceptionHandler apiExceptionHandler;

        @Override
        protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

            String url = httpServletRequest.getRequestURI();
            if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
                // 校验验证码
                try {
                    validate(httpServletRequest);
                } catch (Exception e) {
                    apiExceptionHandler.runtimeExceptionHandler((BindException) e);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

        // 校验验证码逻辑
        private void validate(HttpServletRequest httpServletRequest) throws Exception {
            String code = httpServletRequest.getParameter("code");
            String key = httpServletRequest.getParameter("userKey");

            if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
                throw new Exception("验证码不能为空");
            }

            if (!code.equals(redisUtil.getMapString(CAPTCHA_KEY, key))) {
                throw new Exception("验证码不正确");
            }

            // 若验证码正确，执行以下语句
            // 一次性使用
            redisUtil.delete(CAPTCHA_KEY, key);
        }
}
