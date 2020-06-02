package com.yzy.Global;

import javax.servlet.*;
import java.io.IOException;

public class cssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //        设置请求编码格式
        servletRequest.setCharacterEncoding("utf-8");  //post 改变(请求实体)
        //        设置响应编码格式
        servletResponse.setContentType("text/css;charset=UTF-8");//修改响应编码
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
