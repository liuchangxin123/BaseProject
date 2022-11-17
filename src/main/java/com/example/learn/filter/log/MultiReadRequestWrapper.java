package com.example.learn.filter.log;

import com.google.common.io.ByteStreams;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName MultiReadRequestWrapper
 * @Description 日志输出wrapper
 * @Date 2022/11/17 18:58
 * @Author pluto
 */
public class MultiReadRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body = ByteStreams.toByteArray(super.getInputStream());

    public MultiReadRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
    }

    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public ServletInputStream getInputStream() throws IOException {
        return new RequestBodyCachingInputStream(this.body);
    }

    private class RequestBodyCachingInputStream extends ServletInputStream {
        private byte[] body;
        private int lastIndexRetrieved = -1;
        private ReadListener listener;

        public RequestBodyCachingInputStream(byte[] body) {
            this.body = body;
        }

        public int read() throws IOException {
            if (this.isFinished()) {
                return -1;
            } else {
                int i = this.body[this.lastIndexRetrieved + 1];
                ++this.lastIndexRetrieved;
                if (this.isFinished() && this.listener != null) {
                    try {
                        this.listener.onAllDataRead();
                    } catch (IOException var3) {
                        this.listener.onError(var3);
                        throw var3;
                    }
                }

                return i;
            }
        }

        public boolean isFinished() {
            return this.lastIndexRetrieved == this.body.length - 1;
        }

        public boolean isReady() {
            return this.isFinished();
        }

        @Override
        public void setReadListener(ReadListener listener) {
            if (listener == null) {
                throw new IllegalArgumentException("listener cann not be null");
            } else if (this.listener != null) {
                throw new IllegalArgumentException("listener has been set");
            } else {
                this.listener = listener;
                if (!this.isFinished()) {
                    try {
                        listener.onAllDataRead();
                    } catch (IOException var4) {
                        listener.onError(var4);
                    }
                } else {
                    try {
                        listener.onAllDataRead();
                    } catch (IOException var3) {
                        listener.onError(var3);
                    }
                }

            }
        }

        public int available() throws IOException {
            return this.body.length - this.lastIndexRetrieved - 1;
        }

        public void close() throws IOException {
            this.lastIndexRetrieved = this.body.length - 1;
            this.body = null;
        }
    }
}
