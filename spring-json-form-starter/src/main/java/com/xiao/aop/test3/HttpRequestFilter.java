package com.xiao.aop.test3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author sunjinwei
 * @Date 8/21/21 11:27 PM
 * @Description 拦截器
 * @see https://jiacyer.com/2019/01/23/Java-Spring-form-json-compatibility/
 **/
@Slf4j
public class HttpRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //判断请求类型
        if(request.getContentType() != null){
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {

                //可重复读取流 ContentCachingRequestWrapper
                ServletRequest requestWrapper = new ContentCachingRequestWrapper(request);
                filterChain.doFilter(requestWrapper, servletResponse);
                return;

            } else if (request.getContentType().contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {

                //这里添加判断，只有指定路径，才进行 form 转 json 操作
                filterChain.doFilter(new BodyRequestWrapper(request), servletResponse);
                return;

            } else {

                //其他类型，如文件上传
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }

        filterChain.doFilter(request, servletResponse);
        return;
    }

    @Override
    public void destroy() {
    }
}
