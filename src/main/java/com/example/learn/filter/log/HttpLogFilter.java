package com.example.learn.filter.log;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName HttpLogFilter
 * @Description 日志过滤器
 * @Date 2022/11/17 18:57
 * @Author pluto
 */
@Component
@Slf4j
public class HttpLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/alive") || (StringUtils.hasText(request.getContentType()) && request.getContentType().contains("multipart/form-data"))) {
            // 移除无用日志
            filterChain.doFilter(request, response);
            return;
        }
        String requestId = UUID.randomUUID().toString();
        MultiReadRequestWrapper multiReadRequest = new MultiReadRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponse = new ContentCachingResponseWrapper(response);
        this._logRequest(multiReadRequest, requestId);
        long now = System.currentTimeMillis();
        filterChain.doFilter(multiReadRequest, contentCachingResponse);
        this._logResponse(multiReadRequest, contentCachingResponse, requestId, System.currentTimeMillis() - now);
    }

    private void _logRequest(MultiReadRequestWrapper request, String requestId) {
        HttpRequestLog.HttpRequestLogBuilder logBuilder = HttpRequestLog.builder();
        logBuilder.protocol(request.getProtocol());
        logBuilder.header(request.getHeader("Pluto"));
        logBuilder.method(request.getMethod());
        String query = request.getQueryString();
        String url = request.getRequestURL().toString();
        logBuilder.url(query == null ? url : url + "?" + query);

        try {
            String e = IOUtils.toString(request.getInputStream(), Charsets.UTF_8);
            if (StringUtils.hasText(e)) {
                logBuilder.body(e);
            }

            log.info("\n" + "requestId:" + requestId + logBuilder.build().toString());
        } catch (Exception e) {
            log.error("Failed to log request.", e);
        }
    }

    private void _logResponse(MultiReadRequestWrapper request, ContentCachingResponseWrapper response,
                              String requestId, long tookMs) {
        HttpResponseLog.HttpResponseLogBuilder logBuilder = HttpResponseLog.builder();
        String query = request.getQueryString();
        String url = request.getRequestURL().toString();
        logBuilder.url(query == null ? url : url + "?" + query);
        logBuilder.code(response.getStatus());
        logBuilder.tookMs(tookMs);
        try {
            String e = new String(response.getContentAsByteArray(), Charsets.UTF_8);
            logBuilder.body(e);
            log.info("\n" + "requestId:" + requestId + logBuilder.build().toString());
        } catch (Exception e) {
            log.error("Failed to log response.", e);
        } finally {
            try {
                response.copyBodyToResponse();
            } catch (Exception e) {
                log.error("Failed to copy contentCachingResponse.", e);
            }

        }
    }
}
