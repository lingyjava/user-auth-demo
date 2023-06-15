package com.ly.filter;

import com.alibaba.fastjson.JSON;
import com.ly.domain.user.UserMember;
import com.ly.user.mapper.UserMemberMapper;
import com.ly.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ly
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月06日 15:44:00
 * urlPatterns 目前拦截所有请求，后续根据需求修url匹配样式
 */
@Order(1)
@WebFilter(filterName="AuthorizationFilter",urlPatterns= {"/get/*"})
public class AuthorizationFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMemberMapper userMemberMapper;

    private static final Set<String> ALLOW_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            // 白名单
            "/user/register",
            "/user/login",
            "/user/getVerifyCodePic")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //注意下转型request对象。ServletRequest中没有获取头部信息的方法
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(req);

        String path = requestWrapper.getRequestURI().substring(requestWrapper.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowPath = ALLOW_PATHS.contains(path);
        if (allowPath) {
            filterChain.doFilter(requestWrapper, servletResponse);
            return;
        }

        //获取头部信息中的token
        String token = requestWrapper.getHeader("Authorization");
        if(token == null || "".equals(token)) {
            requestWrapper.setAttribute("verification result","not logged in");
            return;
        }

        if (redisUtil.get(token) == null) {
            throw new ServletException("Invalid Token");
        }

        requestWrapper.setAttribute("verification result", "wrong token");

        UserMember query = new UserMember();
        query.setUserLoginName(redisUtil.get(token));
        List<UserMember> members = userMemberMapper.queryAllByLimit(query, PageRequest.of(0, 1));
        if (!CollectionUtils.isEmpty(members)) {
            requestWrapper.setBodyJsonStr(JSON.toJSONString(members.get(0)));
        }
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
