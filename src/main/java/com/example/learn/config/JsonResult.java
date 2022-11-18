package com.example.learn.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName JsonResult
 * @Description 统一接口返回值
 * @Date 2022/11/15 15:00
 * @Author pluto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

    //状态码
    private Integer code;
    //数据
    private Object data;
    //是否成功
    private Boolean success;

    private String msg;

    public JsonResult(Integer code, Boolean success) {
        this.code = code;
        this.success = success;
    }

    public JsonResult(Integer code, Object data, Boolean success) {
        this.code = code;
        this.data = data;
        this.success = success;
    }

    /**
     * 正确时返回的信息
     */
    public static JsonResult ok(Object data) {
        return new JsonResult(200, data, true);
    }

    public static JsonResult ok() {
        return new JsonResult(200, true);
    }

    /**
     * 错误时返回的信息
     */
    public static JsonResult error(String msg) {
        return new JsonResult(500, null, false, msg);
    }
}
