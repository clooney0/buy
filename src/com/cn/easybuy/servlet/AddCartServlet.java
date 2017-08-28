package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.CartDao;
import com.cn.easybuy.dao.impl.CartDaoImpl;
import com.cn.easybuy.entity.User;


public class AddCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String epid = request.getParameter("epid");
		int id = Integer.valueOf(epid);
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			CartDao cDao = new CartDaoImpl();
			boolean flag = cDao.queryCart(user, id);
			if(flag){
				cDao.updateCart(user, id);
			}else{
				cDao.addCart(user, id);
			}
			response.sendRedirect("shopping.jsp");
		}else{
			request.getRequestDispatcher("login.jsp?error=请先登录").forward(request, response);
		}
	}
}
