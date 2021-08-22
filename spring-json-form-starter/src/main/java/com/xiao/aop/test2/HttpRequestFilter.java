//package com.xiao.aop.test2;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @Author sunjinwei
// * @Date 8/21/21 11:27 PM
// * @Description TODO
// **/
//public class HttpRequestFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        if (request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
//            /* 使用 ContentCachingRequestWrapper 需搭配 ExtendRequestParamArgumentResolver 扩展@RequestParam注解 */
//            ServletRequest requestWrapper = new ContentCachingRequestWrapper(request);
//            filterChain.doFilter(requestWrapper, servletResponse);
//        } else {
////            filterChain.doFilter(servletRequest, servletResponse);
//            ServletRequest requestWrapper = new JsonServletRequestWrapper(request);
//            String body = requestWrapper.getParameter("body");
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {}
//
//
//
////    @Override
////    public void init(FilterConfig filterConfig) throws ServletException {}
////
////    @Override
////    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        if (request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
////            /* 使用自定义 JsonServletRequestWrapper 则无需扩展@RequestParam注解  */
////            filterChain.doFilter(servletRequest, servletResponse);
////        } else {
////            ServletRequest requestWrapper = new JsonServletRequestWrapper(request);
////            filterChain.doFilter(servletRequest, servletResponse);
////        }
////    }
////
////    @Override
////    public void destroy() {}
//}
