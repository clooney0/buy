<%@page import="com.cn.easybuy.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page import="com.cn.easybuy.dao.impl.ProductDaoImpl"%>
<%@ page import="com.cn.easybuy.dao.ProductDao"%>
<%@ page import="com.cn.easybuy.util.Pages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	int pageIndex=1;
	String pageNo = request.getParameter("pageIndex");
	if(pageNo == null) {     //first browse  index.jsp
		pageIndex = 1;
	} else { 
		pageIndex = Integer.valueOf(pageNo); // click  Last/Next 
	}
	//
	Pages pages = new Pages();
	pages.setPageIndex(pageIndex);
	pages.setPageSize(5);
	// dao  to  get total record
	ProductDao product = new ProductDaoImpl();
	int pageCount = product.getCount(); //  total record
	pages.setPageCount(pageCount);
	//get this.page data  (session)
	ProductDao dao = new ProductDaoImpl();
	List<Product> list = dao.getList(pageIndex, pages.getPageSize());  //getList method(current page) 
	
	session.setAttribute("pages2", pages);  // args 
	session.setAttribute("list2", list);    //data
	response.sendRedirect("product.jsp");
	
%>