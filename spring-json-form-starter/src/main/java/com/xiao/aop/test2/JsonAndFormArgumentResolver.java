//package com.xiao.aop.test2;
//
//import lombok.Setter;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @Author sunjinwei
// * @Date 8/21/21 11:22 PM
// * @Description 自定义 JsonAndFormArgumentResolver 类实现不同数据的 Resolver 分发
// * @see https://jiacyer.com/2019/01/23/Java-Spring-form-json-compatibility/
// **/
//public class JsonAndFormArgumentResolver implements HandlerMethodArgumentResolver {
//
//    @Setter
//    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;
//
//    @Setter
//    private ModelAttributeMethodProcessor modelAttributeMethodProcessor;
//
//    public JsonAndFormArgumentResolver(ModelAttributeMethodProcessor methodProcessor, RequestResponseBodyMethodProcessor bodyMethodProcessor) {
//        this.modelAttributeMethodProcessor = methodProcessor;
//        this.requestResponseBodyMethodProcessor = bodyMethodProcessor;
//    }
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        boolean support = modelAttributeMethodProcessor.supportsParameter(parameter)
//                || requestResponseBodyMethodProcessor.supportsParameter(parameter);
//        return support;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        if (request != null) {
//            if (HttpMethod.GET.matches(request.getMethod().toUpperCase()))
//                return modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
//
//            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE))
//                return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
//        }
//        return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
//    }
//
//}
