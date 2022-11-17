package com.example.learn.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName BaseController
 * @Description 公共类
 * @Date 2022/11/17 10:29
 * @Author pluto
 */
public abstract class BaseController {

    protected HttpServletResponse writeWorkbookToFile(HttpServletResponse response, String workBookName) throws UnsupportedEncodingException {
        // 设置文本内省
        response.setContentType("application/vnd.ms-excel");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应头
        response.setHeader("Content-Disposition",
                "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(workBookName, "UTF-8") + ".xlsx");
        return response;
    }
}
