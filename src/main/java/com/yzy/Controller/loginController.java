package com.yzy.Controller;

import com.google.gson.Gson;
import com.yzy.Entity.Login;
import com.yzy.Service.loginService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller("loginController")
public class loginController {
    @Resource(name = "loginService")
    private loginService loginService;


    /**
     * 检验登陆
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String loginName=request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        System.out.println(loginName);
        Login byUsername = loginService.findByUsername(loginName);
        int flag=-1;
        if(byUsername!=null){
            if(byUsername.getLoginpwd().equals(loginPwd)){
                flag=1;
                HttpSession session=request.getSession();
                session.setAttribute("LoginName",loginName);
            }else{
                flag=0;
            }
        }
        Gson gson = new Gson();
        out.print(gson.toJson(flag));

    }


    /**
     * 将用户信息保存进session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void setLoginInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String loginName =(String) session.getAttribute("LoginName");
        Login byUsername = loginService.findByUsername(loginName);
        Gson gson=new Gson();
        out.print(gson.toJson(byUsername));
    }

    /**
     * 登陆成功跳转页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loginToList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/functionSelect.jsp").forward(request,response);
    }

    /**
     * 退出登陆
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("LoginName",null);
        Cookie cookie=new Cookie("loginName",null);
        response.addCookie(cookie);
        request.getRequestDispatcher("/pages/loginPages/index.jsp").forward(request,response);
    }
}
