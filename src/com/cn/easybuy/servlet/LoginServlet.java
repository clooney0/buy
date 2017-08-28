package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.UserDao;
import com.cn.easybuy.dao.impl.UserDaoImpl;
import com.cn.easybuy.entity.User;


public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		UserDao uDao = new UserDaoImpl();
		User user = null;
		String numasd = (String)request.getSession().getAttribute("numrand");
		if(!code.equals(numasd)){
			request.getRequestDispatcher("/login.jsp?error=验证码错误").forward(request, response);
		}else{
			if(userId.equals("") && password.equals("")){
				
			}else{
				user = uDao.queryUserByNameAndPwd(userId, password);
				if(user != null){
					request.getSession().setAttribute("user", user);
					response.sendRedirect("index.jsp");
				}else{
					request.getRequestDispatcher("/login.jsp?error=登陆名或密码错误").forward(request, response);
				}
			}
		}
		
	}
	
}
