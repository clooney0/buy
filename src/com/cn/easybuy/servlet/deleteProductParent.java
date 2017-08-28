package com.cn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductParentDao;
import com.cn.easybuy.dao.impl.ProductParentDaoImpl;
import com.cn.easybuy.entity.ProductParent;


/**
 * Servlet implementation class deleteProductParent
 */
public class deleteProductParent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProductParent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("delepid");
		int ids = Integer.valueOf(id);
		
		
		ProductParentDao pptdao= new ProductParentDaoImpl();
		ProductParent p=new ProductParent();
		
		p.setParentID(ids);;
		
		boolean result=pptdao.deleteProductParent(id);
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
