//package com.xiao.aop.test2;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author sunjinwei
// * @Date 8/21/21 11:25 PM
// * @Description TODO
// **/
//@Configuration
//public class ResolverConfig {
//    @Resource
//    private RequestMappingHandlerAdapter adapter;
//    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;
//    private ModelAttributeMethodProcessor modelAttributeMethodProcessor;
//
//    @PostConstruct
//    public void injectSelfMethodArgumentResolver() {
//        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
//        List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
//        if (argumentResolvers != null) {
//            for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
//                if (resolver instanceof RequestResponseBodyMethodProcessor) {
//                    requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) resolver;
//                }else if (resolver instanceof ModelAttributeMethodProcessor) {
//                    modelAttributeMethodProcessor = (ModelAttributeMethodProcessor) resolver;
//                } else {
//                    resolvers.add(resolver);
//                }
//            }
//            // 合并表单提交处理和@RequestBody
//            resolvers.add(new JsonAndFormArgumentResolver(modelAttributeMethodProcessor, requestResponseBodyMethodProcessor));
//            adapter.setArgumentResolvers(resolvers);
//        }
//    }
//}
