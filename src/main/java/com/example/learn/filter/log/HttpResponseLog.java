package com.example.learn.filter.log;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HttpResponseLog
 * @Description 响应参数日志
 * @Date 2022/11/17 19:00
 * @Author pluto
 */
public class HttpResponseLog {

    private Integer code;
    private String msg;
    private String url;
    private List<String> headers = Lists.newArrayList();
    private String body;
    private long tookMs;
    private String errorMsg;

    @Override
    public String toString() {
        if (this.errorMsg == null) {
            String headers = "[\n\t\t" + Joiner.on(",\n\t\t").join(this.headers) + "\n\t]";
            return "\n<--response start\n{\n\tcode: " + this.code + ",\n\tmsg: " + this.msg + ",\n\turl: " + this.url + ",\n\theaders: " + headers + "," + (this.body != null ? "\n\tbody: " + this.body.replaceAll("\\n", "") + "," : "") + "\n\ttookMs: " + this.tookMs + "ms\n}\n<--response end";
        } else {
            return "\n<--response start\n{\n\turl: " + this.url + ",\n\terrorMsg: " + this.errorMsg + "\n}\n<--response end";
        }
    }

    HttpResponseLog(Integer code, String msg, String url, List<String> headers, String body, long tookMs, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.tookMs = tookMs;
        this.errorMsg = errorMsg;
    }

    public static HttpResponseLogBuilder builder() {
        return new HttpResponseLogBuilder();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getUrl() {
        return this.url;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }

    public long getTookMs() {
        return this.tookMs;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public static class HttpResponseLogBuilder {
        private Integer code;
        private String msg;
        private String url;
        private ArrayList<String> headers;
        private String body;
        private long tookMs;
        private String errorMsg;

        HttpResponseLogBuilder() {
        }

        public HttpResponseLogBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public HttpResponseLogBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public HttpResponseLogBuilder url(String url) {
            this.url = url;
            return this;
        }

        public HttpResponseLogBuilder header(String header) {
            if (this.headers == null) {
                this.headers = new ArrayList();
            }

            this.headers.add(header);
            return this;
        }

        public HttpResponseLogBuilder headers(Collection<? extends String> headers) {
            if (headers == null) {
                throw new NullPointerException("headers cannot be null");
            } else {
                if (this.headers == null) {
                    this.headers = new ArrayList();
                }

                this.headers.addAll(headers);
                return this;
            }
        }

        public HttpResponseLogBuilder clearHeaders() {
            if (this.headers != null) {
                this.headers.clear();
            }

            return this;
        }

        public HttpResponseLogBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpResponseLogBuilder tookMs(long tookMs) {
            this.tookMs = tookMs;
            return this;
        }

        public HttpResponseLogBuilder errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public HttpResponseLog build() {
            List headers;
            switch(this.headers == null ? 0 : this.headers.size()) {
                case 0:
                    headers = Collections.emptyList();
                    break;
                case 1:
                    headers = Collections.singletonList((String)this.headers.get(0));
                    break;
                default:
                    headers = Collections.unmodifiableList(new ArrayList(this.headers));
            }

            return new HttpResponseLog(this.code, this.msg, this.url, headers, this.body, this.tookMs, this.errorMsg);
        }

        public String toString() {
            return "HttpResponseLog.HttpResponseLogBuilder(code=" + this.code + ", msg=" + this.msg + ", url=" + this.url + ", headers=" + this.headers + ", body=" + this.body + ", tookMs=" + this.tookMs + ", errorMsg=" + this.errorMsg + ")";
        }
    }
}
