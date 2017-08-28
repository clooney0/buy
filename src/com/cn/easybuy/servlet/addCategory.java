package com.cn.easybuy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductCategoryDao;
import com.cn.easybuy.dao.impl.ProductCategoryDaoImpl;
import com.cn.easybuy.entity.ProductCategory;


public class addCategory extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String parentId = request.getParameter("parentId");
		int parentid = Integer.valueOf(parentId);
		
		String name="";
		if(request.getParameter("className")!=null)
		{
			name=request.getParameter("className");
		} 
		
		ProductCategoryDao pcdao = new ProductCategoryDaoImpl();
		ProductCategory p = new ProductCategory();
		
		p.setId(parentid);
		p.setName(name);
		p.setParentID(parentid);
		
		boolean result=pcdao.addProductCategory(p);
		if(result!=false){
			response.sendRedirect("manage-result.jsp");
		}
		else{
			request.getRequestDispatcher("productClass-add.jsp").forward(request, response);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
