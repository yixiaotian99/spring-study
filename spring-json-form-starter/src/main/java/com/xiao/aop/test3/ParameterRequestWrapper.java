package com.xiao.aop.test3;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @Author sunjinwei
 * @Date 8/23/21 2:48 PM
 * @Description form表单扩展 request 参数
 * @see https://blog.csdn.net/weixin_41677422/article/details/108769682
 **/
@Slf4j
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<>();

    /**
     * 必须要实现的构造方法
     *
     * @param request
     */
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
//        this.params.putAll(request.getParameterMap());

        //如果是 get 请求接口，需要提取 body={"id":1111} 中 body= 之后的数据
        String body = request.getParameter("body");
        log.info("读取request中参数原始数据, formBody:{}", body);

        if (StringUtils.isNotEmpty(body)) {
            Map<String, Object> paramMap = new HashMap<>(8);

            //将字符串转为 json 结构，循环取出所有参数
            JSONObject bodyObject = JSONUtil.parseObj(body);
            if (bodyObject != null) {
                Set<Map.Entry<String, Object>> bodyEntries = bodyObject.entrySet();
                bodyEntries.stream().forEach(map -> {
                    paramMap.put(map.getKey(), map.getValue());
                });
                addAllParameters(paramMap);
            }
        }
    }

    /**
     * 重载构造方法
     *
     * @param request
     * @param extendParams
     */
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        //这里将扩展参数写入参数表
        addAllParameters(extendParams);
    }

    /**
     * 在获取所有的参数名,必须重写此方法，否则对象中参数值映射不上
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector(params.keySet()).elements();
    }

    /**
     * 重写getParameter方法
     *
     * @param name 参数名
     * @return 返回参数值
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values;
    }

    /**
     * 增加多个参数
     *
     * @param otherParams 增加的多个参数
     */
    public void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 增加参数
     * getParameterMap()中的类型是<String,String[]>类型的，所以这里要将其value转为String[]类型
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

}
