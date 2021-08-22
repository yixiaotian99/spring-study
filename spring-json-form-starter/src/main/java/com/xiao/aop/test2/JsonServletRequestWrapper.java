//package com.xiao.aop.test2;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.io.IOUtils;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
///**
// * @Author sunjinwei
// * @Date 8/21/21 11:26 PM
// * @Description TODO
// **/
//public class JsonServletRequestWrapper extends HttpServletRequestWrapper {
//    private Map<String, String[]> parameterMap; // 所有参数的Map集合
//    private JSONObject jsonBody;  // JSON 数据
//    private byte[] bytes;  // request inputstream 字节数据
//
//    public JsonServletRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//        jsonBody = null;
//        parameterMap = request.getParameterMap();
//        if (parameterMap == null || parameterMap.size() <= 0) {
//            // 非 form 数据时，判断是否为 JSON 数据
//            String bodyStr = IOUtils.toString(request.getInputStream());
//            bytes = bodyStr.getBytes();
//
//            if (JsonValidator.validate(bodyStr))
//                jsonBody = JSON.parseObject(bodyStr);
//
//            if (jsonBody != null) {
//                parameterMap = new HashMap<>();
//                Set<String> keySet = jsonBody.keySet();
//                for (String key : keySet) {
//                    // 解析 JSON 到 Map
//                    Object object = jsonBody.get(key);
//                    String[] t;
//                    if (object == null)
//                        continue;
//                    else if (object instanceof JSONArray) {
//                        JSONArray jsonArray = (JSONArray) object;
//                        t = new String[jsonArray.size()];
//
//                        for (int i=0; i<t.length; i++)
//                            t[i] = jsonArray.get(i).toString();
//                    } else {
//                        t = new String[1];
//                        t[0] = object.toString();
//                    }
//                    parameterMap.put(key, t);
//                }
//            }
//        }
//    }
//
//    @Override
//    public String getParameter(String name) {
//        String[] results = parameterMap.get(name);
//        if (results == null || results.length <= 0)
//            return null;
//        else
//            return results[0];
//    }
//
//    @Override
//    public Map<String, String[]> getParameterMap() {
//        return parameterMap;
//    }
//
//    @Override
//    public Enumeration<String> getParameterNames() {
//        Vector<String> vector = new Vector<>(parameterMap.keySet());
//        return vector.elements();
//    }
//
//    @Override
//    public String[] getParameterValues(String name) {
//        String[] results = parameterMap.get(name);
//        if (results == null || results.length <= 0)
//            return null;
//        else
//            return results;
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(getInputStream()));
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream data = new ByteArrayInputStream(bytes);
//        return new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return data.available() <= 0;
//            }
//            @Override
//            public boolean isReady() {
//                return data.available() > 0;
//            }
//            @Override
//            public void setReadListener(ReadListener readListener) {
//            }
//            @Override
//            public int read() throws IOException {
//                return data.read();
//            }
//        };
//    }
//
//
//    @Override
//    public Enumeration<String> getHeaders(String name) {
//        if (null != name && name.equalsIgnoreCase("Content-Type")) {
//            return new Enumeration<String>() {
//                private boolean hasGetted = false;
//
//                @Override
//                public String nextElement() {
//                    if (hasGetted) {
//                        throw new NoSuchElementException();
//                    } else {
//                        hasGetted = true;
//                        return "application/json;charset=utf-8";
//                    }
//                }
//
//                @Override
//                public boolean hasMoreElements() {
//                    return !hasGetted;
//                }
//            };
//        }
//        return super.getHeaders(name);
//    }
//}
