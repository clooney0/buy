package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.CommentDao;
import com.cn.easybuy.dao.impl.CommentDaoImpl;


public class UpdateCommentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String ids = request.getParameter("id");
		String replyContent = request.getParameter("replyContent");
		if(ids==null || ids.equals("") || replyContent==null || replyContent.equals("")){
			request.getRequestDispatcher("guestbook.jsp?error=修改失败请重试").forward(request, response);
		}else{
			int id = Integer.valueOf(ids);
			CommentDao cDao = new CommentDaoImpl();
			boolean or = cDao.updateReplyById(id,replyContent);
			if(or){
				request.getRequestDispatcher("guestbook.jsp?error=修改留言成功").forward(request, response);
			}else{
				request.getRequestDispatcher("guestbook.jsp?error=修改失败请重试").forward(request, response);
			}
		}
	}

}
