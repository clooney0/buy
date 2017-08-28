package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.CartDao;
import com.cn.easybuy.dao.impl.CartDaoImpl;


public class updateCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String number = request.getParameter("number");
		String cartid = request.getParameter("cartid");
		CartDao cDao = new CartDaoImpl();
		cDao.updateCaerQuantity(cartid,number);
		response.sendRedirect("shopping.jsp");
	}

}
