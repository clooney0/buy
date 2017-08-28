package com.cn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductCategoryDao;
import com.cn.easybuy.dao.impl.ProductCategoryDaoImpl;
import com.cn.easybuy.entity.ProductCategory;


/**
 * Servlet implementation class deleteProductCategory
 */
public class deleteProductCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProductCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("delecid");
		int ids = Integer.valueOf(id);
		
		
		ProductCategoryDao pcydao= new ProductCategoryDaoImpl();
		ProductCategory p=new ProductCategory();
		
		p.setParentID(ids);;
		
		boolean result=pcydao.deleteProductCategory(id);
		if(result){
			response.sendRedirect("manage-result.jsp");
		}
		else{
			request.getRequestDispatcher("productClass.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
