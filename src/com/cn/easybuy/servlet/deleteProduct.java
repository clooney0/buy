package com.cn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductDao;
import com.cn.easybuy.dao.impl.ProductDaoImpl;
import com.cn.easybuy.entity.Product;


/**
 * Servlet implementation class deleteProduct
 */

public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public deleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("deleid");
		int ids = Integer.valueOf(id);
		
		
		ProductDao productdao= new ProductDaoImpl();
		Product p=new Product();
		
		p.setId(ids);
		
		boolean result=productdao.deleteProduct(id);
		if(result){
			request.getSession().removeAttribute("pages2");
			response.sendRedirect("manage-result.jsp");
		}
		else{
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
