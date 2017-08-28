package com.cn.easybuy.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.UserDao;
import com.cn.easybuy.dao.impl.UserDaoImpl;
import com.cn.easybuy.entity.User;


public class UpdateUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String ids = request.getParameter("id");
		int id = Integer.valueOf(ids);
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		String passWord = request.getParameter("passWord");
		String passWord2 = request.getParameter("passWord2");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		if(name.equals("") || passWord.equals("") || passWord2.equals("") 
				|| sex.equals("") || birth.equals("") || mobile.equals("") 
				|| address.equals("") || email.equals("") || !passWord.equals(passWord2)){
			request.getRequestDispatcher("user.jsp?error=用户信息填写有误，请重新修改").forward(request, response);
		}else{
			User user = new User();
			user.setUserID(id);
			user.setUserName(userName);
			user.setName(name);
			user.setPassword(passWord);
			user.setSex(sex);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = null;
			try {
				birthday = format.parse(birth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setBirthday(birthday);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setAddress(address);
			UserDao uDao = new UserDaoImpl();
			boolean or = uDao.updateUser(user);
			if(or){
				request.getRequestDispatcher("user.jsp?error=用户信息修改成功").forward(request, response);
			}else{
				request.getRequestDispatcher("user.jsp?error=用户信息填写有误，请重新修改").forward(request, response);
			}
		}
	}

}
