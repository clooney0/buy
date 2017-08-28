package com.cn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductDao;
import com.cn.easybuy.dao.impl.ProductDaoImpl;
import com.cn.easybuy.entity.Product;

/**
 * Servlet implementation class updateProduct
 */

public class updateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		int ids = Integer.valueOf(id);
		
		String productName = "";
		if(request.getParameter("productName")!=null) {
			productName=request.getParameter("productName");
		}
		String productDescript = "";
		if(request.getParameter("productDescript")!=null) {
			productDescript=request.getParameter("productDescript");
		}
		
		String parentId=request.getParameter("parentId");
		int parentid = Integer.valueOf(parentId);
		
		String productPrice=request.getParameter("productPrice");
		Double productprice = Double.valueOf(productPrice);
		
		String productStock = request.getParameter("productStock");
		int productstock = Integer.valueOf(productStock);
		
		String photo = "";
		if(request.getParameter("photo")!=null) {
			photo=request.getParameter("photo");
		}
		
		//
		ProductDao pd = new ProductDaoImpl();
		Product p = new Product();
		p.setId(ids);
		p.setName(productName);
		p.setDescription(productDescript);
		p.setEpcID(parentid);
		p.setPrice(productprice);
		p.setStock(productstock);
		p.setFileName(photo);
		
		boolean result = pd.updateProduct(p);
			if(result = true) {
				request.getSession().removeAttribute("pages2");
				response.sendRedirect("manage-result.jsp");
				System.out.println("success");
			} else {
				request.getRequestDispatcher("product-modify.jsp").forward(request, response);
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
