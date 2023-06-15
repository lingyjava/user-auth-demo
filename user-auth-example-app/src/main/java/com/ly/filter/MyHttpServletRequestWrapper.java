package com.ly.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写 HttpServletRequestWrapper
 * 处理表单、ajax请求
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月15日 14:26:00
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

    /** 用于存储请求参数 */
    private final Map<String , String[]> params = new HashMap<String, String[]>();

    public MyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        // 把请求参数添加到我们自己的map当中
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 添加参数到map中
     * @param extraParams
     */
    public void setParameterMap(Map<String, Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            setParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 添加参数到map中
     * @param name
     * @param value
     */
    public void setParameter(String name, Object value) {
        if (value != null) {
            System.out.println(value);
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues方法，从当前类的 map中取值
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }


    // Content-Type=application/json 格式的参数数据接收是通过流的形式接收

    private String bodyJsonStr;

    public MyHttpServletRequestWrapper(HttpServletRequest request,String bodyJsonStr) throws IOException {
        super(request);
        this.bodyJsonStr = bodyJsonStr;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(bodyJsonStr.getBytes("utf-8"));
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
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
            public void setReadListener(ReadListener listener) {
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBodyJsonStr() {
        return bodyJsonStr;
    }

    public void setBodyJsonStr(String bodyJsonStr) {
        this.bodyJsonStr = bodyJsonStr;
    }

}