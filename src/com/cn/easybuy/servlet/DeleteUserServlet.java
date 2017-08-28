package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.UserDao;
import com.cn.easybuy.dao.impl.UserDaoImpl;


public class DeleteUserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("id");
		int id = 0;
		if(ids!=null && !ids.equals("")){
			id = Integer.valueOf(ids);
		}
		UserDao uDao = new UserDaoImpl();
		boolean or = uDao.deleteUser(id);
		if(or){
			request.getRequestDispatcher("user.jsp?error=用户信息删除成功").forward(request, response);
		}else{
			request.getRequestDispatcher("user.jsp?error=用户信息删除失败").forward(request, response);
		}
	}
	
}
