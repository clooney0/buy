package com.cn.easybuy.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.CommentDao;
import com.cn.easybuy.dao.impl.CommentDaoImpl;
import com.cn.easybuy.entity.Comment;


public class AddGuestBookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String guestContent = request.getParameter("guestContent");
		
		CommentDao cDao = new CommentDaoImpl();
		boolean or = cDao.addContentById(name, guestContent);
		if(or){
			response.sendRedirect("guestbook.jsp");
		}else{
			response.sendRedirect("guestbook.jsp?error=出现错误了，请重试");
		}
	}

}
