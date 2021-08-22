package com.xiao.aop.test3;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author sunjinwei
 * @Date 8/21/21 11:22 PM
 * @Description 自定义 JsonAndFormArgumentResolver 类实现不同数据的 Resolver 分发
 * @see https://jiacyer.com/2019/01/23/Java-Spring-form-json-compatibility/
 **/
@Slf4j
public class JsonAndFormArgumentResolver implements HandlerMethodArgumentResolver {

    @Setter
    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    @Setter
    private ModelAttributeMethodProcessor modelAttributeMethodProcessor;

    public JsonAndFormArgumentResolver(ModelAttributeMethodProcessor methodProcessor, RequestResponseBodyMethodProcessor bodyMethodProcessor) {
        this.modelAttributeMethodProcessor = methodProcessor;
        this.requestResponseBodyMethodProcessor = bodyMethodProcessor;
    }

    /**
     * 是否有自定义注解，只有包含自定义注解，才会走自定义解析器
     *
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean support = modelAttributeMethodProcessor.supportsParameter(parameter)
                || requestResponseBodyMethodProcessor.supportsParameter(parameter);
        return support;
    }

    /**
     * 自定义解析器
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request != null) {

            //如果是 get 请求直接表单解析器
            if (HttpMethod.GET.matches(request.getMethod().toUpperCase())) {
                return modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
            }

            //如果是json使用json解析器
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {

                //如果是使用自定义解释，是表单与json合并后的解析器，需要将表单转为json提交，如果没有此注解，说明是原始请求，仍使用表单解析器
                if (!parameter.hasParameterAnnotation(RequestCustomBody.class)) {
                    return modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
                }

                log.info("表单强制使用json解析器, contentType:{}", MediaType.APPLICATION_JSON_VALUE);
                return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
            }

        }

        //默认使用json解析器
        return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }

}
