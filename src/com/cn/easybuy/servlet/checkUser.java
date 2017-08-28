package com.cn.easybuy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.UserDao;
import com.cn.easybuy.dao.impl.UserDaoImpl;

public class checkUser extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doGet(req, resp);
    }
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	 //防止传参中文乱码
    	 request.setCharacterEncoding("utf-8");
    	 response.setCharacterEncoding("utf-8");
    	 PrintWriter out=response.getWriter();
         //获得数据
         String uname=request.getParameter("uname");
         //调用业务方法
         UserDao userDao=new UserDaoImpl();
         boolean flag=userDao.queryUserByName(uname);//查询数据库中用户表用户名存不存在
         if(uname==null||uname.equals("")){
        	 out.print("");
         }else{
        	 if(flag){
        		 out.print("用户名存在!");
        	 }else{
        		 out.print("可以注册");
        	 }
         }
  
    }
}
