package com.yzy.Global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class dispatcherServlet extends HttpServlet {
    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        context=new ClassPathXmlApplicationContext("application.xml");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath().substring(1);
        System.out.println(path);
        int index=path.indexOf("/");
        String beanName=null;
        String methodName=null;
        if(index!=-1){
            beanName=path.substring(0,index)+"Controller";
            methodName=path.substring(index+1,path.indexOf(".do"));
        }else{
            beanName="selfController";
            methodName=path.substring(0,path.indexOf(".do"));
        }
        Object obj = context.getBean(beanName);
        //通过反射来调用相应的controller的方法
        try {
            Method method = obj.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(obj,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
