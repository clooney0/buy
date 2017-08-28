package com.cn.easybuy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.easybuy.dao.ProductCategoryDao;
import com.cn.easybuy.dao.ProductParentDao;
import com.cn.easybuy.dao.impl.ProductCategoryDaoImpl;
import com.cn.easybuy.dao.impl.ProductParentDaoImpl;
import com.cn.easybuy.entity.ProductCategory;
import com.cn.easybuy.entity.ProductParent;



/**
 * Servlet implementation class updateCategory
 */
public class updateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// parent
		int pid = Integer.valueOf(request.getParameter("parentid"));

		String parentname = "";
		if (request.getParameter("className") != null) {
			parentname = request.getParameter("className");
		}

		ProductParentDao ppd = new ProductParentDaoImpl();
		ProductParent pp = new ProductParent();

		pp.setParentID(pid);
		pp.setParentName(parentname);

		boolean result = ppd.updateProductParent(pp);
		if (result = true) {
			response.sendRedirect("manage-result.jsp");
			System.out.println("success");
		} else {
			request.getRequestDispatcher("productClass-modify.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// child
		String cgid = request.getParameter("categoryid");
		int cid = Integer.valueOf(cgid);
				

		int parid = Integer.valueOf(request.getParameter("parentId"));

		String parentname2 = request.getParameter("className2");

		ProductCategoryDao pcd = new ProductCategoryDaoImpl();
		ProductCategory pc = new ProductCategory();

		pc.setId(cid);
		pc.setParentID(parid);
		pc.setName(parentname2);

		boolean result2 = pcd.updateProductCategory(pc);
		if (result2 = true) {
			response.sendRedirect("manage-result.jsp");
			System.out.println("success");
		} else {
			request.getRequestDispatcher("productClass-modify2.jsp").forward(request, response);
		}

	}

}
