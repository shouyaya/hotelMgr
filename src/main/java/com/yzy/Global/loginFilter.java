package com.yzy.Global;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if(request.getSession().getAttribute("LoginName")!=null){
            chain.doFilter(request,response);
        }else{
          if(request.getServletPath().indexOf("checkLogin")!=-1||request.getServletPath().indexOf("setLoginInfo")!=-1||request.getServletPath().indexOf("index")!=-1){
              chain.doFilter(request,response);
          }else {
              response.sendRedirect(request.getContextPath()+"/pages/loginPages/index.jsp");
          }
        }



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
