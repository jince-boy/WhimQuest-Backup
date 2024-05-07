package com.whim.common.filter.wrapper;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Jince
 * @since: 2023.12.26 下午 04:45
 * @description: 自定义请求包装类, 解决过滤器中post请求获取json数据问题
 */
@Slf4j
public class BodyReaderRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public BodyReaderRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException {
        super(request);
        request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try (InputStream inputStream = request.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream, request.getCharacterEncoding()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.info("获取数据流出现问题");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.info("自定义请求包装类出现异常:{}", e.getMessage());
                }
            }
        }
        body = sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int available() {
                return body.length;
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    //转换成String
    public JSONObject getBodyString() {
        String s = new String(body, StandardCharsets.UTF_8);
        return JSONObject.parseObject(s);
    }

}
