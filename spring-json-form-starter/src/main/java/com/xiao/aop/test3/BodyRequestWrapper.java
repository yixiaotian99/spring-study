package com.xiao.aop.test3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * @Author sunjinwei
 * @Date 8/22/21 10:43 AM
 * @Description 更新body中值
 * @see https://blog.csdn.net/weixin_41677422/article/details/108769682
 **/
@Slf4j
public class BodyRequestWrapper extends HttpServletRequestWrapper {


    /**
     * 定义字符集合
     */
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 流重写后消息体
     */
    private byte[] body = new byte[0];

    /**
     * 是否form表单重写
     */
    private boolean contentTypeOverride = Boolean.FALSE;

    /**
     * 前端使用 body={"id":1, "name":"张三"} 这种结构传值
     *
     * @param request
     * @throws IOException
     */
    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        //获取 www-form-urlencoded 之后的值
        String formBody = request.getParameter("body");
        log.info("读取request中流原始数据, formBody:{}", formBody);

        //如果是post请求，将消息写入到inputstream流中
        if (StringUtils.isNotEmpty(formBody)) {

            //准备写入到request流消息体
            body = formBody.getBytes(CHARSET_UTF8);

            //打标记为form强制转json
            contentTypeOverride = Boolean.TRUE;
        }

        request.setAttribute("contentTypeOverride", contentTypeOverride);
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    /**
     * 在调用getInputStream函数时，创建新的流
     *
     * @return
     */
    @Override
    public ServletInputStream getInputStream() {
        return new MyServletInputStream(new ByteArrayInputStream(body));
    }

    class MyServletInputStream extends ServletInputStream {
        private InputStream inputStream;

        public MyServletInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

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
    }

    /**
     * 重写 getHeaders 为了让 form 表单从流中读取数据
     *
     * @param name
     * @return
     * @see https://blog.csdn.net/carrie__yang/article/details/73541897
     */
    @Override
    public Enumeration<String> getHeaders(String name) {
        if (null != name && name.equalsIgnoreCase("Content-Type")) {
            return new Enumeration<String>() {
                private boolean hasGetted = false;

                @Override
                public String nextElement() {
                    if (hasGetted) {
                        throw new NoSuchElementException();
                    } else {
                        hasGetted = true;
//                        return "application/json;charset=utf-8";
                        return MediaType.APPLICATION_JSON_VALUE;
                    }
                }

                @Override
                public boolean hasMoreElements() {
                    return !hasGetted;
                }
            };
        }
        return super.getHeaders(name);
    }

    /**
     * 为了让form表单使用流获取数据，必须重写此方法
     * org.springframework.http.server.ServletServerHttpRequest#getBody()
     *
     * @return
     */
    @Override
    public String getContentType() {

        String formBody = super.getRequest().getParameter("body");

        //只有消息体中包含 body 字样，才进行表单重置
        if (StringUtils.isNotEmpty(formBody)) {
            return MediaType.APPLICATION_JSON_VALUE;
        }

        return super.getContentType();
    }
}


