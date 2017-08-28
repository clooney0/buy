package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.OrderDao;
import com.cn.easybuy.dao.impl.OrderDaoImpl;


public class UpdateOrderStuts extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("OrderId");
		String statuss = request.getParameter("status");
		int id = Integer.valueOf(ids);
		int status = Integer.valueOf(statuss);
		OrderDao oDao = new OrderDaoImpl();
		oDao.updateStatusByID(id, status);
	}

}
