package com.vnnet.newscommon.utils;


import com.alibaba.fastjson.JSONObject;
import com.vnnet.newscommon.bean.HttpResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class HttpUtils {

    public static HttpServletRequest getHttpServletRequest() {
//        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }



    public static void print(HttpServletResponse response, int code, String msg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        HttpResult result = HttpResult.error(code, msg);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

}