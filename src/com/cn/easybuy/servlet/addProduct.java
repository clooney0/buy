package com.cn.easybuy.servlet;

import java.io.IOException;
import java.io.InputStream;

import java.io.File;  
import java.io.FileOutputStream;  

import java.io.RandomAccessFile;  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.cn.easybuy.dao.ProductDao;
import com.cn.easybuy.dao.impl.ProductDaoImpl;
import com.cn.easybuy.entity.Product;


/**
 * Servlet implementation class addProduct
 */

public class addProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProduct() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String productName="";
		if(request.getParameter("productName")!=null)
		{
			productName=request.getParameter("productName");
		} 
		String productDescription="";
		if(request.getParameter("productDescription")!=null)
		{
			productDescription=request.getParameter("productDescription");
		} 
		String parentId = request.getParameter("parentId");
		int parentid = Integer.valueOf(parentId);
		
		String productPrice=request.getParameter("productPrice");
		Double productprice = Double.valueOf(productPrice);
		
		String productStock = request.getParameter("productStock");
		int productstock = Integer.valueOf(productStock);
		
		String photo = "";
		if(request.getParameter("photo")!=null) {
			photo="images/product/"+request.getParameter("photo");
		}
		ProductDao pd = new ProductDaoImpl();
		Product p = new Product();
		
		p.setName(productName);
		p.setDescription(productDescription);
		p.setEpcID(parentid);
		p.setPrice(productprice);
		p.setStock(productstock);
		p.setFileName(photo);
		
		boolean result=pd.addProduct(p);
		if(result!=false){
			response.sendRedirect("manage-result.jsp");
		}
		else{
			request.getRequestDispatcher("product-add.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
