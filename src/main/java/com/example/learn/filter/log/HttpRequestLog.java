package com.example.learn.filter.log;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HttpRequestLog
 * @Description 请求参数日志
 * @Date 2022/11/17 18:59
 * @Author pluto
 */
public class HttpRequestLog {
    private String protocol;
    private String url;
    private String method;
    private List<String> headers = Lists.newArrayList();
    private String body;

    @Override
    public String toString() {
        String headers = "[\n\t\t" + Joiner.on(",\n\t\t").join(this.headers) + "\n\t]";
        return "\n-->request start\n{\n\turl: " + this.url + ",\n\tmethod: " + this.method + ",\n\theaders: " + headers + "," + (this.body != null ? "\n\tbody: " + this.body.replaceAll("\\n", "") + "," : "") + "\n\tprotocol: " + this.protocol + "\n}\n-->request end";
    }

    HttpRequestLog(String protocol, String url, String method, List<String> headers, String body) {
        this.protocol = protocol;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    public static HttpRequestLogBuilder builder() {
        return new HttpRequestLogBuilder();
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }

    public static class HttpRequestLogBuilder {
        private String protocol;
        private String url;
        private String method;
        private ArrayList<String> headers;
        private String body;

        HttpRequestLogBuilder() {
        }

        public HttpRequestLogBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public HttpRequestLogBuilder url(String url) {
            this.url = url;
            return this;
        }

        public HttpRequestLogBuilder method(String method) {
            this.method = method;
            return this;
        }

        public HttpRequestLogBuilder header(String header) {
            if (this.headers == null) {
                this.headers = new ArrayList();
            }
            if (StringUtils.isBlank(header)) {
                return this;
            }

            this.headers.add(header);
            return this;
        }

        public HttpRequestLogBuilder headers(Collection<? extends String> headers) {
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

        public HttpRequestLogBuilder clearHeaders() {
            if (this.headers != null) {
                this.headers.clear();
            }

            return this;
        }

        public HttpRequestLogBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequestLog build() {
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

            return new HttpRequestLog(this.protocol, this.url, this.method, headers, this.body);
        }

        public String toString() {
            return "HttpRequestLog.HttpRequestLogBuilder(protocol=" + this.protocol + ", url=" + this.url + ", method=" + this.method + ", headers=" + this.headers + ", body=" + this.body + ")";
        }
    }

}
